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
import salas.Sala;

/**
 *
 * @author Hector Espinoza
 */
public class ControlMessage extends Observable implements TemplateConnection, Runnable, IObserver {

    private ServerSocket server;
    private final Map<String, Sala> rooms;
    private final String SALA_DE_ESPERA = "sala de espera";
    private final int PORT = 50064;
    private final Thread serverThread;
    private final ReentrantLock lock = new ReentrantLock();

    private final IChatLogger logger = LoggerFactory.getLogger(ControlMessage.class);

    public ControlMessage() {
        serverThread = new Thread(this);
        rooms = new HashMap<>();
        rooms.put(SALA_DE_ESPERA, new Sala(SALA_DE_ESPERA)); // Sala de espera como una Sala
    }

    public void init() {
        try {
            server = new ServerSocket(PORT);
            rooms.put(SALA_DE_ESPERA, new Sala(SALA_DE_ESPERA)); // Inicializa la sala de espera como una Sala
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

                // Iniciar el hilo del cliente
                Thread connectionThread = new Thread(client);
                connectionThread.start();

                // Agregar el cliente a la sala de espera
                Sala salaDeEspera = rooms.get(SALA_DE_ESPERA); // Obtenemos la sala de espera como Sala
                if (salaDeEspera != null) {
                    salaDeEspera.agregarJugador(client);
                    client.subscribe(this);
                    System.out.println("Cliente agregado a la sala de espera.");
                } else {
                    System.out.println("Error: No se encontró la sala de espera.");
                }
            }
        } catch (IOException ex) {
            logger.error(String.format("Error al iniciar la conexión al servidor: %s", ex.getMessage()));
        }
    }

    @Override
    public void onConectarse(Message message) {
        lock.lock();
        try {
            if (message != null && message.getSender() != null) {
                Sala salaDeEspera = rooms.get(SALA_DE_ESPERA);
                if (salaDeEspera != null) {
                    ClientThread clienteDisponible = salaDeEspera.getJugadores().stream()
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
                    System.out.println("Error: Sala de espera no encontrada.");
                }
            } else {
                System.out.println("El Message o el que envía es null.");
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
            if (codigoSala != null && rooms.containsKey(codigoSala)) {
                Sala sala = rooms.get(codigoSala);

                // Buscar al cliente en la sala
                var user = sala.getJugadores().stream()
                        .filter(c -> c.getJugador().equals(message.getSender()))
                        .findFirst()
                        .orElse(null);

                if (user != null) {
                    sala.getJugadores().remove(user); // Eliminar al cliente de la sala
                    user.disconnect(); // Desconectar al cliente
                    notifyObservers(message); // Notificar a los observadores
                    System.out.println("El cliente " + message.getSender().getNombre() + " se ha desconectado de la sala " + codigoSala);
                } else {
                    System.out.println("El cliente no fue encontrado en la sala " + codigoSala);
                }
            } else {
                System.out.println("El código de sala es inválido o no existe.");
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
            String codigoSala = "000";

            if (codigoSala == null || codigoSala.isEmpty()) {
                System.out.println("El código de la sala no puede estar vacío.");
                return;
            }

            if (rooms.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " ya existe.");
            } else {
                Sala nuevaSala = new Sala(codigoSala); // Crear una nueva sala
                rooms.put(codigoSala, nuevaSala); // Añadir la sala al mapa
                System.out.println("Sala " + codigoSala + " creada exitosamente.");
                onConectarse(message);
                onUnirseSala(message); // Unir al cliente a la nueva sala
            }
        } finally {
            lock.unlock();
        }

//        lock.lock();
//        try {
//            String codigoSala = message.getContent().getCodigoSala();
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
    }

    @Override
    public void onUnirseSala(Message message) {

        lock.lock();
        try {
            String codigoSala = message.getContent().getCodigoSala();

            // Buscar al cliente en la sala de espera
            var user = rooms.get(SALA_DE_ESPERA).getJugadores().stream()
                    .filter(c -> c.getJugador().equals(message.getSender()))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                System.out.println("El cliente no está en la sala de espera.");
                return;
            }

            if (codigoSala == null || !rooms.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " no existe.");
                return;
            }

            // Mover al cliente a la nueva sala
            Sala sala = rooms.get(codigoSala);
            rooms.get(SALA_DE_ESPERA).getJugadores().remove(user);
            sala.agregarJugador(user);
            System.out.println("El cliente " + message.getSender().getNombre() + " se unió a la sala " + codigoSala);

            // Enviar el estado del tablero al jugador que se une
            enviarEstadoTablero(sala, user);

        } finally {
            lock.unlock();
        }

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
//                } else {
//
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
    }

    private void enviarEstadoTablero(Sala sala, ClientThread user) {
        MessageBody body = new MessageBody();
        body.setCodigoSala(sala.getCodigo());
        body.setEstadoTablero(sala.getTablero()); // Obtener el tablero de la sala

        Message mensaje = new Message.Builder()
                .messageType(MessageType.ESTADO_TABLERO)
                .body(body)
                .build();

        // Enviar el mensaje al jugador
        user.sendMessage(mensaje);
    }

    @Override
    public void onPasarOpciones(Message mensaje) {

        lock.lock();
        try {
            String codigoSala = mensaje.getContent().getCodigoSala();

            if (codigoSala == null || !rooms.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " no existe.");
                return;
            }

            Sala sala = rooms.get(codigoSala);

            // Actualizar el estado del tablero en la sala
            sala.setTablero(mensaje.getContent().getEstadoTablero());

            // Propagar los cambios a todos los jugadores de la sala
            List<ClientThread> clientesEnSala = sala.getJugadores();
            clientesEnSala.forEach(cliente -> {
                if (!cliente.getJugador().equals(mensaje.getSender())) {
                    cliente.sendMessage(mensaje);
                }
            });

            System.out.println("Mensaje enviado a los demás jugadores en la sala: " + codigoSala);

        } finally {
            lock.unlock();
        }

