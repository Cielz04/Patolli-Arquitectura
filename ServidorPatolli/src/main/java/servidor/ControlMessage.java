package servidor;

import PatolliCliente.ClientThread;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import com.chat.tcpcommons.Observable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import tablero.Tablero;
import entidades.Jugador;
import java.awt.Color;

import java.util.Observer;

public class ControlMessage extends Observable implements Runnable {

    private ServerSocket server;
    private Tablero tableroServidor;
    private final int PORT = 50064;
    private final Thread serverThread;
    private final ReentrantLock lock = new ReentrantLock();
    private final List<ClientThread> clientesConectados;
    private int tablerou = 0;

    public ControlMessage() {
        serverThread = new Thread(this);
        tableroServidor = new Tablero();
        clientesConectados = new ArrayList<>();
        tableroServidor.setJuegoInicia(false);
    }

    public void init() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Servidor iniciado en el puerto: " + PORT);
            serverThread.start();
        } catch (IOException ex) {
            System.err.println("Error al iniciar el servidor: " + ex.getMessage());
        }
    }

    @Override
    public void run() {

        try {
            while (true) {
                Socket clientSocket = server.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

                // Crear y registrar cliente
                ClientThread cliente = new ClientThread(clientSocket, jugadorNuevo());
                this.subscribe(cliente);
                agregarCliente(cliente);

                // Escuchar mensajes de cada cliente en el hilo del servidor
                new Thread(() -> escucharCliente(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error aceptando conexiones: " + e.getMessage());
        } finally {
            cerrarServidor();
        }

//        try {
//            while (true) {
//                Socket clientSocket = server.accept();
//                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());
//
//                // Crear un nuevo cliente y registrarlo
//                ClientThread client = new ClientThread(clientSocket, jugadorNuevo());
//                this.subscribe((IObserver) client);
//                agregarCliente(client);
//                new Thread(client).start();
//                MessageBody body = new MessageBody("Cliente Conectado");
//                Message mensaje = new Message.Builder()
//                        .messageType(MessageType.CONECTARSE)
//                        .body(body)
//                        .build();
//                
//                if (clientesConectados.size()!=0){
//                    
//                }
//
//                notificarTodos(mensaje);
//            }
//        } catch (IOException e) {
//            System.err.println("Error aceptando conexiones: " + e.getMessage());
//        } finally {
//            cerrarServidor();
//        }
    }

    private void escucharCliente(ClientThread cliente) {
        try {
            while (true) {

                Message mensaje = (Message) cliente.getInputStream().readObject();
                if (tablerou == 1) {
                    System.out.println("");
                }
                if (mensaje != null) {
                    procesarMensaje(mensaje); // Procesar mensaje en el servidor
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer mensaje del cliente: " + e.getMessage());
            eliminarCliente(cliente); // Desconectar al cliente en caso de error
        }
    }

    private void cerrarServidor() {
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el servidor: " + e.getMessage());
        }
    }

    public void procesarMensaje(Message mensaje) {
        lock.lock();
        try {
            switch (mensaje.getMessageType()) {
                case CONECTARSE ->
                    manejarConectarse(mensaje);
                case DESCONECTARSE ->
                    manejarDesconectarse(mensaje);
                case UNIRSE_SALA ->
                    manejarUnirseSala(mensaje);
                case CONFIGURAR_TABLERO ->
                    manejarConfigurarTableroServidor(mensaje);
                case TABLERO_ACTUALIZADO ->
                    manejarPasarCambios(mensaje);
                case ERROR ->
                    manejarError(mensaje);
                default ->
                    System.out.println("Tipo de mensaje no reconocido: " + mensaje.getMessageType());
            }
        } finally {
            lock.unlock();
        }
    }

    private void agregarCliente(ClientThread cliente) {
        lock.lock();
        try {
            clientesConectados.add(cliente);
        } finally {
            lock.unlock();
        }
    }

    private void manejarConectarse(Message mensaje) {
        Jugador jugador = mensaje.getSender();

        // Add null check for jugador
        if (jugador == null) {
            System.err.println("Error: Jugador is null");
            return;
        }

        if (esClienteRegistrado(jugador)) {
            enviarError(mensaje, "El jugador ya está conectado");
            return;
        }

        if (tableroServidor.isJuegoInicia()) {
            enviarError(mensaje, "El juego ya ha comenzado");
            return;
        }

        ClientThread cliente = obtenerClientePorJugador(jugador);
        if (cliente == null) {
            System.err.println("Cliente no encontrado para el jugador: " + jugador.getNombre());
            return;
        }

        tableroServidor.agregarJugador(jugador);
        enviarEstadoTablero(cliente);

        notificarTodos(new Message.Builder()
                .messageType(MessageType.UNIRSE_SALA)
                .body(new MessageBody("Jugador conectado: " + jugador.getNombre()))
                .build());
    }

    private void manejarPasarCambios(Message mensaje) {
        Tablero tableroActualizado = mensaje.getContent().getEstadoTablero();
        if (tableroActualizado == null) {
            System.err.println("El mensaje no contiene un tablero válido.");
            return;
        }

        tableroServidor.actualizarConMensaje(tableroActualizado);
        notificarTodos(new Message.Builder()
                .messageType(MessageType.TABLERO_ACTUALIZADO)
                .body(new MessageBody("Tablero actualizado", tableroServidor))
                .build());
        notifyObservers(new Message.Builder()
                .messageType(MessageType.TABLERO_ACTUALIZADO)
                .body(new MessageBody("Tablero del juego actualizado", tableroServidor))
                .build()
        );
    }

    private void enviarEstadoTablero(ClientThread cliente) {
        MessageBody body = new MessageBody("Estado del tablero actualizado", tableroServidor);
        Message mensaje = new Message.Builder()
                .messageType(MessageType.TABLERO_ACTUALIZADO)
                .body(body)
                .build();
        cliente.sendMessage(mensaje);
    }

    private void manejarDesconectarse(Message mensaje) {
        Jugador jugador = mensaje.getSender();
        ClientThread cliente = obtenerClientePorJugador(jugador);
        if (cliente == null) {
            System.err.println("El cliente no está registrado: " + jugador.getNombre());
            return;
        }

        eliminarCliente(cliente);
        notificarTodos(new Message.Builder()
                .messageType(MessageType.DESCONECTARSE)
                .body(new MessageBody("Jugador desconectado: " + jugador.getNombre()))
                .build());
    }

    private void eliminarCliente(ClientThread cliente) {
        lock.lock();
        try {
            clientesConectados.remove(cliente);
        } finally {
            lock.unlock();
        }
    }

    private boolean esClienteRegistrado(Jugador jugador) {
        for (ClientThread cliente : clientesConectados) {
            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
                return true;
            }
        }
        return false;
    }

    private ClientThread obtenerClientePorJugador(Jugador jugador) {
        for (ClientThread cliente : clientesConectados) {
            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
                return cliente;
            }
        }
        return null;
    }

    private void enviarError(Message mensaje, String error) {
        ClientThread cliente = obtenerClientePorJugador(mensaje.getSender());
        if (cliente != null) {
            cliente.sendMessage(new Message.Builder()
                    .messageType(MessageType.ERROR)
                    .body(new MessageBody(error))
                    .build());
        }
    }

    private Jugador jugadorNuevo() {
        int numeroJugador = tableroServidor.getJugadores().size() + 1;
        Color color = switch (numeroJugador) {
            case 1 ->
                Color.RED;
            case 2 ->
                Color.BLUE;
            case 3 ->
                Color.GREEN;
            case 4 ->
                Color.YELLOW;
            default ->
                Color.BLACK;
        };
        return new Jugador("Jugador " + numeroJugador, color);
    }

    // Método para manejar cuando un jugador se une a la sala
    private void manejarUnirseSala(Message mensaje) {
        System.out.println("Entro unirseSala");
        Jugador jugador = mensaje.getSender();

        
        // Add null checks
        if (jugador == null) {
            System.err.println("Error: Jugador is null when joining room");
            return;
        }

        if (tableroServidor.isJuegoInicia()) {
            notificarJugadorError(jugador, "El juego ya ha comenzado");
            return;
        }

        // Añadir el jugador al tablero

            System.out.println("Jugador " + jugador.getNombre() + " se unió al tablero.");


//            ClientThread cliente = findClientForPlayer(jugador);

//            if (cliente != null) {
                // Enviar al cliente el estado actual del tablero
//                enviarEstadoTablero(cliente);


           Tablero tableroMensaje = new Tablero();
           tableroMensaje.actualizarConMensaje(tableroServidor);
                // Notificar a los demás jugadores
                notificarTodos(new Message.Builder()
                        .messageType(MessageType.UNIRSE_SALA)
                        .body(new MessageBody("Jugador " + jugador.getNombre() + " se ha unido al juego"))
                        .build());

                notifyClient(new Message.Builder()
                        .messageType(MessageType.UNIRSE_SALA)
                        .body(new MessageBody("Jugador " + jugador.getNombre() + " se ha unido al juego", tableroServidor))
                        .build());
//            } else {
//                System.err.println("No se encontró el cliente para el jugador: " + jugador.getNombre());
//            }

    }

    private ClientThread findClientForPlayer(Jugador jugador) {
        if (jugador == null || jugador.getNombre() == null) {
        System.err.println("Error: Cannot find client for null player");
        return null;
    }

    return obtenerClientePorJugador(jugador);
    }

    private void manejarError(Message mensaje) {
        String mensajeError = mensaje.getContent().getMensaje();
        Jugador jugador = mensaje.getSender();

        System.err.println("Error recibido del jugador " + jugador.getNombre() + ": " + mensajeError);

    }

    // Método para manejar la configuración del tablero (para servidor)
    private void manejarConfigurarTableroServidor(Message mensaje) {
        // Obtener el tablero actualizado del mensaje
        Tablero tableroActualizado = mensaje.getContent().getEstadoTablero();

        // Actualizar el tablero del servidor
        tableroServidor.actualizarConMensaje(tableroActualizado);

        if (tablerou == 1) {
            System.out.println(tableroServidor.getApuesta());
        }

        System.out.println(tableroServidor.getCantidadCasillasAspa());

        // Notificar a todos los clientes conectados sobre la nueva configuración
        notificarTodos(new Message.Builder()
                .messageType(MessageType.CONFIGURAR_TABLERO)
                .body(new MessageBody("Tablero configurado en el servidor", tableroServidor))
                .build());

        tablerou++;
        System.out.println("Tablero del servidor actualizado con la configuración.");

        System.out.println(tableroServidor.getApuesta());
    }

    // Método para notificar a todos los clientes conectados
    private void notificarTodos(Message mensaje) {
        for (ClientThread cliente : clientesConectados) {
            System.out.println("Enviando mensaje a cliente: " + cliente.getJugador().getNombre());
            cliente.sendMessage(mensaje);
        }
    }

    private void notificarJugadorError(Jugador jugador, String mensajeError) {
        ClientThread cliente = obtenerClientePorJugador(jugador);

        if (cliente != null) {
            cliente.sendMessage(new Message.Builder()
                    .messageType(MessageType.ERROR)
                    .body(new MessageBody(mensajeError))
                    .build());
        } else {
            System.err.println("No se pudo enviar el error, cliente no encontrado para el jugador: " + jugador.getNombre());
        }
    }

    @Override
    public void notifyObservers(Object obj) {
        for (ClientThread cliente : clientesConectados) {
            cliente.onUpdate((Message) obj);
        }
    }

    public void notifyClient(Object obj) {
        Message mensaje = (Message) obj;
        for (ClientThread cliente : clientesConectados) {
            cliente.onUpdate((Message) obj);
        }
    }

}

//
//package servidor;
//
//import PatolliCliente.ClientThread;
//import com.chat.tcpcommons.Message;
//import com.chat.tcpcommons.MessageBody;
//import com.chat.tcpcommons.MessageType;
//import static com.chat.tcpcommons.MessageType.UNIRSE_SALA;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.locks.ReentrantLock;
//import tablero.Tablero;
//import entidades.Jugador;
//import java.awt.Color;
//
///**
// * Clase que gestiona las conexiones de clientes y la lógica del juego en el
// * servidor.
// *
// * @author Hector Espinoza
// */
//public class ControlMessage implements Runnable {
//
//    private ServerSocket server;
//    private final Tablero tableroServidor; // Único tablero de la partida
//    private final int PORT = 50064;
//    private final Thread serverThread;
//    private final ReentrantLock lock = new ReentrantLock();
//    private final List<ClientThread> clientesConectados; // Lista de clientes conectados
//
//    public ControlMessage() {
//        serverThread = new Thread(this);
//        tableroServidor = new Tablero();
//        clientesConectados = new ArrayList<>();
//        tableroServidor.setJuegoInicia(false); // Por defecto, el juego no ha iniciado
//    }
//
//    // Método para iniciar el servidor
//    public void init() {
//        try {
//            server = new ServerSocket(PORT);
//            System.out.println("Servidor iniciado en el puerto: " + PORT);
//            serverThread.start();
//        } catch (IOException ex) {
//            System.err.println("Error al iniciar el servidor: " + ex.getMessage());
//        }
//    }
//
//    @Override
//    public void run() {
//        try {
//            // Aceptar conexiones entrantes
//            while (true) {
//                Socket clientSocket = server.accept();
//                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());
//
//                // Crear un nuevo hilo para manejar este cliente
//                ClientThread client = new ClientThread(clientSocket, jugadorNuevo());
//                agregarCliente(client); // Agregar cliente a la lista
//                new Thread(client).start();
//            }
//        } catch (IOException e) {
//            System.err.println("Error aceptando conexiones: " + e.getMessage());
//        } finally {
//            try {
//                if (server != null) {
//                    server.close(); // Cierra el servidor si se detiene
//                }
//            } catch (IOException e) {
//                System.err.println("Error al cerrar el servidor: " + e.getMessage());
//            }
//        }
//    }
//
//    // Método para procesar los mensajes recibidos
//    public void procesarMensaje(Message mensaje) {
//        lock.lock();
//        try {
//            switch (mensaje.getMessageType()) {
//                case CONECTARSE ->
//                    manejarConectarse(mensaje);
//                case DESCONECTARSE ->
//                    manejarDesconectarse(mensaje);
//                case UNIRSE_SALA ->
//                    manejarUnirseSala(mensaje);
//                case CONFIGURAR_TABLERO ->
//                    manejarConfigurarTableroServidor(mensaje);
//                case TABLERO_ACTUALIZADO -> {
//                    System.out.println("Mensaje TABLERO_ACTUALIZADO recibido.");
//                    manejarPasarCambios(mensaje);
//                }
//                case ERROR ->
//                    manejarError(mensaje);
//                default ->
//                    System.out.println("Tipo de mensaje no reconocido: " + mensaje.getMessageType());
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    private void agregarCliente(ClientThread cliente) {
//        clientesConectados.add(cliente);
//    }
//
//    private void manejarConectarse(Message mensaje) {
//        Jugador jugador = mensaje.getSender();
//        ClientThread cliente = obtenerClientePorJugador(jugador);
//
//        if (esClienteRegistrado(jugador)) {
//            System.out.println("El jugador ya está registrado: " + jugador.getNombre());
//            cliente.sendMessage(new Message.Builder()
//                    .messageType(MessageType.ERROR)
//                    .body(new MessageBody("El jugador ya está conectado"))
//                    .build());
//            return;
//        }
//
//        if (tableroServidor.isJuegoInicia()) {
//            cliente.sendMessage(new Message.Builder()
//                    .messageType(MessageType.ERROR)
//                    .body(new MessageBody("El juego ya ha comenzado"))
//                    .build());
//            return;
//        }
//
//        if (tableroServidor.agregarJugador(jugador)) {
//            System.out.println("Jugador " + jugador.getNombre() + " se ha conectado.");
//
//            // Añadir el cliente a la lista de clientes conectados
//            agregarCliente(cliente);
//
//            // Enviar el estado del tablero al nuevo cliente
//            enviarEstadoTablero(cliente);
//
//            // Notificar a todos los jugadores conectados sobre el nuevo jugador
//            notificarTodos(new Message.Builder()
//                    .messageType(MessageType.UNIRSE_SALA)
//                    .body(new MessageBody("Jugador conectado: " + jugador.getNombre()))
//                    .build());
//        } else {
//            cliente.sendMessage(new Message.Builder()
//                    .messageType(MessageType.ERROR)
//                    .body(new MessageBody("No hay espacio para más jugadores"))
//                    .build());
//        }
//    }
//
//    private boolean esClienteRegistrado(Jugador jugador) {
//        System.out.println("Entro a verificar cliente");
//        for (ClientThread cliente : clientesConectados) {
//            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
//                return true; // El cliente ya está registrado
//            }
//        }
//        return false; // El cliente no está registrado
//    }
//
//    // Verifica si el jugador ya está conectado al juego
//    private ClientThread jugadorYaConectado(Jugador jugador) {
//        for (ClientThread cliente : clientesConectados) {
//            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
//                return cliente;  // El jugador ya está conectado
//            }
//        }
//        return null;  // El jugador no está conectado
//    }
//
//    // Método para manejar cuando un jugador se une a la sala
//    private void manejarUnirseSala(Message mensaje) {
//        System.out.println("Entro unirseSala");
//        Jugador jugador = mensaje.getSender();
//
//        if (tableroServidor.isJuegoInicia()) {
//            // Notificar al jugador que no puede unirse si el juego ya ha comenzado
//            notificarJugadorError(jugador, "El juego ya ha comenzado");
//            return;
//        }
//
//        // Añadir el jugador al tablero
//        if (tableroServidor.agregarJugador(jugador)) {
//            System.out.println("Jugador " + jugador.getNombre() + " se unió al tablero.");
//
//            // Enviar al cliente el estado actual del tablero
//            ClientThread cliente = obtenerClientePorJugador(jugador);
//            enviarEstadoTablero(cliente);  // Enviar el tablero actualizado al cliente
//
//            // Notificar a los demás jugadores que un nuevo jugador se ha unido
//            notificarTodos(new Message.Builder()
//                    .messageType(MessageType.UNIRSE_SALA)
//                    .body(new MessageBody("Jugador " + jugador.getNombre() + " se ha unido al juego"))
//                    .build());
//        } else {
//            // Si no hay espacio en el tablero
//            notificarJugadorError(jugador, "No hay espacio para más jugadores");
//        }
//    }
//
//    // Método para manejar los cambios en el tablero
//    private void manejarPasarCambios(Message mensaje) {
//        if (mensaje.getContent().getEstadoTablero() == null) {
//            System.err.println("El mensaje no contiene un tablero válido.");
//            return;
//        }
//
//        // Actualiza el tablero del servidor
//        Tablero tableroActualizado = mensaje.getContent().getEstadoTablero();
//        tableroServidor.actualizarConMensaje(tableroActualizado);
//
//        System.out.println("Tablero del servidor actualizado con cambios del cliente.");
//
//        // Notifica a todos los clientes conectados
//        notificarTodos(new Message.Builder()
//                .messageType(MessageType.TABLERO_ACTUALIZADO)
//                .body(new MessageBody("Tablero actualizado", tableroServidor))
//                .build());
//    }
//
//    // Método para enviar el estado del tablero a un cliente
//    private void enviarEstadoTablero(ClientThread cliente) {
//        MessageBody body = new MessageBody();
//        body.setEstadoTablero(tableroServidor);  // El estado actual del tablero
//        body.setMensaje("Tablero actualizado");  // Mensaje informativo
//
//        // Enviar el mensaje con el tablero actualizado
//        Message mensaje = new Message.Builder()
//                .messageType(MessageType.TABLERO_ACTUALIZADO)
//                .body(body)
//                .build();
//
//        cliente.sendMessage(mensaje);  // Enviar el mensaje al cliente que se unió
//        System.out.println("Estado del tablero enviado al cliente.");
//    }
//
//    // Método para eliminar un cliente de la lista de clientes conectados
//    public void eliminarCliente(ClientThread cliente) {
//        lock.lock();
//        try {
//            clientesConectados.remove(cliente);// Método para notificar a todos los clientes conectados
//    private void notificarTodos(Message mensaje) {
//        for (ClientThread cliente : clientesConectados) {
//            System.out.println("Enviando mensaje a cliente: " + cliente.getJugador().getNombre());
//            cliente.sendMessage(mensaje);
//        }
//    }
//            System.out.println("Cliente eliminado.");
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    // Método para notificar a todos los clientes conectados
//    private void notificarTodos(Message mensaje) {
//        for (ClientThread cliente : clientesConectados) {
//            System.out.println("Enviando mensaje a cliente: " + cliente.getJugador().getNombre());
//            cliente.sendMessage(mensaje);
//        }
//    }
//
//    private ClientThread obtenerClientePorJugador(Jugador jugador) {
//        for (ClientThread cliente : clientesConectados) {
//            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
//                return cliente;
//            }
//        }
//        return null;
//    }
//
//    private void manejarError(Message mensaje) {
//        String mensajeError = mensaje.getContent().getMensaje();
//        Jugador jugador = mensaje.getSender();
//
//        System.err.println("Error recibido del jugador " + jugador.getNombre() + ": " + mensajeError);
//
//    }
//
//    private void notificarJugadorError(Jugador jugador, String mensajeError) {
//        ClientThread cliente = obtenerClientePorJugador(jugador);
//
//        if (cliente != null) {
//            cliente.sendMessage(new Message.Builder()
//                    .messageType(MessageType.ERROR)
//                    .body(new MessageBody(mensajeError))
//                    .build());
//        }
//    }
//
//    // Método para manejar la desconexión de un cliente
//    private void manejarDesconectarse(Message mensaje) {
//        Jugador jugador = mensaje.getSender();
//        ClientThread cliente = obtenerClientePorJugador(jugador);
//
//        if (cliente == null) {
//            System.err.println("El jugador no está conectado.");
//            return;
//        }
//
//        eliminarCliente(cliente);
//
//        // Notificar a todos que el jugador se ha desconectado
//        notificarTodos(new Message.Builder()
//                .messageType(MessageType.DESCONECTARSE)
//                .body(new MessageBody("Jugador desconectado: " + jugador.getNombre()))
//                .build());
//
//        System.out.println("Jugador " + jugador.getNombre() + " se ha desconectado.");
//    }
//
//    // Método para manejar errores
//    private void manejarError(Message mensaje, ClientThread cliente) {
//        System.err.println("Error recibido del cliente: " + mensaje.getContent().getMensaje());
//    }
//
//    // Método para manejar la configuración del tablero (para servidor)
//    private void manejarConfigurarTableroServidor(Message mensaje) {
//        // Obtener el tablero actualizado del mensaje
//        Tablero tableroActualizado = mensaje.getContent().getEstadoTablero();
//
//        // Actualizar el tablero del servidor
//        tableroServidor.actualizarConMensaje(tableroActualizado);
//
//        // Notificar a todos los clientes conectados sobre la nueva configuración
//        notificarTodos(new Message.Builder()
//                .messageType(MessageType.CONFIGURAR_TABLERO)
//                .body(new MessageBody("Tablero configurado en el servidor", tableroServidor))
//                .build());
//
//        System.out.println("Tablero del servidor actualizado con la configuración.");
//    }
//
//    // Método para manejar la actualización del tablero (cuando un cliente actualiza el tablero)
//    private void manejarActualizacionTablero(Message mensaje, ClientThread cliente) {
//        // Aquí puedes manejar la actualización del tablero, como propagar cambios
//        System.out.println("Actualización de tablero recibida.");
//    }
//
//    private Jugador jugadorNuevo() {
//        int numeroJugador = tableroServidor.getJugadores().size() + 1;
//        Color color = null;
//        if (numeroJugador == 1) {
//            color = Color.RED;
//        }
//        if (numeroJugador == 2) {
//            color = Color.BLUE;
//        }
//        if (numeroJugador == 3) {
//            color = Color.GREEN;
//        }
//        if (numeroJugador == 4) {
//            color = Color.YELLOW;
//        }
//
//        return new Jugador("Jugador " + numeroJugador, color);
//    }
//
//}
