package Control;

import com.chat.tcpcommons.ClientThread;
import com.chat.tcpcommons.IConnection;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Connection implements IObserver, IConnection, Serializable {

    private ClientThread client;
    private Jugador jugador;
    private int port;
    private String host;
    private final IChatLogger logger = LoggerFactory.getLogger(Connection.class);

    public Connection(String host, int port, String nombreJugador) {
        this.host = host;
        this.port = port;
        this.jugador = new Jugador(nombreJugador);
    }

    public void init() {
        try {
            // Verificar si el servidor está corriendo
            if (!ServidorDelJuego.isRunning()) {
                logger.info("Servidor no está corriendo. Inicializando...");
                ServidorDelJuego.getInstance(); // Inicializa el servidor
            }

            // Conectar al servidor
            Socket clientSocket = new Socket(host, port);
            client = new ClientThread(clientSocket, new EstadoDelJuego());
            client.setJugador(jugador);
            Thread chatThread = new Thread(client);
            chatThread.start();
            client.subscribe(this); // Subscribirse para actualizaciones del servidor

            logger.info("Jugador conectado: " + jugador.getNombre());
        } catch (IOException ex) {
            logger.error(String.format("Error de conexión: %s", ex.getMessage()));
            System.exit(0); // Cierra la aplicación si no puede conectarse
        }
    }

    @Override
    public void onUpdate(Object obj) {
        // Manejar actualizaciones del servidor
    }
    
    
}
    
