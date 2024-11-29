package entidades;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class EstadoDelJuego implements Serializable {

    /**
     * Clase que contiene el estado del juego
     */
    private List<Jugador> jugadores;
    private Tablero tablero;
    private int turno;
    private boolean partidaIniciada;
    private int valorUltTiro;
    private Jugador jugadorTurno;
    private int numCasillasAspa;

    public EstadoDelJuego() {
    }

    public EstadoDelJuego(List<Jugador> jugadores, Tablero tablero, int turno, boolean partidaIniciada, int valorUltTiro) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.turno = turno;
        this.partidaIniciada = partidaIniciada;
        this.valorUltTiro = valorUltTiro;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public boolean isPartidaIniciada() {
        return partidaIniciada;
    }

    public void setPartidaIniciada(boolean partidaIniciada) {
        this.partidaIniciada = partidaIniciada;
    }

    public int getValorUltTiro() {
        return valorUltTiro;
    }

    public void setValorUltTiro(int valorUltTiro) {
        this.valorUltTiro = valorUltTiro;
    }

    public Jugador getJugadorEnTurno() {
        return jugadorTurno;
    }

    public void setJugadorEnTurno(Jugador jugadorEnTurno) {
        this.jugadorTurno = jugadorEnTurno;
    }

    public void iniciarTurnos() {
        if (!jugadores.isEmpty()) {
            jugadorTurno = jugadores.get(0);
        }
    }

    public void siguienteTurno() {
        int indexActual = jugadores.indexOf(jugadorTurno);
        int siguienteIndex = (indexActual + 1) % jugadores.size();
        jugadorTurno = jugadores.get(siguienteIndex);
    }

    public int getNumCasillasAspa() {
        return numCasillasAspa;
    }

    public void setNumCasillasAspa(int numCasillasAspa) {
        this.numCasillasAspa = numCasillasAspa;
    }
    
    
}
