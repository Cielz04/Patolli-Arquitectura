package servidor;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.ClientThread;
import com.chat.tcpcommons.IConnection;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import com.chat.tcpcommons.TemplateConnection;
import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import entidades.Jugador;
import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import utileria.Utileria;

/**
 *
 * @author Hector Espinoza
 */
public class ClientConnection implements TemplateConnection, IObserver, IConnection, Serializable{
    
    private ClientThread cliente; 
    private final Jugador jugador;
    private final int PORT=50064;
    private final String HOST="localhost";
    private final FrmInicio frameInicio;

    private final IChatLogger logger = LoggerFactory.getLogger(ClientConnection.class);
    
    ReentrantLock lock = new ReentrantLock();

    public ClientConnection(FrmInicio frameInicio) {
        this.frameInicio=frameInicio;
        jugador = new Jugador();
        jugador.setNombre("Jugador 1");
    }

    @Override
    public void init() {
        try {
            Socket clientSocket = new Socket(HOST, PORT);
            
            cliente = new ClientThread(clientSocket);
            cliente.setJugador(jugador);
            
            Thread patolliThread = new Thread(cliente);
            patolliThread.start();
            
            cliente.subscribe(this); 
            
            MessageBody cuerpo = new MessageBody();
            cuerpo.setCodigoSala(jugador.getNombre());

            var connectionMessage = new Message.Builder().sender(jugador).messageType(MessageType.CONECTARSE).body(cuerpo).build();
            sendMessage(connectionMessage);
            
            System.out.println("Usuario conectado");
        } catch (IOException ex) {
            logger.error(String.format(" %s : el servidor no responde", ex.getMessage()));
            System.exit(0);
        }
    }

    @Override
    public void onUpdate(Object obj) {
        Message mensaje=(Message)obj;
        System.out.println("Mensaje recibido: "+ mensaje.getMessageType());
        proccessMessage(mensaje);
    }

    @Override
    public void sendMessage(Message mensaje) {
        mensaje.setSender(jugador);
        cliente.sendMessage(mensaje);
    }

    @Override
    public void disconnect() {
        cliente.disconnect();
    }

    @Override
    public void onDisconnect(Message mensaje) {
//        frameInicio.onDesconectarse(mensaje);
    }
    
    @Override
    public void onConectarse(Message mensaje) {
//        frameInicio.onConectarse(mensaje);
    }
    
    @Override
    public void onUnirseSala(Message mensaje) {
//        frameInicio.onUnirseSala(mensaje);
    }
    
    @Override
    public void onCrearSala(Message mensaje) {
//        frameInicio.onCrearSala(mensaje);
    }
    
    @Override
    public void onPasarOpciones(Message mensaje) {
//        frameInicio.onPasarOpciones(mensaje);
    }
    
    @Override
    public void onPasarJugadores(Message mensaje) {
//        frameInicio.onPasarJugadores(mensaje);
    }

    @Override
    public void onPasarCambios(Message mensaje) {
//        frameInicio.onPasarCambios(mensaje);
    }
    
    
}
