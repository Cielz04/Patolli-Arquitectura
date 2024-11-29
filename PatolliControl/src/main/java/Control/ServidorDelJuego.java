package Control;

import com.chat.tcpcommons.ClientThread;
import entidades.EstadoDelJuego;
import entidades.Tablero;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageType;
import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import entidades.Jugador;
import java.awt.Color;
import java.io.IOException;
import java.util.Observer;
import java.awt.Color;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class ServidorDelJuego extends Observable implements Runnable {

    private ServerSocket server;
    private List<ClientThread> clients;
    private EstadoDelJuego gameState;
    private int port = 1002;
    private boolean running;
    private static final Color[] COLORES_PREDEFINIDOS = {
        Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
        Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK
    };
    private int indiceColor = 0;
    private static ServidorDelJuego instanciaUnica;
    private int jugadoresConectados = 0;

    private IChatLogger logger = LoggerFactory.getLogger(ServidorDelJuego.class);

    public ServidorDelJuego() {
        this.clients = new ArrayList<>();
        this.gameState = new EstadoDelJuego(
                new ArrayList<>(),
                new Tablero(),
                0,
                false,
                0
        );
        this.running = true;
    }

    @Override
    public void run() {
    try {
        logger.info("Servidor en espera de conexiones...");
        while (running) {
            Socket clientSocket = server.accept(); // Espera conexión de un cliente
            ClientThread client = new ClientThread(clientSocket, gameState);

            // Crear un nuevo jugador y asignar color automáticamente
            Jugador nuevoJugador = new Jugador("Jugador" + (clients.size() + 1), asignarColor());
            client.setJugador(nuevoJugador); // Configura el jugador en el cliente
            gameState.getJugadores().add(nuevoJugador); // Agrega el jugador al estado del juego

            // Agregar al jugador a la lista de clientes
            clients.add(client);
            new Thread(client).start(); // Inicia el hilo del cliente

            logger.info("Nuevo jugador conectado: " + nuevoJugador.getNombre() + " con color " + nuevoJugador.getColor());

            // Inicia partida si hay suficientes jugadores
            if (!gameState.isPartidaIniciada() && gameState.getJugadores().size() >= 2) {
                iniciarPartida();
            }
        }
    } catch (IOException e) {
        logger.error("Error al aceptar conexión: " + e.getMessage());
    }
}

    public void init() {
        try {
            server = new ServerSocket(port);
            new Thread(this).start();
            logger.info("Servidor iniciado en el puerto: " + port);
        } catch (IOException e) {
            logger.error("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    public void detener() {
        running = false;
        try {
            server.close();
        } catch (IOException e) {
            logger.error("Error al detener el servidor: " + e.getMessage());
        }
    }

    private void iniciarPartida() {
        gameState.setPartidaIniciada(true);
        gameState.iniciarTurnos(); // Asigna el primer jugador en turno
        logger.info("La partida ha comenzado. Turno de: " + gameState.getJugadorEnTurno().getNombre());
        notificarEstadoJuego();
    }

    public void siguienteTurno() {
        gameState.siguienteTurno();
        logger.info("Nuevo turno. Turno de: " + gameState.getJugadorEnTurno().getNombre());
        notificarEstadoJuego();
    }

    private void notificarEstadoJuego() {
        // Enviar el estado del juego actualizado a todos los clientes
        for (ClientThread client : clients) {
            client.enviarEstadoJuego(gameState);
        }
    }

    public static ServidorDelJuego getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new ServidorDelJuego();
            instanciaUnica.init(); // Inicializa el servidor si no está corriendo
            
        }
        return instanciaUnica;
    }

    public static boolean isRunning() {
    return instanciaUnica != null || isPortInUse(1002);
}

    protected Color asignarColor() {
        jugadoresConectados++;
        switch (jugadoresConectados) {
            case 1: return Color.RED;
            case 2: return Color.BLUE;
            case 3: return Color.GREEN;
            case 4: return Color.YELLOW;
            default: return null;
        }
    }

    public Color obtenerColorParaJugador() {
        return asignarColor();
    }
    
    public static boolean isPortInUse(int port) {
    try (ServerSocket socket = new ServerSocket(port)) {
        return false;
    } catch (IOException e) {
        return true;
    }
}

    public EstadoDelJuego getGameState() {
        return gameState;
    }

    public void setGameState(EstadoDelJuego gameState) {
        this.gameState = gameState;
    }
    
    
    
    
    
    
}
