package PatolliCliente;

import Pantallas.FrmTablero;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import entidades.Jugador;
import java.net.Socket;
import tablero.Tablero;

public class ClienteControlador implements IObserver {

    private Tablero tableroLocal;
    private FrmTablero tablero;
    private Jugador jugador;
    private ClientThread hiloCliente;
    private boolean isHost;

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
                    System.out.println("Conexión con servidor exitosa!");
                }
                System.out.println("TABLERO DEL SERVIDOR ACTUALIZADO");

                if (!tableroLocal.isSalaEspera()) {
                    // Obtener el número de jugador asignado desde el mensaje
                    int numJugadorAsignado = mensaje.getContent().getNumJugador(); // Asumiendo que el servidor envía el número de jugador
                    tablero.setNumJugadorDesdeServidor(numJugadorAsignado);  // Establece el número de jugador en el tablero

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

    private void manejarUnirseSala(Message mensaje) {
        tableroLocal.actualizarMenosCasillas(mensaje.getContent().getTablero());

        if (tableroLocal != null && !tablero.isInicializar()) {
            int numJugadorAsignado = tableroLocal.getJugadores().size(); // Asigna según los jugadores
            tablero.setNumJugadorDesdeServidor(numJugadorAsignado); // Actualiza en FrmTablero
            tablero.inicializar();
            tablero.setVisible(true);
        }

    }

    private void manejarActualizacionTablero(Message mensaje) {
        tableroLocal.actualizarConMensaje(mensaje);
        if (tableroLocal != null) {
            tablero.setVisible(false);

            // Asegúrate de que el mensaje tenga el valor correcto de jugadorTurno
            int jugadorTurno = mensaje.getContent().getJugadorTurno();
            System.out.println("Jugador en turno recibido: " + jugadorTurno);

            tablero.setJugadorEnTurno(jugadorTurno); // Actualiza el turno en el tablero
            tablero.redibujarTablero(tableroLocal);
            tablero.setVisible(true);
        } else {
            System.err.println("El tablero recibido al unirse a la sala es nulo.");
        }

    }

    public void enviarMensaje(Message mensaje) {
        hiloCliente.sendMessage(mensaje);
    }

    // Método para enviar actualizaciones del tablero local al servidor
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

    public Tablero getTableroLocal() {
        return tableroLocal;
    }

    @Override
    public void onUpdate(Object obj) {
        procesarMensaje((Message) obj);
    }

    public Jugador getJugador() {
        return jugador;
    }
}
