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
    private int tableroActualizaciones = 0;
    

    public ClienteControlador(Jugador jugador) {
        this.jugador = jugador;
        this.tableroLocal = new Tablero();
        this.tablero = FrmTablero.getInstance(tableroLocal, this);

        try {
            // Crear conexión con el servidor y configurar ClientThread
            Socket socket = new Socket("localhost", 50064);
            this.hiloCliente = new ClientThread(socket, jugador);

            // Registrar ClienteControlador como observador del hiloCliente
            hiloCliente.addObserver(this);

            // Iniciar hilo para manejar mensajes del servidor
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

            case ERROR:
                System.err.println("Error recibido: " + mensaje.getContent().getMensaje());
                break;

            default:
                System.out.println("Mensaje no reconocido: " + mensaje.getMessageType());
        }
    }

    private void manejarActualizacionTablero(Message mensaje) {
        if (mensaje.getContent().getEstadoTablero() == null) {
            System.err.println("El mensaje no contiene un tablero válido.");
            return;
        }
        // Actualiza el tablero local
        Tablero tableroRecibido = mensaje.getContent().getEstadoTablero();
        tableroLocal.actualizarConMensaje(tableroRecibido);

        System.out.println("Tablero local actualizado.");
//        tablero.redibujarTablero(tableroLocal);
    }

    public void enviarMensaje(Message mensaje) {
        if (tableroActualizaciones >=2){
            tablero.inicializar();
            tablero.setVisible(true);
        }
        hiloCliente.sendMessage(mensaje);
        tableroActualizaciones++;
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
