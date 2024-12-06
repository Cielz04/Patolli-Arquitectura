package tablero;

import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import entidades.Jugador;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Hector Espinoza
 */
public class Tablero implements Serializable {

    private LinkedList<Casilla> casillas; // Lista enlazada de casillas
    private int cantidadFichas;
    private List<Integer> fichasJugador1Posicion;
    private List<Integer> fichasJugador2Posicion;
    private List<Integer> fichasJugador3Posicion;
    private List<Integer> fichasJugador4Posicion;
    private int jugadorTurno = 0;
    private int cantidadJugadores;
    private boolean juegoTermino = false;
    private boolean juegoInicia = false;
    private int apuesta;
    private List<Integer> cantidadMontoJugadores;
    private int cantidadCasillasAspa;
    private List<Jugador> jugadores;
    private boolean salaEspera = true;

    public Tablero() {
        this.casillas = new LinkedList<>();
        this.fichasJugador1Posicion = new LinkedList<>();
        this.fichasJugador2Posicion = new LinkedList<>();
        this.fichasJugador3Posicion = new LinkedList<>();
        this.fichasJugador4Posicion = new LinkedList<>();
        this.cantidadMontoJugadores = new LinkedList<>();
        this.jugadores = new LinkedList<>();
    }

    public void agregarCasilla(Casilla nuevaCasilla) {
        if (casillas.isEmpty()) {
            // Si la lista está vacía, la casilla apunta a sí misma
            nuevaCasilla.setSiguiente(nuevaCasilla);
            casillas.add(nuevaCasilla);
        } else {
            // Si ya hay casillas, se agrega al final de la lista y se ajusta la circularidad
            Casilla ultimaCasilla = casillas.getLast();
            ultimaCasilla.setSiguiente(nuevaCasilla); // Última casilla apunta a la nueva
            nuevaCasilla.setSiguiente(casillas.getFirst()); // Nueva casilla apunta a la primera
            casillas.add(nuevaCasilla); // Agregar a la lista
        }
    }

    public int getTamanio() {
        return casillas.size();
    }

    public boolean casillaOcupada(Casilla casilla) {
        return casilla.isOcupada();  // Cambié la llamada a instancias de Casilla
    }

