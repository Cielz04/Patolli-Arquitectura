package entidades;

import java.util.ArrayList;
import java.util.List;
import tablero.Tablero;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class EstadoDelJuego {
    private List<Jugador> jugadores;
    private boolean partidaIniciada;
    private int cantidadCasillas;  // Número de casillas en el tablero
    private int cantidadFichas;    // Número de fichas por jugador
    private Tablero tablero;
    private String codigoSala;
    private int apuesta;  // Apuesta por jugador

    public EstadoDelJuego() {
        this.jugadores = new ArrayList<>();
        this.partidaIniciada = false;
        this.cantidadCasillas = 8;  // Valor por defecto
        this.cantidadFichas = 2;    // Valor por defecto
    }

    // Métodos getter y setter
    public int getCantidadCasillas() {
        return cantidadCasillas;
    }

    public void setCantidadCasillas(int cantidadCasillas) {
        this.cantidadCasillas = cantidadCasillas;
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public int getCantidadFichas() {
        return cantidadFichas;
    }

    public void setCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;  // Almacena la apuesta
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public boolean isPartidaIniciada() {
        return partidaIniciada;
    }

    public void iniciarPartida() {
        this.partidaIniciada = true;
    }

    public void finalizarPartida() {
        this.partidaIniciada = false;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

}