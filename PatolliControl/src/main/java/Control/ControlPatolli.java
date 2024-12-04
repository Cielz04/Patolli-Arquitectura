package Control;

import Pantallas.FrmInicio;
import Pantallas.FrmTablero;

import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import java.io.IOException;
import tablero.Tablero;

/**
 *
 * @author Hector Espinoza
 */
public class ControlPatolli implements IControlPatolli {
    
    private static ControlPatolli instancia;
    private Partida partida;

    private ControlPatolli() {
        partida = new Partida();
    }

    public static ControlPatolli getInstance() {
        if (instancia == null) {
            instancia = new ControlPatolli();
        }
        return instancia;
    }

    @Override
    public void conectarse(FrmInicio frameInicio) {
        partida.conectarse(frameInicio);
    }

    @Override
    public void desconectar(String codigoSala, int miJugador) {
        partida.desconectar(codigoSala);
    }

    @Override
    public void enviarMensaje(Message mensaje) {
        partida.enviarMensaje(mensaje);
    }

    // Método para obtener el tablero
    public Tablero getTablero() {
        return partida.getTablero();
    }

    // Método para inicializar el tablero
    public void inicializarTablero(Tablero tablero) {
        partida.inicializarTablero(tablero);
    }

    // Método para recibir mensajes
    public Message recibirMensaje() throws IOException {
        System.out.println();
        return partida.recibirMensaje(); // Delegar la recepción a la clase Partida
    }

//    public void iniciarObservador() {
//        // Hacer que ControlPatolli sea un observador de ClientThread
//        partida.getCliente().subscribe(this);
//    }

//    @Override
    public void onUpdate(Object obj) {
        // Cuando llega un mensaje, se maneja aquí
        Message mensaje = (Message) obj;
        System.out.println("Mensaje recibido: " + mensaje.getMessageType());
        proccessMessage(mensaje);
    }

    // Procesar el mensaje recibido
    private void proccessMessage(Message mensaje) {
        switch (mensaje.getMessageType()) {
            case ESTADO_TABLERO:
                // Aquí procesas el estado del tablero
                manejarEstadoTablero(mensaje);
                break;
            // Otros tipos de mensajes pueden ser gestionados aquí
            default:
                System.out.println("Mensaje no manejado: " + mensaje.getMessageType());
        }
    }

