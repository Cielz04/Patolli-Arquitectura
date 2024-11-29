package servidor;

import com.chat.tcpcommons.ClientThread;
import com.chat.tcpcommons.IConnection;
import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.TemplateConnection;
import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tablero.Tablero;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Servidor extends Observable implements IConnection, IObserver, TemplateConnection {

    private ControlMessage controlMensajes;
    ReentrantLock lock = new ReentrantLock();

    private final IChatLogger logger = LoggerFactory.getLogger(Servidor.class);

    public Servidor() {
    }

    
    @Override
    public void init() {
        try {
            System.out.println("Iniciando el servidor...");
            controlMensajes = new ControlMessage();
            controlMensajes.init();
            controlMensajes.subscribe(this);
            System.out.println("Servidor iniciado");
        } catch (Exception ex) {
            logger.error(String.format("Error al iniciar el servidor: ", ex.getMessage()));
        }
    }

    @Override
    public void sendMessage(Message message) {
        controlMensajes.proccessMessage(message);
    }

    @Override
    public void onUpdate(Object obj) {
        lock.lock();
        try {
            proccessMessage((Message) obj);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onConectarse(Message mensaje) {
        controlMensajes.onConectarse(mensaje);
    }

    @Override
    public void onCrearSala(Message mensaje) {
        controlMensajes.onCrearSala(mensaje);
    }

    @Override
    public void onUnirseSala(Message mensaje) {
        controlMensajes.onUnirseSala(mensaje);
    }

    @Override
    public void onPasarOpciones(Message mensaje) {
        controlMensajes.onPasarOpciones(mensaje);
    }

    @Override
    public void onPasarJugadores(Message mensaje) {
        controlMensajes.onPasarJugadores(mensaje);
    }

    @Override
    public void onPasarCambios(Message mensaje) {
        controlMensajes.onPasarCambios(mensaje);
    }

//    private ServerSocket server;
//    private List<ClientThread> clients;
//    private EstadoDelJuego gameState;
//    private int port = 50065;
//    private boolean running;
//    private static Servidor instancia;
//    private String codigoPartida = "HolaMundo2307";
//    private Consumer<Tablero> onPartidaCreada;
//    private boolean partidaIniciada = false;
//    private List<ClienteHandler> clientes;
//    private Socket servidorSocket;
//
////    private static final Color[] COLORES_PREDEFINIDOS = {
////        Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
////        Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK
////    };
//    private int indiceColor = 0;
//    private static Servidor instanciaUnica;
//    private int jugadoresConectados = 0;
//
//    private IChatLogger logger = LoggerFactory.getLogger(Servidor.class);
//
////    public Servidor() {
////        try {
////            this.server = new ServerSocket(port);
////            System.out.println("Servidor iniciado, esperando conexiones...");
////            this.gameState = new EstadoDelJuego();
////            this.clients = new ArrayList<>();
////        } catch (IOException e) {
////            e.printStackTrace();
////            throw new RuntimeException("No se pudo inicializar el ServerSocket.", e);
////        }
////    }
//    
//    public Servidor() {
//        clientes = new ArrayList<>();
//        this.gameState = new EstadoDelJuego();
//        this.clients = new ArrayList<>();
//    }
//
//    public void iniciarServidor() throws IOException {
//        if (isPortInUse(port)) {
//            JOptionPane.showMessageDialog(null, "El puerto " + port + " ya está en uso.");
//            return;  // No intentamos iniciar el servidor si el puerto ya está en uso
//        }
//
//        if (server == null || server.isClosed()) {
//            server = new ServerSocket(port);
//            server.setReuseAddress(true);
//            System.out.println("Servidor escuchando en puerto " + port);
//        } else {
//            System.out.println("Servidor ya está en ejecución.");
//        }
//
//        // Aquí puedes manejar la lógica de aceptación de clientes
//        while (true) {
//            Socket clienteSocket = server.accept();
//            System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());
//            // Lógica para manejar la conexión con el cliente
//        }
////        try {
////            if (server == null || server.isClosed()) {
////                server = new ServerSocket(port);
////                System.out.println("Servidor iniciado en el puerto: " + port);
////            }
////            new Thread(this).start(); // Inicia el hilo del servidor
////        } catch (IOException e) {
////            System.err.println("Error al iniciar el servidor: " + e.getMessage());
////        }
//    }
//
//    public static class ClienteHandler implements Runnable {
//
//        private Socket socket;
//        private BufferedReader reader;
//        private BufferedWriter writer;
//
//        public ClienteHandler(Socket socket) {
//            this.socket = socket;
//            try {
//                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            } catch (IOException e) {
//                System.err.println("Error al crear flujos de comunicación: " + e.getMessage());
//            }
//        }
//
//        @Override
//        public void run() {
//            try {
//                String mensaje;
//                while ((mensaje = reader.readLine()) != null) {
//                    System.out.println("Mensaje del cliente: " + mensaje);
//                    writer.write("Respuesta del servidor: " + mensaje);
//                    writer.newLine();
//                    writer.flush();
//                }
//            } catch (IOException e) {
//                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
//            } finally {
//                try {
//                    reader.close();
//                    writer.close();
//                    socket.close();
//                } catch (IOException e) {
//                    System.err.println("Error al cerrar los recursos: " + e.getMessage());
//                }
//            }
//        }
//    }
//
//    public void escucharConexiones() {
//        try {
//            // Verifica que el servidor no sea null antes de llamar accept()
//            if (server != null) {
//                Socket cliente = server.accept(); // Espera por una conexión de cliente
//                System.out.println("Cliente conectado: " + cliente.getInetAddress());
//                // Manejo del cliente
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Manejo de errores
//        }
//    }
//
//    public static Servidor getInstance() {
////        if (instancia == null) {
////            instancia = new Servidor(); // Solo se crea una vez
////        } else if (instancia.getGameState() == null) {
////            instancia.setGameState(new EstadoDelJuego());
////        }
////        return instancia;
//        if (instancia == null) {
//            instancia = new Servidor(); // Solo se crea una vez
//        } else if (instancia.getGameState() == null) {
//            instancia.setGameState(new EstadoDelJuego());
//        }
//        return instancia;
//    }
//
//    @Override
//    public void run() {
//        try {
//            logger.info("Servidor en espera de conexiones...");
//            running = true;
//            while (running) {
//                try {
//                    Socket clientSocket = server.accept();
//                    manejarConexionCliente(clientSocket);
//                } catch (SocketException e) {
//                    if (!running) {
//                        logger.info("Servidor detenido correctamente.");
//                    } else {
//                        logger.error("SocketException: " + e.getMessage());
//                    }
//                } catch (IOException e) {
//                    logger.error("Error de E/S al aceptar conexión: " + e.getMessage());
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Error crítico en el servidor: " + e.getMessage());
//        } finally {
//            detener();
//        }
//    }
//
//    private void manejarConexionCliente(Socket clientSocket) throws IOException {
//        synchronized (gameState) {
//            if (jugadoresConectados >= 4) {
//                logger.warning("Se ha alcanzado el límite máximo de jugadores.");
//                clientSocket.close();
//                return;
//            }
//        }
//
//        ClientThread clientThread = new ClientThread(clientSocket);
//
//        synchronized (gameState) {
//            Jugador nuevoJugador = new Jugador("Jugador" + (gameState.getJugadores().size() + 1));
//            clientThread.setJugador(nuevoJugador);
//            gameState.getJugadores().add(nuevoJugador);
//        }
//
//        synchronized (clients) {
//            clients.add(clientThread);
//        }
//        new Thread(clientThread).start();
//
//        jugadoresConectados++; // Incrementa el contador de jugadores conectados
//        logger.info("Nuevo jugador conectado.");
//    }
//
//    public static boolean isRunning() {
//        return instancia != null && !instancia.server.isClosed();
//    }
//
//    public boolean isServerInitialized() {
//        return this.server != null && !this.server.isClosed();
//    }
//
//    public void detener() {
//        running = false;
//        try {
//            if (server != null && !server.isClosed()) {
//                server.close();
//                logger.info("Servidor detenido correctamente.");
//            }
//        } catch (IOException e) {
//            logger.error("Error al detener el servidor: " + e.getMessage());
//        }
//        
//    }
//
//    public static boolean isPortInUse(int port) {
//        try (ServerSocket socket = new ServerSocket(port)) {
//            return false;  // El puerto está libre
//        } catch (IOException e) {
//            return true;  // El puerto está en uso
//        }
//
//    }
//
//    private void iniciarPartida() {
//        if (gameState.getJugadores().size() < 2) {
//            System.out.println("Se requieren al menos 2 jugadores");
//            return;
//        }
//        partidaIniciada = true;
//        gameState.setPartidaIniciada(true);
//        System.out.println("La partida comenzó");
//    }
//
//    protected Color asignarColor() {
//        if (jugadoresConectados > 0 && jugadoresConectados <= COLORES_PREDEFINIDOS.length) {
//            return COLORES_PREDEFINIDOS[jugadoresConectados - 1];
//        }
//        // Si se exceden los colores predefinidos, asigna colores adicionales dinámicamente
//        return new Color((int) (Math.random() * 0x1000000)); // Color aleatorio
//    }
//
//    public Color obtenerColorParaJugador() {
//        return asignarColor();
//    }
//
//    public void crearPartida() {
//
//        if (partidaIniciada) {
//            throw new IllegalStateException("Ya existe una partida activa.");
//        }
//        // Verificar si el servidor no está inicializado o si está cerrado
//        if (server == null || server.isClosed()) {
//            try {
//                // Crear un nuevo ServerSocket en el puerto especificado
//                server = new ServerSocket(port);
//                System.out.println("Servidor iniciado en el puerto: " + port);
//            } catch (IOException e) {
//                throw new RuntimeException("Error al crear la partida.", e);
//            }
//        }
//
//        // Marcar la partida como iniciada
//        partidaIniciada = true;
//        gameState.setPartidaIniciada(true);
//
//        // Crear el tablero y asociarlo al estado del juego
//        Tablero tablero = new Tablero();
//        gameState.setTablero(tablero);
//
//        // Mostrar mensaje de éxito
//        System.out.println("Partida creada exitosamente.");
//    }
//
//    private void validarEstadoDelJuego() {
//        if (gameState == null) {
//            throw new IllegalStateException("El estado del juego no está inicializado.");
//        }
//    }
//
//    public EstadoDelJuego getGameState() {
//        return gameState;
//    }
//
//    public void setGameState(EstadoDelJuego gameState) {
//        if (gameState == null) {
//            throw new IllegalArgumentException("El estado del juego no puede ser null.");
//        }
//        this.gameState = gameState;
//    }
//
//    public boolean unirsePartida(String codigo, Jugador jugador) {
//        synchronized (gameState) {
//            if (!codigo.equals(codigoPartida)) {
//                System.out.println("Código incorrecto");
//                return false;
//            }
//            if (partidaIniciada) {
//                System.out.println("La partida ya está iniciada");
//                return false;
//            }
//            if (gameState.getJugadores().size() >= 4) {
//                System.out.println("La partida ya está llena");
//                return false;
//            }
//
//            gameState.getJugadores().add(jugador);
//            System.out.println("Jugador añadido: " + jugador.getNombre());
//            return true;
//        }
//    }
//
//    public String getCodigoPartida() {
//        return codigoPartida;
//    }
//
//    // Método para iniciar el servidor manualmente
//    public void iniciar() {
//        if (isPortInUse(port)) {
//            logger.info("El puerto " + port + " ya está inicializado.");
//            return;
//        }
//
//        if (isServerInitialized()) {
//            logger.info("El servidor ya está en funcionamiento.");
//            return;
//        }
//
//        try {
//            if (server == null) {
//                this.server = new ServerSocket(port);
//                logger.info("Servidor en espera de conexiones...");
//            }
//        } catch (IOException e) {
//            logger.error("Error al inicializar el servidor: " + e.getMessage());
//        }
//    }
//
//    // Método para desconectar un jugador
//    public void desconectarJugador(ClientThread clientThread) {
//        synchronized (clients) {
//            clients.remove(clientThread);  // Elimina el hilo del cliente
//        }
//        synchronized (gameState) {
//            gameState.getJugadores().remove(clientThread.getJugador());  // Elimina al jugador de la lista
//        }
//        jugadoresConectados--;  // Decrementa el contador de jugadores
//        logger.info("Jugador desconectado.");
//    }
}
