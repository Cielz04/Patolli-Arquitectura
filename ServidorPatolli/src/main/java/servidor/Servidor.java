package servidor;

import PatolliCliente.ClientThread;
import PatolliCliente.IConnection;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.TemplateConnection;
import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import java.util.Observable;

/**
 * Esta clase es responsable de gestionar la comunicación entre el servidor y
 * los clientes, procesando los mensajes que los clientes envían y coordinando
 * la lógica del juego de Patolli. La clase extiende {@link Observable} para
 * permitir la notificación a los observadores y se integra con el patrón
 * {@link TemplateConnection} para la conexión con los clientes. Además,
 * implementa la interfaz {@link IObserver} para recibir actualizaciones de los
 * mensajes.
 *
 * La clase delega el procesamiento real de los mensajes a
 * {@link ControlMessage}, que maneja las operaciones específicas del juego,
 * como la conexión de jugadores, la actualización de tableros, y otros eventos
 * relacionados con el juego.
 *
 * Algunos métodos, como los de la interfaz {@link TemplateConnection}, están
 * definidos pero no implementados en esta clase.
 */
public class Servidor extends Observable implements TemplateConnection, IObserver {

    private final ControlMessage controlMensajes;

    /**
     * Constructor de la clase {@link Servidor}. Inicializa una nueva instancia
     * de {@link ControlMessage} para gestionar los mensajes y la lógica del
     * juego.
     */
    public Servidor() {
        this.controlMensajes = new ControlMessage();
    }

    /**
     * Inicializa el servidor, delegando la responsabilidad a
     * {@link ControlMessage}.
     */
    public void init() {
        controlMensajes.init();
    }

    /**
     * Procesa un mensaje recibido, delegando la tarea a {@link ControlMessage}.
     *
     * @param message el mensaje que se va a procesar.
     */
    public void proccessMessage(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Método que se invoca cuando hay una actualización, en este caso un
     * mensaje. El mensaje recibido es procesado por el servidor.
     *
     * @param obj el objeto que representa la actualización (se espera que sea
     * de tipo {@link Message}).
     */
    @Override
    public void onUpdate(Object obj) {
        if (obj instanceof Message) {
            proccessMessage((Message) obj);
        }
    }

    /**
     * Desconecta al servidor de manera opcional. Actualmente no implementado.
     */
    public void disconnect() {
        // Opcional: Implementar lógica adicional si es necesario
    }

    /**
     * Maneja el mensaje de tipo CONECTARSE, delegando el procesamiento a
     * {@link ControlMessage}.
     *
     * @param message el mensaje de conexión.
     */
    public void onConectarse(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Maneja el mensaje de tipo DESCONECTARSE, delegando el procesamiento a
     * {@link ControlMessage}.
     *
     * @param message el mensaje de desconexión.
     */
    public void onDesconectarse(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Maneja el mensaje de tipo UNIRSE_SALA, delegando el procesamiento a
     * {@link ControlMessage}.
     *
     * @param message el mensaje de unión a la sala.
     */
    public void onUnirseSala(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Maneja el mensaje de tipo CONFIGURAR_TABLERO, delegando el procesamiento
     * a {@link ControlMessage}.
     *
     * @param message el mensaje para configurar el tablero.
     */
    public void onConfigurarTablero(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Maneja el mensaje de tipo ACTUALIZAR_TABLERO, delegando el procesamiento
     * a {@link ControlMessage}.
     *
     * @param message el mensaje para actualizar el tablero.
     */
    public void onActualizarTablero(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Maneja el mensaje de tipo ERROR, delegando el procesamiento a
     * {@link ControlMessage}.
     *
     * @param message el mensaje de error.
     */
    public void onError(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    /**
     * Método no implementado de la interfaz {@link TemplateConnection}, que
     * maneja el evento de conectar un cliente. Lanzará una
     * {@link UnsupportedOperationException}.
     *
     * @param message el mensaje de conexión.
     * @param client el cliente que se conecta.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void onConectarse(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método no implementado de la interfaz {@link TemplateConnection}, que
     * maneja el evento de desconectar un cliente. Lanzará una
     * {@link UnsupportedOperationException}.
     *
     * @param message el mensaje de desconexión.
     * @param client el cliente que se desconecta.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void onDesconectarse(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método no implementado de la interfaz {@link TemplateConnection}, que
     * maneja el evento de unirse un cliente a la sala. Lanzará una
     * {@link UnsupportedOperationException}.
     *
     * @param message el mensaje para unirse a la sala.
     * @param client el cliente que se une a la sala.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void onUnirseSala(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método no implementado de la interfaz {@link TemplateConnection}, que
     * maneja el evento de configurar el tablero para un cliente. Lanzará una
     * {@link UnsupportedOperationException}.
     *
     * @param message el mensaje para configurar el tablero.
     * @param client el cliente que configura el tablero.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void onConfigurarTablero(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método no implementado de la interfaz {@link TemplateConnection}, que
     * maneja el evento de actualizar el tablero para un cliente. Lanzará una
     * {@link UnsupportedOperationException}.
     *
     * @param message el mensaje para actualizar el tablero.
     * @param client el cliente que actualiza el tablero.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void onActualizarTablero(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método no implementado de la interfaz {@link TemplateConnection}, que
     * maneja el evento de recibir un error de un cliente. Lanzará una
     * {@link UnsupportedOperationException}.
     *
     * @param message el mensaje de error.
     * @param client el cliente que envía el error.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void onError(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
