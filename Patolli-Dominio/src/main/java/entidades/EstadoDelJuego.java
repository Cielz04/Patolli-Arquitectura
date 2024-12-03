package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import tablero.Tablero;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class EstadoDelJuego implements Serializable {

    private List<Jugador> jugadores;
    private Tablero tablero;
    private int turno;
    private boolean partidaIniciada;
    private int valorUltTiro;
    private Jugador jugadorTurno;
    private int numCasillasAspa;

    // Constructor por defecto inicializa la lista de jugadores.
    public EstadoDelJuego() {
        this.jugadores = new ArrayList<>();
        this.tablero = new Tablero(); // Suponiendo que el tablero también debería inicializarse.
        this.turno = 0;
        this.partidaIniciada = false;
        this.valorUltTiro = 0;
        this.jugadorTurno = null;
        this.numCasillasAspa = 0;
    }

    // Constructor con parámetros
    public EstadoDelJuego(List<Jugador> jugadores, Tablero tablero, int turno, boolean partidaIniciada, int valorUltTiro) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.turno = turno;
        this.partidaIniciada = partidaIniciada;
        this.valorUltTiro = valorUltTiro;
    }

    // Getters y setters
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

    public void setJugadorEnTurno(Jugador jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    // Inicia los turnos del juego asignando el primer jugador como jugador en turno.
    public void iniciarTurnos() {
        if (!jugadores.isEmpty()) {
            jugadorTurno = jugadores.get(0);
        }
    }

    // Avanza al siguiente turno, asignando el siguiente jugador.
    public void siguienteTurno() {
        if (!jugadores.isEmpty()) {
            int indexActual = jugadores.indexOf(jugadorTurno);
            int siguienteIndex = (indexActual + 1) % jugadores.size();
            jugadorTurno = jugadores.get(siguienteIndex);
        }
    }

    public int getNumCasillasAspa() {
        return numCasillasAspa;
    }

    public void setNumCasillasAspa(int numCasillasAspa) {
        this.numCasillasAspa = numCasillasAspa;
    }

    // Reinicia el estado del juego a sus valores iniciales.
    public void reiniciarPartida() {
        this.jugadores.clear();
        this.tablero = new Tablero();
        this.turno = 0;
        this.partidaIniciada = false;
        this.valorUltTiro = 0;
        this.jugadorTurno = null;
        this.numCasillasAspa = 0;
    }
}