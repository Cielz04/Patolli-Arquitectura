package entidades;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import tablero.Tablero;

/**
 * Clase Juego. Representa la lógica principal de un juego de Patolli,
 * incluyendo el tablero, jugadores, turnos y configuraciones. Implementa la
 * interfaz {@link Serializable} para permitir la serialización de objetos.
 */
public class Juego implements Serializable {

    private Tablero tablero;
    private int apuesta, fondoFijo, numCasillasAspa;
    private List<Jugador> jugadores;
    private static Juego singletonJuego;
    private boolean pCreada = false;
    private boolean pIniciada = false;
    private int turno = 0;
    private int valorUltTiro = 0;
    private int cantFichas;
    private int canJugadores;

    /**
     * Constructor de la clase Juego.
     *
     * @param tablero el tablero del juego.
     * @param apuesta la cantidad de la apuesta.
     * @param fondoFijo el fondo fijo del juego.
     * @param numCasillasAspa el número de casillas en forma de aspa.
     * @param jugadores la lista de jugadores que participan en el juego.
     */
    public Juego(Tablero tablero, int apuesta, int fondoFijo, int numCasillasAspa, List<Jugador> jugadores) {
        this.tablero = tablero;
        this.apuesta = apuesta;
        this.fondoFijo = fondoFijo;
        this.jugadores = jugadores;
        this.numCasillasAspa = numCasillasAspa;
    }

    /**
     * Constructor por defecto. Inicializa el juego con un tablero vacío y una
     * lista vacía de jugadores.
     */
    public Juego() {
        this.tablero = new Tablero();
        this.jugadores = new ArrayList<>();
    }

    /**
     * Obtiene el tablero del juego.
     *
     * @return el objeto {@link Tablero}.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Establece el tablero del juego.
     *
     * @param tablero el tablero que se desea asignar.
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Obtiene el valor de la apuesta.
     *
     * @return la cantidad de la apuesta.
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Establece el valor de la apuesta.
     *
     * @param apuesta la cantidad a establecer como apuesta.
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Obtiene el fondo fijo del juego.
     *
     * @return el fondo fijo.
     */
    public int getFondoFijo() {
        return fondoFijo;
    }

    /**
     * Establece el fondo fijo del juego.
     *
     * @param fondoFijo la cantidad a establecer como fondo fijo.
     */
    public void setFondoFijo(int fondoFijo) {
        this.fondoFijo = fondoFijo;
    }

    /**
     * Obtiene el número de casillas en forma de aspa.
     *
     * @return el número de casillas.
     */
    public int getNumCasillasAspa() {
        return numCasillasAspa;
    }

    /**
     * Establece el número de casillas en forma de aspa.
     *
     * @param numCasillasAspa el número de casillas.
     */
    public void setNumCasillasAspa(int numCasillasAspa) {
        this.numCasillasAspa = numCasillasAspa;
    }

    /**
     * Agrega jugadores al juego en función de la cantidad especificada.
     * Configura colores y nombres predeterminados para los jugadores.
     */
    public void addJugador(Jugador nombre) {
        if (jugadores != null && (canJugadores > 1)) {
            jugadores = new LinkedList<>();
            jugadores.add(new Jugador("Jugador 1", Color.RED));

            if (canJugadores == 2) {
                jugadores.add(new Jugador("Jugador 2", Color.GREEN));

            } else if (canJugadores == 3) {
                jugadores.add(new Jugador("Jugador 2", Color.GREEN));
                jugadores.add(new Jugador("Jugador 3", Color.YELLOW));

            } else if (canJugadores == 4) {
                jugadores.add(new Jugador("Jugador 2", Color.GREEN));
                jugadores.add(new Jugador("Jugador 3", Color.YELLOW));
                jugadores.add(new Jugador("Jugador 4", Color.BLUE));

            }
        }

        if (jugadores != null && (canJugadores > 1)) {
            jugadores = new LinkedList<>();
            jugadores.add(new Jugador("Jugador 1", Color.RED));

            if (canJugadores == 2) {
                jugadores.add(new Jugador("Jugador 2", Color.GREEN));

            } else if (canJugadores == 3) {
                jugadores.add(new Jugador("Jugador 2", Color.GREEN));
                jugadores.add(new Jugador("Jugador 3", Color.YELLOW));

            } else if (canJugadores == 4) {
                jugadores.add(new Jugador("Jugador 2", Color.GREEN));
                jugadores.add(new Jugador("Jugador 3", Color.YELLOW));
                jugadores.add(new Jugador("Jugador 4", Color.BLUE));

            }
        }

    }

    /**
     * Obtiene la lista de jugadores.
     *
     * @return la lista de jugadores.
     */
    public List<Jugador> getListaJugador() {
        return this.jugadores;
    }

    /**
     * Obtiene la instancia única (singleton) de la clase Juego.
     *
     * @return la instancia única de Juego.
     */
    public static Juego getInstance() {
        if (singletonJuego == null) {
            singletonJuego = new Juego();
        }
        return singletonJuego;
    }

    /**
     * Verifica si la partida ha sido creada.
     *
     * @return {@code true} si la partida ha sido creada; de lo contrario,
     * {@code false}.
     */
    public boolean ispCreada() {
        return pCreada;
    }

    /**
     * Establece el estado de creación de la partida.
     *
     * @param pCreada {@code true} si la partida está creada; de lo contrario,
     * {@code false}.
     */
    public void setpCreada(boolean pCreada) {
        this.pCreada = pCreada;
    }

    /**
     * Inicia la partida.
     */
    public void empezarPartida() {
        this.pIniciada = true;
    }

    /**
     * Verifica si la partida ha sido iniciada.
     *
     * @return {@code true} si la partida está iniciada; de lo contrario,
     * {@code false}.
     */
    public boolean getpIniciada() {
        return this.pIniciada;
    }

    /**
     * Obtiene el turno actual del juego.
     *
     * @return el turno actual.
     */
    public int getTurno() {
        return this.turno;
    }

    /**
     * Avanza al siguiente turno en el juego.
     */
    public void setTurno() {
        this.turno += 1;
        if (this.turno == this.getListaJugador().size()) {
            this.turno = 0;
        }
    }

    /**
     * Obtiene el valor del último tiro.
     *
     * @return el valor del último tiro.
     */
    public int getValorUltTiro() {
        return valorUltTiro;
    }

    /**
     * Establece el valor del último tiro.
     *
     * @param valorUltTiro el valor del último tiro.
     */
    public void setValorUltTiro(int valorUltTiro) {
        this.valorUltTiro = valorUltTiro;
    }

    /**
     * Obtiene la cantidad de jugadores.
     *
     * @return la cantidad de jugadores.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Obtiene la cantidad de fichas.
     *
     * @return la cantidad de fichas.
     */
    public int getCantFichas() {
        return cantFichas;
    }

    /**
     * Establece la cantidad de fichas.
     *
     * @param cantFichas la cantidad de fichas.
     */
    public void setCantFichas(int cantFichas) {
        this.cantFichas = cantFichas;
    }

    /**
     * Obtiene la cantidad de jugadores.
     *
     * @return la cantidad de jugadores.
     */
    public int getCanJugadores() {
        return canJugadores;
    }

    /**
     * Establece la cantidad de jugadores.
     *
     * @param canJugadores la cantidad de jugadores.
     */
    public void setCanJugadores(int canJugadores) {
        this.canJugadores = canJugadores;
    }

    public void addJugador() {
    }

}