//        lock.lock();
//        try {
//            String codigoSala = mensaje.getContent().getCodigoSala();
//
//            if (codigoSala == null) {
//                System.out.println("El cliente no está en ninguna sala.");
//                return;
//            }
//
//            // Enviar el mensaje a los demás jugadores en la sala
//            List<ClientThread> clientesEnSala = rooms.get(codigoSala);
//            clientesEnSala.forEach(cliente -> {
//                if (!cliente.getJugador().equals(mensaje.getSender())) {
//                    cliente.sendMessage(mensaje);
//                }
//            });
//
//            System.out.println("Mensaje enviado a los demás jugadores en la sala: " + codigoSala);
//
//        } finally {
//            lock.unlock();
//        }
    }

    @Override
    public void onPasarJugadores(Message mensaje) {

    }

    @Override
    public void onPasarCambios(Message mensaje) {

        lock.lock();
        try {
            String codigoSala = mensaje.getContent().getCodigoSala();

            if (codigoSala == null || !rooms.containsKey(codigoSala)) {
                System.out.println("La sala " + codigoSala + " no existe.");
                return;
            }

            Sala sala = rooms.get(codigoSala);

            // Actualizar el estado del tablero en la sala
            sala.setTablero(mensaje.getContent().getEstadoTablero());

            // Propagar los cambios a todos los jugadores de la sala
            sala.getJugadores().forEach(cliente -> {
                if (!cliente.getJugador().equals(mensaje.getSender())) {
                    cliente.sendMessage(mensaje);
                }
            });

            System.out.println("Mensaje enviado a los demás jugadores en la sala: " + codigoSala);

        } finally {
            lock.unlock();
        }

//        String codigoSala = mensaje.getContent().getCodigoSala();
//
//        if (codigoSala == null) {
//            System.out.println("El cliente no está en ninguna sala.");
//            return;
//        }
//
//        // Enviar el mensaje a los demás jugadores en la sala
//        List<ClientThread> clientesEnSala = rooms.get(codigoSala);
//        clientesEnSala.forEach(cliente -> {
//            if (!cliente.getJugador().equals(mensaje.getSender())) {
//                cliente.sendMessage(mensaje);
//            }
//        });
//
//        System.out.println("Mensaje enviado a los demás jugadores en la sala: " + codigoSala);
    }

}
