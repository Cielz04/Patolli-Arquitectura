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
 *
 * @author esmeraldamolinaestrada
 */
public class Servidor extends Observable implements TemplateConnection, IObserver {

    private final ControlMessage controlMensajes;

    public Servidor() {
        this.controlMensajes = new ControlMessage();
    }

    public void init() {
        controlMensajes.init();
    }

    public void proccessMessage(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    @Override
    public void onUpdate(Object obj) {
        if (obj instanceof Message) {
            proccessMessage((Message) obj);
        }
    }

    public void disconnect() {
        // Opcional: Implementar lógica adicional si es necesario
    }

    public void onConectarse(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    public void onDesconectarse(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    public void onUnirseSala(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    public void onConfigurarTablero(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    public void onActualizarTablero(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    public void onError(Message message) {
        controlMensajes.procesarMensaje(message);
    }

    @Override
    public void onConectarse(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onDesconectarse(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onUnirseSala(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onConfigurarTablero(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onActualizarTablero(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onError(Message message, ClientThread client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
