package servidor;

import com.chat.tcpcommons.ClientThread;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import com.chat.tcpcommons.Observable;
import com.chat.tcpcommons.TemplateConnection;
import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Hector Espinoza
 */
public class ControlMessage extends Observable implements TemplateConnection, Runnable, IObserver {

    private ServerSocket server;
    public final Map<String, List<ClientThread>> rooms;
    private final String SALA_DE_ESPERA = "sala de espera";
    private final int PORT = 50064;
    private final Thread serverThread;
    private final ReentrantLock lock = new ReentrantLock();
    private final Map<String, Integer> roomSizes = new HashMap<>();

    private final IChatLogger logger = LoggerFactory.getLogger(ControlMessage.class);

    public ControlMessage() {
        serverThread = new Thread(this);
        rooms = new HashMap<>();
        rooms.put(SALA_DE_ESPERA, new ArrayList<>()); // Inicializa la lista para la sala de espera
    }

    public void init() {
        try {
            server = new ServerSocket(PORT);
            serverThread.start();
        } catch (IOException ex) {
            logger.error(String.format("Error al iniciar el servidor: %s", ex.getMessage()));
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket clientSocket = server.accept();

                // Crear y configurar el cliente
                ClientThread client = new ClientThread(clientSocket);
                //client.getUser().setNombre(nombreUsuario);

                // Iniciar el hilo del cliente
                Thread connectionThread = new Thread(client);
                connectionThread.start();

                // Agregar el cliente a la sala de espera
                rooms.get(SALA_DE_ESPERA).add(client);
                client.subscribe(this);
            }
        } catch (IOException ex) {
            logger.error(String.format("Error al iniciar la conexion al servidor: %s", ex.getMessage()));
        }
    }

    @Override
    public void onConectarse(Message message) {
        lock.lock();
        try {
            if (message != null && message.getSender() != null) {
                ClientThread clienteDisponible = rooms.get(SALA_DE_ESPERA).stream()
                        .filter(c -> c.getJugador().getNombre() == null)
                        .findFirst()
                        .orElse(null);

                if (clienteDisponible != null) {
                    clienteDisponible.setJugador(message.getSender());
                    System.out.println("Cliente en la sala de espera: " + message.getSender().getNombre());
                } else {
                    System.out.println("No hay espacio disponible para asignar el usuario en la sala de espera.");
                }
            } else {
                System.out.println("El Message o el que envía es null");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onDisconnect(Message message) {
        lock.lock();
        try {
            String codigoSala = message.getContent().getCodigoSala();
            var user = rooms.get(codigoSala).stream().filter(c -> c.getJugador().equals(message.getSender())).findFirst().orElse(null);
            if (user != null) {
                rooms.get(codigoSala).remove(user);
                user.disconnect();
                notifyObservers(message);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onUpdate(Object obj) {
        proccessMessage((Message) obj);
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj));
    }

    @Override
    public void onCrearSala(Message message) {
        lock.lock();
        try {
            String codigoSala = "000"; // Código fijo, puedes cambiarlo para que sea dinámico
            int tamaño = message.getContent().getTamaño();

            if (codigoSala == null || codigoSala.isEmpty()) {
                System.out.println("El código de la sala no puede estar vacío.");
                return;
            }

            if (rooms.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " ya existe.");
            } else {
                rooms.put(codigoSala, new ArrayList<>());
                roomSizes.put(codigoSala, tamaño); // Asociar el tamaño con la sala
                System.out.println("Sala " + codigoSala + " creada exitosamente con tamaño " + tamaño);
                onConectarse(message);
                onUnirseSala(message);
            }
        } finally {
            lock.unlock();
        }
    }

//    @Override
//    public void onCrearSala(Message message) {
//        lock.lock();
//        try {
//            String codigoSala = "000";
//
//            if (codigoSala == null || codigoSala.isEmpty()) {
//                System.out.println("El código de la sala no puede estar vacío.");
//                return;
//            }
//
//            if (rooms.containsKey(codigoSala)) {
//                System.out.println("La sala " + codigoSala + " ya existe.");
//            } else {
//                // Aquí accedemos al tamaño
//                int tamaño = message.getContent().getTamaño();
//                System.out.println("Tamaño de la sala recibido: " + tamaño);
//
//                // Usar el tamaño recibido en la lógica
//                rooms.put(codigoSala, new ArrayList<>());
//                System.out.println("Sala " + codigoSala + " creada exitosamente.");
//                onConectarse(message);
//                onUnirseSala(message); // Unir al cliente a la nueva sala
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//    @Override
//    public void onCrearSala(Message message) {
//        lock.lock();
//        try {
//            String codigoSala = "000";
//
//            if (codigoSala == null || codigoSala.isEmpty()) {
//                System.out.println("El código de la sala no puede estar vacío.");
//                return;
//            }
//
//            if (rooms.containsKey(codigoSala)) {
//                System.out.println("La sala " + codigoSala + " ya existe.");
//            } else {
//                rooms.put(codigoSala, new ArrayList<>());
//                System.out.println("Sala " + codigoSala + " creada exitosamente.");
//                onConectarse(message);
//                onUnirseSala(message); // Unir al cliente a la nueva sala
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
    
    @Override
    public void onUnirseSala(Message message) {
        lock.lock();
        try {
            String codigoSala = message.getContent().getCodigoSala();

            // Buscar al cliente en la sala de espera
            var user = rooms.get(SALA_DE_ESPERA).stream()
                    .filter(c -> c.getJugador().equals(message.getSender()))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                System.out.println("El cliente no está en la sala de espera.");
                return;
            }

            // Verificar si la sala existe
            if (codigoSala == null || !rooms.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " no existe.");

                // Crear mensaje de error para el cliente
                MessageBody cuerpoNegativo = new MessageBody();
                cuerpoNegativo.setRazonDesconexion("La sala " + codigoSala + " no existe.");
                Message MessageNegativo = new Message.Builder()
                        .body(cuerpoNegativo)
                        .messageType(MessageType.DESCONECTARSE)
                        .build();

                // Eliminar al cliente de la sala de espera y desconectarlo
                rooms.get(SALA_DE_ESPERA).remove(user);
                user.sendMessage(MessageNegativo);
                user.disconnect();
                return;
            }

            // Mover al cliente a la nueva sala
            rooms.get(SALA_DE_ESPERA).remove(user);
            rooms.get(codigoSala).add(user);
            System.out.println("El cliente " + message.getSender().getNombre() + " se unió a la sala " + codigoSala);

            // Obtener el tamaño de la sala (debería estar almacenado en un mapa como `roomSizes`)
            int tamañoSala = roomSizes.getOrDefault(codigoSala, -1); // Verificar si existe el tamaño

            if (tamañoSala == -1) {
                System.out.println("No se encontró el tamaño de la sala " + codigoSala);
                return;
            }

            // Enviar mensaje al cliente con el tamaño de la sala
            MessageBody cuerpoTamaño = new MessageBody();
            cuerpoTamaño.setTamaño(tamañoSala);
            Message mensajeTamaño = new Message.Builder()
                    .body(cuerpoTamaño)
                    .messageType(MessageType.PASAR_JUGADORES)
                    .build();

            user.sendMessage(mensajeTamaño);

            // Notificar a los demás jugadores que un nuevo cliente se unió
            MessageBody cuerpoOtrosJugadores = new MessageBody();
            Message MessageOtrosJugadores = new Message.Builder()
                    .body(cuerpoOtrosJugadores)
                    .messageType(MessageType.UNIRSE_SALA)
                    .build();

            rooms.get(codigoSala).forEach(cliente -> {
                if (!cliente.equals(user)) {
                    cliente.sendMessage(MessageOtrosJugadores);
                }
            });

            System.out.println("El cliente " + message.getSender().getNombre()
                    + " recibió el tamaño de la sala: " + tamañoSala);

        } finally {
            lock.unlock();
        }
    }

//    @Override
//    public void onUnirseSala(Message message) {
//        lock.lock();
//        try {
//            String codigoSala = message.getContent().getCodigoSala();
//
//            var user = rooms.get(SALA_DE_ESPERA).stream()
//                    .filter(c -> c.getJugador().equals(message.getSender()))
//                    .findFirst()
//                    .orElse(null);
//
//            if (user == null) {
//                System.out.println("El cliente no está en la sala de espera.");
//                return;
//            }
//
//            if (codigoSala == null || !rooms.containsKey(codigoSala)) {
//                System.out.println("La sala " + codigoSala + " no existe.");
//                //Message para el jugador que no se pudo unir
//                MessageBody cuerpoNegativo = new MessageBody();
//                cuerpoNegativo.setRazonDesconexion("La sala " + codigoSala + " no existe.");
//                MessageType tipoRespuesta = MessageType.DESCONECTARSE;
//                Message MessageNegativo = new Message.Builder()
//                        .body(cuerpoNegativo)
//                        .messageType(tipoRespuesta)
//                        .build();
//                rooms.get(SALA_DE_ESPERA).remove(user);
//                user.sendMessage(MessageNegativo);
//                user.disconnect();
//                return;
//            }
//
//            // Mover al cliente a la nueva sala
//            rooms.get(SALA_DE_ESPERA).remove(user);
//            rooms.get(codigoSala).add(user);
//            System.out.println("El cliente " + message.getSender().getNombre() + " se unió a la sala " + codigoSala);
////            message.setContent(rooms.get(codigoSala).get(0));
//
//            if (rooms.get(codigoSala).size() == 0) {
//                System.out.println("No sala");
//            } else {
//                System.out.println("Si sala");
//            }
//
//            for (int i = 0; i < rooms.get(codigoSala).size(); i++) {
//                System.out.println(rooms.get(codigoSala).get(i).getJugador().getNombre());
//            }
//
//            //Message para los jugador que ya estaban en la sala
//            MessageBody cuerpoOtrosJugadores = new MessageBody();
//            MessageType tipoOtrosJugadores = MessageType.UNIRSE_SALA;
//            Message MessageOtrosJugadores = new Message.Builder()
//                    .body(cuerpoOtrosJugadores)
//                    .messageType(tipoOtrosJugadores)
//                    .build();
//
//            //Message para el jugador que se unio
//            MessageBody cuerpoRespuesta = new MessageBody();
//            cuerpoRespuesta.setJugadores(rooms.get(codigoSala).size());
//            cuerpoRespuesta.setExisteSala(true);
//            MessageType tipoRespuesta = MessageType.PASAR_JUGADORES;
//            Message MessageRespuesta = new Message.Builder()
//                    .body(cuerpoRespuesta)
//                    .messageType(tipoRespuesta)
//                    .build();
//
//            rooms.get(codigoSala).forEach(cliente -> {
//                if (!cliente.equals(user)) {
//                    cliente.sendMessage(MessageOtrosJugadores);
//                }
//            });
//
//            for (int i = 0; i < rooms.get(codigoSala).size(); i++) {
//                rooms.get(codigoSala).get(i).sendMessage(MessageOtrosJugadores);
//            }
//
//        } finally {
//            lock.unlock();
//
//        }
//    }
    @Override
    public void onPasarOpciones(Message mensaje) {
        lock.lock();
        try {
            String codigoSala = mensaje.getContent().getCodigoSala();

            if (codigoSala == null) {
                System.out.println("El cliente no está en ninguna sala.");
                return;
            }

            // Enviar el mensaje a los demás jugadores en la sala
            List<ClientThread> clientesEnSala = rooms.get(codigoSala);
            clientesEnSala.forEach(cliente -> {
                if (!cliente.getJugador().equals(mensaje.getSender())) {
                    cliente.sendMessage(mensaje);
                }
            });

            System.out.println("Mensaje enviado a los demás jugadores en la sala: " + codigoSala);

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onPasarJugadores(Message mensaje) {

    }

    @Override
    public void onPasarCambios(Message mensaje) {
        String codigoSala = mensaje.getContent().getCodigoSala();

        if (codigoSala == null) {
            System.out.println("El cliente no está en ninguna sala.");
            return;
        }

        // Enviar el mensaje a los demás jugadores en la sala
        List<ClientThread> clientesEnSala = rooms.get(codigoSala);
        clientesEnSala.forEach(cliente -> {
            if (!cliente.getJugador().equals(mensaje.getSender())) {
                cliente.sendMessage(mensaje);
            }
        });

        System.out.println("Mensaje enviado a los demás jugadores en la sala: " + codigoSala);
    }

}
