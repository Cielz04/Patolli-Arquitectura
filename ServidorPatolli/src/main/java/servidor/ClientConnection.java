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
public class ClientConnection implements TemplateConnection, IObserver, IConnection, Serializable {

    private ClientThread cliente; // Hilo encargado de recibir los mensajes
    private final Jugador jugador;
    private final int PORT = 50064;
    private final String HOST = "localhost";
    private final FrmInicio frameInicio;

    public ClientConnection(FrmInicio frameInicio) {
        this.frameInicio = frameInicio;
        jugador = new Jugador();
        jugador.setNombre(Utileria.generarNombre()); // Nombre predeterminado
    }

    public void init() {
        try {
            Socket clientSocket = new Socket(HOST, PORT); // Conectar con el servidor
            cliente = new ClientThread(clientSocket); // Crear hilo para la comunicación
            cliente.setJugador(jugador); // Establecer el jugador
            Thread patolliThread = new Thread(cliente);
            patolliThread.start(); // Iniciar el hilo
            cliente.subscribe(this); // Suscribir para recibir notificaciones

            // Enviar el mensaje de conexión al servidor
            MessageBody cuerpo = new MessageBody();
            cuerpo.setCodigoSala("000");
            var connectionMessage = new Message.Builder().sender(jugador).messageType(MessageType.CONECTARSE).body(cuerpo).build();
            sendMessage(connectionMessage);

            System.out.println("Usuario conectado");
        } catch (IOException ex) {
            System.err.println("Error al conectar al servidor: " + ex.getMessage());
            System.exit(0);
        }
    }

    public void sendMessage(Message mensaje) {
        if (cliente != null) {
            cliente.sendMessage(mensaje); // Enviar mensaje a través de ClientThread
        }
    }

    public void disconnect() {
        if (cliente != null) {
            cliente.disconnect(); // Desconectar
        }
    }

    public Message recibirMensaje() throws IOException {
        if (cliente != null) {
            System.out.println(cliente.recibirMensaje().getContent().getTamaño());
            return cliente.recibirMensaje(); // Obtener mensaje desde ClientThread
        }
        return null;
    }

//////    private ClientThread cliente; 
//////    private final Jugador jugador;
//////    private final int PORT=50064;
//////    private final String HOST="localhost";
//////    private final FrmInicio frameInicio;
//////
//////    private final IChatLogger logger = LoggerFactory.getLogger(ClientConnection.class);
//////    
//////    ReentrantLock lock = new ReentrantLock();
//////
//////    public ClientConnection(FrmInicio frameInicio) {
//////        this.frameInicio=frameInicio;
//////        jugador = new Jugador();
//////        jugador.setNombre("Jugador 1");
//////    }
//////
//////    @Override
//////    public void init() {
//////        try {
//////            Socket clientSocket = new Socket(HOST, PORT);
//////            
//////            cliente = new ClientThread(clientSocket);
//////            cliente.setJugador(jugador);
//////            
//////            Thread patolliThread = new Thread(cliente);
//////            patolliThread.start();
//////            
//////            cliente.subscribe(this); 
//////            
//////            MessageBody cuerpo = new MessageBody();
//////            cuerpo.setCodigoSala(jugador.getNombre());
//////
//////            var connectionMessage = new Message.Builder().sender(jugador).messageType(MessageType.CONECTARSE).body(cuerpo).build();
//////            sendMessage(connectionMessage);
//////            
//////            System.out.println("Usuario conectado");
//////        } catch (IOException ex) {
//////            logger.error(String.format(" %s : el servidor no responde", ex.getMessage()));
//////            System.exit(0);
//////        }
//////    }
//////
//////    @Override
//////    public void onUpdate(Object obj) {
//////        Message mensaje=(Message)obj;
//////        System.out.println("Mensaje recibido: "+ mensaje.getMessageType());
//////        proccessMessage(mensaje);
//////    }
//////
//////    @Override
//////    public void sendMessage(Message mensaje) {
//////        mensaje.setSender(jugador);
//////        cliente.sendMessage(mensaje);
//////    }
//////
//////    @Override
//////    public void disconnect() {
//////        cliente.disconnect();
//////    }
//////
//////    @Override
//////    public void onDisconnect(Message mensaje) {
////////        frameInicio.onDesconectarse(mensaje);
//////    }
//////    
//////    @Override
//////    public void onConectarse(Message mensaje) {
////////        frameInicio.onConectarse(mensaje);
//////    }
//////    
//////    @Override
//////    public void onUnirseSala(Message mensaje) {
////////        frameInicio.onUnirseSala(mensaje);
//////    }
//////    
//////    @Override
//////    public void onCrearSala(Message mensaje) {
////////        frameInicio.onCrearSala(mensaje);
//////    }
//////    
//////    @Override
//////    public void onPasarOpciones(Message mensaje) {
////////        frameInicio.onPasarOpciones(mensaje);
//////    }
//////    
//////    @Override
//////    public void onPasarJugadores(Message mensaje) {
////////        frameInicio.onPasarJugadores(mensaje);
//////    }
//////
//////    @Override
//////    public void onPasarCambios(Message mensaje) {
////////        frameInicio.onPasarCambios(mensaje);
//////    }
    @Override
    public void proccessMessage(Message message) {
        TemplateConnection.super.proccessMessage(message); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void onConectarse(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onCrearSala(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onUnirseSala(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onPasarOpciones(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onPasarJugadores(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onPasarCambios(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onDisconnect(Message message) {
        TemplateConnection.super.onDisconnect(message); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void onUpdate(Object message) {
        if (message instanceof Message) {
        Message msg = (Message) message;
        // Lógica para manejar el mensaje recibido
        System.out.println("Mensaje recibido: " + msg);
        
       message = msg;
        // Actualiza el cliente o envía una respuesta
    } else {
        System.out.println("Tipo de mensaje desconocido.");
    }
    }
    
    

}
