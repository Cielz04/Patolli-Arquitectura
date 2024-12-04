package Servidor;

import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Sala {

    private String codigoSala;
    private List<Jugador> jugadores;
    private int maxJugadores;

    public Sala(String codigoSala, int maxJugadores) {
        this.codigoSala = codigoSala;
        this.maxJugadores = maxJugadores;
        this.jugadores = new ArrayList<>();
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public void agregarJugador(Jugador jugador) {
        if (jugadores.size() < maxJugadores) {
            jugadores.add(jugador);
        }
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public boolean tieneEspacio() {
        return jugadores.size() < maxJugadores;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }
}
