package PatolliCliente;

import Pantallas.FrmTablero;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import entidades.Jugador;
import java.net.Socket;
import tablero.Tablero;

/**
 * Clase ClienteControlador que gestiona la lógica del cliente en el juego de
 * Patolli. Se encarga de la comunicación con el servidor y la actualización del
 * estado local del tablero.
 */
public class ClienteControlador implements IObserver {

    private Tablero tableroLocal;
    private FrmTablero tablero;
    private Jugador jugador;
    private ClientThread hiloCliente;
    private boolean isHost;

    /**
     * Constructor de ClienteControlador. Inicializa el cliente, establece la
     * conexión con el servidor y configura los observadores necesarios.
     *
     * @param jugador el jugador asociado a este cliente.
     * @param host indica si este cliente actúa como host del juego.
     */
    public ClienteControlador(Jugador jugador, boolean host) {
        this.jugador = jugador;
        this.tableroLocal = new Tablero();
        this.tablero = FrmTablero.getInstance(tableroLocal, this);
        this.isHost = host;

        try {

            Socket socket = new Socket("localhost", 50064);
            this.hiloCliente = new ClientThread(socket, jugador);
            hiloCliente.addObserver(this);
            new Thread(hiloCliente).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Procesa los mensajes recibidos desde el servidor.
     *
     * @param mensaje el mensaje recibido.
     */
    public void procesarMensaje(Message mensaje) {
        switch (mensaje.getMessageType()) {
            case CONECTARSE:
                System.out.println("Conexión exitosa: " + mensaje.getContent().getMensaje());
                break;

            case DESCONECTARSE:
                System.out.println("El jugador se desconectó: " + mensaje.getContent().getMensaje());
                break;

            case TABLERO_ACTUALIZADO:
                manejarActualizacionTablero(mensaje);
                break;

            case CONFIGURAR_TABLERO:
                if (isHost) {
                    System.out.println("Conexion con servidor exitosa!");
                }
                System.out.println("TABLERO DEL SERVIDOR ACTUALIZADO");

                if (!tableroLocal.isSalaEspera()) {
                    // Obtener el número de jugador del mensaje
                    tablero.setNumJugador(1);
                    tablero.inicializar();
                    tablero.setVisible(true);
                }
                break;
            case UNIRSE_SALA:
                manejarUnirseSala(mensaje);
                break;
            case ERROR:
                System.err.println("Error recibido: " + mensaje.getContent().getMensaje());
                break;

            default:
                System.out.println("Mensaje no reconocido: " + mensaje.getMessageType());
        }
    }

    /**
     * Maneja la lógica para unirse a una sala de juego.
     *
     * @param mensaje el mensaje recibido con información sobre la sala.
     */
    private void manejarUnirseSala(Message mensaje) {
        tableroLocal.actualizarMenosCasillas(mensaje.getContent().getTablero());

        if (tableroLocal != null && !tablero.isInicializar()) {
            int numJugadorAsignado = tableroLocal.getJugadores().size(); // Asigna según los jugadores
            tablero.setNumJugadorDesdeServidor(numJugadorAsignado); // Actualiza en FrmTablero
            tablero.inicializar();
            tablero.setVisible(true);
        }

    }

    /**
     * Maneja la lógica de actualización del tablero en el cliente.
     *
     * @param mensaje el mensaje recibido con los datos actualizados del
     * tablero.
     */
    private void manejarActualizacionTablero(Message mensaje) {

        tableroLocal.actualizarConMensaje(mensaje);
        if (tableroLocal != null) {
            // Asegúrate de que el tablero recibido no sea nulo
            tablero.setVisible(false);
            tablero.setJugadorEnTurno(mensaje.getContent().getJugadorTurno());
            tablero.redibujarTablero(tableroLocal);
            tablero.setVisible(true);
        } else {
            System.err.println("El tablero recibido al unirse a la sala es nulo.");
        }

    }

    /**
     * Envía un mensaje al servidor.
     *
     * @param mensaje el mensaje que se enviará.
     */
    public void enviarMensaje(Message mensaje) {
        hiloCliente.sendMessage(mensaje);
    }

    /**
     * Envía una actualización del tablero local al servidor.
     */
    public void enviarActualizacionTablero() {
        if (tableroLocal == null) {
            System.err.println("El tablero local no está inicializado.");
            return;
        }
        try {
            Message mensaje = new Message.Builder()
                    .messageType(MessageType.TABLERO_ACTUALIZADO)
                    .sender(jugador)
                    .body(new MessageBody("Actualización del tablero", tableroLocal))
                    .build();
            enviarMensaje(mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar actualización del tablero: " + e.getMessage());
        }
//        try {
//            Message mensaje = new Message.Builder()
//                    .messageType(MessageType.TABLERO_ACTUALIZADO)
//                    .sender(jugador)
//                    .body(new MessageBody("Actualización del tablero", tableroLocal))
//                    .build();
//            enviarMensaje(mensaje);
//        } catch (Exception e) {
//            System.err.println("Error al enviar actualización del tablero: " + e.getMessage());
//        }
    }

    /**
     * Devuelve el tablero local.
     *
     * @return el tablero local.
     */
    public Tablero getTableroLocal() {
        return tableroLocal;
    }

    /**
     * Método llamado cuando se recibe una actualización de un observable.
     *
     * @param obj el objeto que contiene la actualización.
     */
    @Override
    public void onUpdate(Object obj) {
        procesarMensaje((Message) obj);
    }

    /**
     * Devuelve el jugador asociado a este cliente.
     *
     * @return el jugador.
     */
    public Jugador getJugador() {
        return jugador;
    }
}
