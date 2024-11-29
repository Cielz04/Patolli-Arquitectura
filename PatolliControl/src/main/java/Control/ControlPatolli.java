package Control;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.Message;
import entidades.Juego;
import entidades.Jugador;
import java.awt.Color;
import tablero.Tablero;

/**
 *
 * @author Hector Espinoza
 */
public class ControlPatolli implements IControlPatolli {

    static ControlPatolli controlSingleTon;
    Partida juego;
    FrmInicio frameInicio;

    public ControlPatolli(FrmInicio frameInicio) {
        this.frameInicio = frameInicio;
        juego = new Partida();
    }

    public ControlPatolli() {
    }

    

    public void configurarJugadores(int canJugadores) {
        Juego.getInstance().setCanJugadores(canJugadores);

        Juego.getInstance().addJugador();
    }

    public void setCantidadCasillas(int casillas) {
        Juego.getInstance().setNumCasillasAspa(casillas);
    }

    public void setCantidadFichas(int canFichas) {
        Juego.getInstance().setCantFichas(canFichas);
    }

    public void setApuesta(int apuesta) {
        Juego.getInstance().setApuesta(apuesta);
    }

    public int getCasillasAspas() {
        return Juego.getInstance().getNumCasillasAspa();
    }

    /**
     * MODIFICAR NO SE DEBE TENER ACCESSO DIRECTO AL TABLERO, SOLO A JUEGO
     *
     * @param tablero
     */
    public void setTablero(Tablero tablero) {
        Juego.getInstance().setTablero(tablero);
    }

    public Color getColorTurnoJugador() {
        return Juego.getInstance().getJugadores().get(1).getColor();
    }

    public Tablero getTablero() {
        return Juego.getInstance().getTablero();
    }

    public static ControlPatolli getInstance() {
        if (controlSingleTon == null) {
            controlSingleTon = new ControlPatolli();
        }
        return controlSingleTon;
    }

    public int getTurno() {
        return Juego.getInstance().getTurno();
    }

    public void cambiarTurno() {
        Juego.getInstance().setTurno();
    }

    public Jugador getJugadorTurno(int turno) {
        return Juego.getInstance().getJugadores().get(turno);
    }

    @Override
    public void conectarse() {
        juego.conectarse(frameInicio);
    }

    @Override
    public void enviarMensaje(Message mensaje) {
        juego.enviarMensaje(mensaje);
    }

    @Override
    public void desconectar(String codigoSala, int miJugador) {
        juego.desconectar(codigoSala, miJugador);
    }

}
