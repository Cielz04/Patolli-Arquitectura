package servidor;

import PatolliCliente.ClientThread;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import static com.chat.tcpcommons.MessageType.UNIRSE_SALA;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import tablero.Tablero;
import entidades.Jugador;

/**
 * Clase que gestiona las conexiones de clientes y la lógica del juego en el
 * servidor.
 *
 * @author Hector Espinoza
 */
public class ControlMessage implements Runnable {

    private ServerSocket server;
    private final Tablero tableroServidor; // Único tablero de la partida
    private final int PORT = 50064;
    private final Thread serverThread;
    private final ReentrantLock lock = new ReentrantLock();
    private final List<ClientThread> clientesConectados; // Lista de clientes conectados

    public ControlMessage() {
        serverThread = new Thread(this);
        tableroServidor = new Tablero();
        clientesConectados = new ArrayList<>();
        tableroServidor.setJuegoInicia(false); // Por defecto, el juego no ha iniciado
    }

    // Método para iniciar el servidor
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
            // El servidor estará en espera de conexiones entrantes.
            while (true) {
                // Espera una conexión del cliente (bloqueante).
                Socket clientSocket = server.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

                // Crea un nuevo hilo de cliente para manejar la comunicación con el cliente conectado.
                ClientThread client = new ClientThread(clientSocket);

                // Aquí puedes agregar el cliente a una lista de clientes activos si es necesario
                agregarCliente(client); // Método para agregar el cliente a la lista de clientes

                // Inicia un hilo para gestionar la comunicación con este cliente.
                new Thread(client).start();
            }
        } catch (IOException ex) {
            // Si ocurre un error al aceptar conexiones, se imprime el mensaje de error.
            System.err.println("Error aceptando conexiones: " + ex.getMessage());
        }
    }

    // Método para procesar los mensajes recibidos
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

    private void manejarConectarse(Message mensaje) {
        Jugador jugador = mensaje.getSender();
        ClientThread cliente = obtenerClientePorJugador(jugador);

        if (cliente != null) {
            cliente.sendMessage(new Message.Builder()
                    .messageType(MessageType.ERROR)
                    .body(new MessageBody("El jugador ya está conectado"))
                    .build());
            return;
        }

        if (tableroServidor.isJuegoInicia()) {
            // Respuesta de error si el juego ya inició
            cliente.sendMessage(new Message.Builder()
                    .messageType(MessageType.ERROR)
                    .body(new MessageBody("El juego ya ha comenzado"))
                    .build());
            return;
        }

        if (tableroServidor.agregarJugador(jugador)) {
            // Actualización y notificación
            notificarTodos(new Message.Builder()
                    .messageType(MessageType.UNIRSE_SALA)
                    .body(new MessageBody("Jugador conectado: " + jugador.getNombre()))
                    .build());
        } else {
            cliente.sendMessage(new Message.Builder()
                    .messageType(MessageType.ERROR)
                    .body(new MessageBody("No hay espacio para más jugadores"))
                    .build());
        }
    }

    // Verifica si el jugador ya está conectado al juego
    private ClientThread jugadorYaConectado(Jugador jugador) {
        for (ClientThread cliente : clientesConectados) {
            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
                return cliente;  // El jugador ya está conectado
            }
        }
        return null;  // El jugador no está conectado
    }

    // Método para manejar cuando un jugador se une a la sala
    private void manejarUnirseSala(Message mensaje) {
        Jugador jugador = mensaje.getSender();

        if (tableroServidor.isJuegoInicia()) {
            // Notificar que el juego ya ha comenzado
            notificarJugadorError(jugador, "El juego ya ha comenzado");
            return;
        }

        if (tableroServidor.agregarJugador(jugador)) {
            System.out.println("Jugador " + jugador.getNombre() + " se unió a la sala.");

            // Enviar estado actualizado del tablero al nuevo jugador
            ClientThread cliente = obtenerClientePorJugador(jugador);
            enviarEstadoTablero(cliente);

            // Notificar a los demás jugadores
            notificarTodos(new Message.Builder()
                    .messageType(MessageType.UNIRSE_SALA)
                    .body(new MessageBody("Jugador " + jugador.getNombre() + " se ha unido a la sala"))
                    .build());
        } else {
            // No hay espacio para más jugadores
            notificarJugadorError(jugador, "No hay espacio para más jugadores");
        }
    }

    // Método para manejar los cambios en el tablero
    private void manejarPasarCambios(Message mensaje) {
        Tablero tableroActualizado = mensaje.getContent().getEstadoTablero();

        // Actualizar el tablero del servidor
        tableroServidor.actualizarConMensaje(tableroActualizado);
        System.out.println("Cambios en el tablero aplicados.");

        // Notificar a todos los jugadores sobre los cambios
        notificarTodos(new Message.Builder()
                .messageType(MessageType.TABLERO_ACTUALIZADO)
                .body(new MessageBody("Tablero actualizado", tableroServidor))
                .build());
    }

    // Método para enviar el estado del tablero a un cliente
    private void enviarEstadoTablero(ClientThread cliente) {
        MessageBody body = new MessageBody();
        body.setEstadoTablero(tableroServidor);
        body.setMensaje("NUEVO TABLERO");
        Message mensaje = new Message.Builder()
                .messageType(MessageType.TABLERO_ACTUALIZADO)
                .body(body)
                .build();
        cliente.sendMessage(mensaje);
        System.out.println("Estado del tablero enviado al cliente.");
    }

    // Método para agregar un cliente a la lista de clientes conectados
    private void agregarCliente(ClientThread cliente) {
        lock.lock();
        try {
            clientesConectados.add(cliente);
        } finally {
            lock.unlock();
        }
    }

    // Método para eliminar un cliente de la lista de clientes conectados
    public void eliminarCliente(ClientThread cliente) {
        lock.lock();
        try {
            clientesConectados.remove(cliente);
        } finally {
            lock.unlock();
        }
    }

    // Método para notificar a todos los clientes conectados
    private void notificarTodos(Message mensaje) {
        for (ClientThread cliente : clientesConectados) {
            cliente.sendMessage(mensaje);
        }
    }

    private ClientThread obtenerClientePorJugador(Jugador jugador) {
        for (ClientThread cliente : clientesConectados) {
            if (cliente.getJugador() != null && cliente.getJugador().equals(jugador)) {
                return cliente;
            }
        }
        return null;
    }

    private void manejarError(Message mensaje) {
        String mensajeError = mensaje.getContent().getMensaje();
        Jugador jugador = mensaje.getSender();

        System.err.println("Error recibido del jugador " + jugador.getNombre() + ": " + mensajeError);

    }

    private void notificarJugadorError(Jugador jugador, String mensajeError) {
        ClientThread cliente = obtenerClientePorJugador(jugador);

        if (cliente != null) {
            cliente.sendMessage(new Message.Builder()
                    .messageType(MessageType.ERROR)
                    .body(new MessageBody(mensajeError))
                    .build());
        }
    }

    // Método para manejar la desconexión de un cliente
    private void manejarDesconectarse(Message mensaje) {
        Jugador jugador = mensaje.getSender();
        ClientThread cliente = obtenerClientePorJugador(jugador);

        if (cliente == null) {
            System.err.println("El jugador no está conectado.");
            return;
        }

        eliminarCliente(cliente);

        // Notificar a todos que el jugador se ha desconectado
        notificarTodos(new Message.Builder()
                .messageType(MessageType.DESCONECTARSE)
                .body(new MessageBody("Jugador desconectado: " + jugador.getNombre()))
                .build());

        System.out.println("Jugador " + jugador.getNombre() + " se ha desconectado.");
    }

    // Método para manejar errores
    private void manejarError(Message mensaje, ClientThread cliente) {
        System.err.println("Error recibido del cliente: " + mensaje.getContent().getMensaje());
    }

    // Método para manejar la configuración del tablero (para servidor)
    private void manejarConfigurarTableroServidor(Message mensaje) {
        MessageBody configuracion = mensaje.getContent();

        // Aquí puedes extraer parámetros del mensaje y aplicarlos al tablero
        tableroServidor.actualizarConMensaje(configuracion.getEstadoTablero());

        System.out.println("Configuración del tablero aplicada: " + configuracion);

        // Notificar a todos los jugadores sobre la nueva configuración
        notificarTodos(new Message.Builder()
                .messageType(MessageType.CONFIGURAR_TABLERO)
                .body(new MessageBody("El tablero ha sido configurado."))
                .build());
    }

    // Método para manejar la actualización del tablero (cuando un cliente actualiza el tablero)
    private void manejarActualizacionTablero(Message mensaje, ClientThread cliente) {
        // Aquí puedes manejar la actualización del tablero, como propagar cambios
        System.out.println("Actualización de tablero recibida.");
    }

    

}