    private void manejarEstadoTablero(Message mensaje) {
        if (mensaje.getMessageType() == MessageType.ESTADO_TABLERO) {
            MessageBody body = mensaje.getContent();
            Tablero tablero = body.getEstadoTablero();
            // Aquí inicializamos el tablero con el estado recibido
            this.getTablero().setCantidadCasillasAspa(tablero.getCantidadCasillasAspa());
            // Inicializar la interfaz gráfica del tablero
            FrmTablero tableroFrame = new FrmTablero(ControlPatolli.getInstance(), body.getCodigoSala());
            tableroFrame.manejarEstadoTablero(mensaje); // Llamar a la inicialización del tablero
            tableroFrame.setVisible(true);
        }
    }
    
    

//////////////////    private static ControlPatolli instancia;
//////////////////    private Partida partida;
//////////////////
//////////////////    private ControlPatolli() {
//////////////////        partida = new Partida();
//////////////////    }
//////////////////
//////////////////    public static ControlPatolli getInstance() {
//////////////////        if (instancia == null) {
//////////////////            instancia = new ControlPatolli();
//////////////////        }
//////////////////        return instancia;
//////////////////    }
//////////////////
//////////////////    @Override
//////////////////    public void conectarse(FrmInicio frameInicio) {
//////////////////        partida.conectarse(frameInicio);
//////////////////    }
//////////////////
//////////////////    @Override
//////////////////    public void desconectar(String codigoSala, int miJugador) {
//////////////////        partida.desconectar(codigoSala);
//////////////////    }
//////////////////
//////////////////    @Override
//////////////////    public void enviarMensaje(Message mensaje) {
//////////////////        partida.enviarMensaje(mensaje);
//////////////////    }
//////////////////
//////////////////    // Método para obtener el tablero
//////////////////    public Tablero getTablero() {
//////////////////        return partida.getTablero();
//////////////////    }
//////////////////
//////////////////    // Método para inicializar el tablero
//////////////////    public void inicializarTablero(Tablero tablero) {
//////////////////        partida.inicializarTablero(tablero);
//////////////////    }
//////////////////
//////////////////    public Message recibirMensaje() {
//////////////////        return partida.recibirMensaje(); // Lógica de recepción desde el servidor
//////////////////    }

//    static ControlPatolli controlSingleTon;
//    Partida partida;
//    Juego juego = Juego.getInstance();
//    public boolean host;
//    FrmInicio frameInicio;
//    private int jugadores;
//    private ClientThread hiloCliente;
//
//    public ControlPatolli(FrmInicio frameInicio) throws IOException {
//        this.frameInicio = frameInicio;
//        partida = new Partida();
//        hiloCliente = new ClientThread(new Socket("localhost", 50064));
//    }
//
////    public void setJugadorClient (String nombre){
////        hiloCliente.setJugador(new Jugador("nombre", Color.RED));
////    }
//    public ControlPatolli() {
//        partida = new Partida();
//        try {
//            hiloCliente = new ClientThread(new Socket("localhost", 50064));
//        } catch (IOException ex) {
//            Logger.getLogger(ControlPatolli.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void crearSala() {
//        // Crear el tablero y el mensaje
//        MessageBody mb = new MessageBody();
//        mb.setCodigoSala("000");
//        mb.setJugador(1);
//        mb.setTamaño(getTablero().getCantidadCasillasAspa());
//        mb.setJugadores(getTablero().getCanJugadores());
//
//        Jugador nJugador = new Jugador("Jugador 1", Color.RED);
//        Message mensaje = new Message(mb, nJugador, nJugador, com.chat.tcpcommons.MessageType.CREAR_SALA);
//        Message mensajeConectarse = new Message(mb, nJugador, nJugador, com.chat.tcpcommons.MessageType.CONECTARSE);
//
//        // Inicializar el servidor
//        ControlMessage cm = new ControlMessage();
//        
//            // Simular la conexión de un cliente
//            hiloCliente.setJugador(nJugador);
//
//            // Agregar el cliente a la sala de espera
//            cm.rooms.get("sala de espera").add(hiloCliente);
//            hiloCliente.subscribe(cm); // Suscribir el cliente al observable
//
//            // Simular los mensajes
////            cm.onCrearSala(mensaje);
////            cm.onConectarse(mensajeConectarse);
//            hiloCliente.sendMessage(mensaje);
//           
////            cliente.sendMessage(mensajeConectarse);
//
//        
//    }
//
//    public MessageBody unirseSala(String codigoPartida) {
//        // Crear el mensaje base para unirse a la sala
//        MessageBody mb = new MessageBody();
//        mb.setCodigoSala(codigoPartida);
//        mb.setJugador(4);
//
//        // Crear el jugador que se va a unir
//        Jugador nJugador = new Jugador("Jugador 3", Color.BLUE); // Cambia el nombre y el color según corresponda
//
////        Message mensajeConectarse = new Message(mb, nJugador, nJugador, com.chat.tcpcommons.MessageType.CONECTARSE);
//        // Crear el mensaje de unirse a sala
//        Message mensajeUnirse = new Message(mb, nJugador, nJugador, com.chat.tcpcommons.MessageType.UNIRSE_SALA);
//        Message mensajeConectarse = new Message(mb, nJugador, nJugador, com.chat.tcpcommons.MessageType.CONECTARSE);
//        // Inicializar la lógica de mensajes
//        ControlMessage cm = new ControlMessage();
//       
//            // Simular la conexión de un cliente
//            hiloCliente.setJugador(nJugador);
//
//            // Agregar el cliente a la sala de espera
//            cm.rooms.get("sala de espera").add(hiloCliente);
//            hiloCliente.subscribe(cm); // Suscribir el cliente al observable
//
//            // Procesar el mensaje de unirse a sala
////            cm.onConectarse(mensajeConectarse);
////            cliente.sendMessage(mensajeConectarse);
//            hiloCliente.sendMessage(mensajeConectarse);
//            hiloCliente.sendMessage(mensajeUnirse);
//        
//            return mb;
//        
//    }
//    
//    
//
//    public void configurarJugadores(int canJugadores) {
//        juego.setCanJugadores(canJugadores);
//
//        juego.addJugador();
//    }
//
//    public void setCantidadCasillas(int casillas) {
//        juego.setNumCasillasAspa(casillas);
//    }
//
//    public void setCantidadFichas(int canFichas) {
//        juego.setCantFichas(canFichas);
//    }
//
//    public void setApuesta(int apuesta) {
//        juego.setApuesta(apuesta);
//    }
//
//    public int getCasillasAspas() {
//        return juego.getNumCasillasAspa();
//    }
//
//    /**
//     * MODIFICAR NO SE DEBE TENER ACCESSO DIRECTO AL TABLERO, SOLO A JUEGO
//     *
//     * @param tablero
//     */
//    public void setTablero(Tablero tablero) {
//        juego.setTablero(tablero);
//    }
//
//    public Color getColorTurnoJugador() {
//        return juego.getJugadores().get(1).getColor();
//    }
//
//    public Tablero getTablero() {
//        return juego.getTablero();
//    }
//
//    public static ControlPatolli getInstance() {
//        if (controlSingleTon == null) {
//            controlSingleTon = new ControlPatolli();
//        }
//        return controlSingleTon;
//    }
//
//    public int getTurno() {
//        return juego.getTurno();
//    }
//
//    public void cambiarTurno() {
//        juego.setTurno();
//    }
//
//    public Jugador getJugadorTurno(int turno) {
//        return juego.getJugadores().get(turno);
//    }
//
//    @Override
//    public void conectarse() {
//        partida.conectarse(frameInicio);
//    }
//
//    @Override
//    public void enviarMensaje(Message mensaje) {
//        partida.enviarMensaje(mensaje);
//    }
//
//    @Override
//    public void desconectar(String codigoSala, int miJugador) {
//        partida.desconectar(codigoSala, miJugador);
//    }
//
//    public int getJugadores() {
//        return jugadores;
//    }
//
//    public void setJugadores(int jugadores) {
//        this.jugadores = jugadores;
//    }
}