    public void ordenarCasillas(List<Casilla> listaCasillas) {
        switch (getCantidadCasillasAspa()) {
            case 8: {
                int[] orden = {15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 64,
                    55, 54, 53, 52, 51, 50, 49, 48, 56, 57, 58, 59, 60, 61, 62, 63, 66,
                    16, 18, 20, 22, 24, 26, 28, 30, 31, 29, 27, 25, 23, 21, 19, 17, 67,
                    40, 41, 42, 43, 44, 45, 46, 47, 39, 38, 37, 36, 35, 34, 33, 32, 65};
                this.casillas = new LinkedList<>();
                for (int index : orden) {
                    agregarCasilla(listaCasillas.get(index));
                }
                break;
            }
            case 10: {
                int[] orden = {19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 80,
                    69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 82,
                    20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 39, 37, 35, 33, 31, 29, 27, 25, 23, 21, 83,
                    50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 81};
                this.casillas = new LinkedList<>();
                for (int index : orden) {
                    agregarCasilla(listaCasillas.get(index));
                }
                break;
            }
            case 14: {
                int[] orden = {27, 25, 23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 112,
                    97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
                    111, 114, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 55, 53, 51, 49, 47, 45, 43, 41, 39, 37, 35, 33, 31, 29,
                    115, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 113};
                this.casillas = new LinkedList<>();
                for (int index : orden) {
                    agregarCasilla(listaCasillas.get(index));
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Número de casillas de aspa no soportado: " + getCantidadCasillasAspa());
        }
    }

    public LinkedList<Casilla> getCasillas() {
        return casillas;
    }

    public int getCantidadCasillasAspa() {
        return cantidadCasillasAspa;
    }

    public void setCantidadCasillasAspa(int cantidadCasillasAspa) {
        this.cantidadCasillasAspa = cantidadCasillasAspa;
    }

    public int getCantidadFichas() {
        return cantidadFichas;
    }

    public void setCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    public List<Integer> getFichasJugador1Posicion() {
        return fichasJugador1Posicion;
    }

    public void setFichasJugador1Posicion(List<Integer> fichasJugador1Posicion) {
        this.fichasJugador1Posicion = fichasJugador1Posicion;
    }

    public List<Integer> getFichasJugador2Posicion() {
        return fichasJugador2Posicion;
    }

    public void setFichasJugador2Posicion(List<Integer> fichasJugador2Posicion) {
        this.fichasJugador2Posicion = fichasJugador2Posicion;
    }

    public List<Integer> getFichasJugador3Posicion() {
        return fichasJugador3Posicion;
    }

    public void setFichasJugador3Posicion(List<Integer> fichasJugador3Posicion) {
        this.fichasJugador3Posicion = fichasJugador3Posicion;
    }

    public List<Integer> getFichasJugador4Posicion() {
        return fichasJugador4Posicion;
    }

    public void setFichasJugador4Posicion(List<Integer> fichasJugador4Posicion) {
        this.fichasJugador4Posicion = fichasJugador4Posicion;
    }

    public int getJugadorTurno() {
        return jugadorTurno;
    }

    public void setJugadorTurno(int jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public boolean isJuegoTermino() {
        return juegoTermino;
    }

    public void setJuegoTermino(boolean juegoTermino) {
        this.juegoTermino = juegoTermino;
    }

    public boolean isJuegoInicia() {
        return juegoInicia;
    }

    public void setJuegoInicia(boolean juegoInicia) {
        this.juegoInicia = juegoInicia;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public List<Integer> getCantidadMontoJugadores() {
        return cantidadMontoJugadores;
    }

    public void setCantidadMontoJugadores(List<Integer> cantidadMontoJugadores) {
        this.cantidadMontoJugadores = cantidadMontoJugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean agregarJugador(Jugador jugador) {
        if (this.jugadores != null) {
            jugadores.add(jugador);
            return true;
        } else {
            jugadores = new LinkedList<>();
            jugadores.add(jugador);
            return true;
        }

    }

    public boolean eliminarJugador(Jugador jugador) {
        if (this.jugadores != null) {
            jugadores.remove(jugador);
            return true;
        }
        return false;
    }

    public boolean isSalaEspera() {
        return salaEspera;
    }

    public void setSalaEspera(boolean salaEspera) {
        this.salaEspera = salaEspera;
    }

    public void actualizarConMensaje(Message tablero) {
        if (tablero == null) {
            throw new IllegalArgumentException("El objeto Tablero no puede ser nulo.");
        }

        MessageBody content = tablero.getContent();
        if (content == null) {
            throw new IllegalArgumentException("El contenido del mensaje no puede ser nulo.");
        }

        // Asegurándonos de que las propiedades del tablero no sean nulas o inválidas
        if (content.getApuesta() > 0) {
            this.apuesta = content.getApuesta();
        }

        if (content.getCantidadCasillasAspa() > 0) {
            this.cantidadCasillasAspa = content.getCantidadCasillasAspa();
        }

        if (content.getCantidadFichas() > 0) {
            this.cantidadFichas = content.getCantidadFichas();
        }

        if (content.getCantidadJugadores() > 0) {
            this.cantidadJugadores = content.getCantidadJugadores();
        }

        // Verificamos si las listas no están vacías antes de asignarlas
        if (content.getCantidadMontoJugadores() != null && !content.getCantidadMontoJugadores().isEmpty()) {
            this.cantidadMontoJugadores = content.getCantidadMontoJugadores();
        }

        if (content.getFichasJugador1Posicion() != null && !content.getFichasJugador1Posicion().isEmpty()) {
            this.fichasJugador1Posicion = content.getFichasJugador1Posicion();
        }

        if (content.getFichasJugador2Posicion() != null && !content.getFichasJugador2Posicion().isEmpty()) {
            this.fichasJugador2Posicion = content.getFichasJugador2Posicion();
        }

        if (content.getFichasJugador3Posicion() != null && !content.getFichasJugador3Posicion().isEmpty()) {
            this.fichasJugador3Posicion = content.getFichasJugador3Posicion();
        }

        if (content.getFichasJugador4Posicion() != null && !content.getFichasJugador4Posicion().isEmpty()) {
            this.fichasJugador4Posicion = content.getFichasJugador4Posicion();
        }

        // Verificamos los valores booleanos y enteros (no tienen sentido con 0)
        this.juegoInicia = content.isJuegoInicia();
        this.juegoTermino = content.isJuegoTermino();

        if (content.getJugadorTurno() > 0) {
            this.jugadorTurno = content.getJugadorTurno();
        }

        // Verificamos si los jugadores no son nulos ni vacíos
        if (content.getJugadores() != null && !content.getJugadores().isEmpty()) {
            this.jugadores = content.getJugadores();
        }

        this.salaEspera = content.isSalaEspera(); // Este valor no tiene restricción de validación
    }

    public void actualizarMenosCasillas(Tablero tablero) {

        if (tablero == null) {
            throw new IllegalArgumentException("El objeto Tablero no puede ser nulo.");
        }

        // Asegurándonos de que las propiedades del tablero no sean nulas
        this.apuesta = tablero.getApuesta();
        this.cantidadCasillasAspa = tablero.getCantidadCasillasAspa();
        this.cantidadFichas = tablero.getCantidadFichas();
        this.cantidadJugadores = tablero.getCantidadJugadores();

        // Verificamos si las listas son nulas antes de asignarlas
        this.cantidadMontoJugadores = (tablero.getCantidadMontoJugadores() != null)
                ? tablero.getCantidadMontoJugadores() : new LinkedList<>();

        this.fichasJugador1Posicion = (tablero.getFichasJugador1Posicion() != null)
                ? tablero.getFichasJugador1Posicion() : new LinkedList<>();
        this.fichasJugador2Posicion = (tablero.getFichasJugador2Posicion() != null)
                ? tablero.getFichasJugador2Posicion() : new LinkedList<>();
        this.fichasJugador3Posicion = (tablero.getFichasJugador3Posicion() != null)
                ? tablero.getFichasJugador3Posicion() : new LinkedList<>();
        this.fichasJugador4Posicion = (tablero.getFichasJugador4Posicion() != null)
                ? tablero.getFichasJugador4Posicion() : new LinkedList<>();

        this.juegoInicia = tablero.isJuegoInicia();
        this.juegoTermino = tablero.isJuegoTermino();
        this.jugadorTurno = tablero.getJugadorTurno();

        // Verificamos si los jugadores no son nulos
        this.jugadores = (tablero.getJugadores() != null) ? tablero.getJugadores() : new LinkedList<>();
        this.salaEspera = tablero.isSalaEspera();

    }

}
