package tablero;

import entidades.EstadoDelJuego;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Tablero implements Serializable {

    private LinkedList<Casilla> casillas; // Lista enlazada de casillas
    private int canJugadores = 0;
    private int cantidadFichasAspa;
    private EstadoDelJuego estadoDelJuego;
    private int cantidadCasillasAspa;

    // Constructor vacío
    public Tablero() {
        this.casillas = new LinkedList<>();
    }

    // Constructor que recibe un EstadoDelJuego
    public Tablero(EstadoDelJuego estadoDelJuego) {
        this.estadoDelJuego = estadoDelJuego;
        this.casillas = new LinkedList<>();
        this.cantidadCasillasAspa = estadoDelJuego.getCantidadCasillas();
        this.cantidadFichasAspa = estadoDelJuego.getCantidadFichas();
        inicializarTablero();
    }

    // Inicializa el tablero con la cantidad de casillas necesarias
    private void inicializarTablero() {
        int tamañoEsperado = calcularTamañoLista(cantidadCasillasAspa);
        for (int i = 0; i < tamañoEsperado; i++) {
            Casilla nuevaCasilla = new Casilla();
            agregarCasilla(nuevaCasilla);
        }
    }

    // Calcula el tamaño esperado de la lista en función de la cantidad de casillas por aspa
    private int calcularTamañoLista(int cantidadCasillasAspa) {
        switch (cantidadCasillasAspa) {
            case 8:
                return 68;  // Número total de índices en el arreglo de orden para 8 aspas
            case 10:
                return 84;  // Número total de índices en el arreglo de orden para 10 aspas
            case 14:
                return 116; // Número total de índices en el arreglo de orden para 14 aspas
            default:
                throw new IllegalArgumentException("Cantidad de casillas por aspa no soportada.");
        }
    }

    // Método para agregar una casilla a la lista
    public void agregarCasilla(Casilla nuevaCasilla) {
        if (casillas.isEmpty()) {
            nuevaCasilla.setSiguiente(nuevaCasilla);  // Primera casilla apunta a sí misma
            casillas.add(nuevaCasilla);
        } else {
            Casilla ultimaCasilla = casillas.getLast();
            ultimaCasilla.setSiguiente(nuevaCasilla);  // La última casilla apunta a la nueva
            nuevaCasilla.setSiguiente(casillas.getFirst());  // Nueva casilla apunta a la primera
            casillas.add(nuevaCasilla);  // Añadimos la nueva casilla a la lista
        }
    }

    // Ordena las casillas según el orden definido por la cantidad de aspas
    public void ordenarCasillas(List<Casilla> listaCasillas) {
        int tamañoEsperado = calcularTamañoLista(cantidadCasillasAspa);
        if (listaCasillas.size() < tamañoEsperado) {
            throw new IllegalStateException("Lista de casillas tiene un tamaño insuficiente: "
                    + listaCasillas.size() + " pero se esperaban al menos " + tamañoEsperado);
        }

        switch (getCantidadCasillasAspa()) {
            case 8: {
                int[] orden = {15, 13, 11, 9, 7, 5, 3, 1, 0, 2, 4, 6, 8, 10, 12, 14, 64,
                    55, 54, 53, 52, 51, 50, 49, 48, 56, 57, 58, 59, 60, 61, 62, 63, 66,
                    16, 18, 20, 22, 24, 26, 28, 30, 31, 29, 27, 25, 23, 21, 19, 17, 67,
                    40, 41, 42, 43, 44, 45, 46, 47, 39, 38, 37, 36, 35, 34, 33, 32, 65};
                this.casillas = new LinkedList<>();
                for (int index : orden) {
                    if (index >= 0 && index < listaCasillas.size()) {
                        agregarCasilla(listaCasillas.get(index));
                    } else {
                        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index
                                + " para lista de tamaño: " + listaCasillas.size());
                    }
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
                    if (index >= 0 && index < listaCasillas.size()) {
                        agregarCasilla(listaCasillas.get(index));
                    } else {
                        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index
                                + " para lista de tamaño: " + listaCasillas.size());
                    }
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
                    if (index >= 0 && index < listaCasillas.size()) {
                        agregarCasilla(listaCasillas.get(index));
                    } else {
                        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index
                                + " para lista de tamaño: " + listaCasillas.size());
                    }
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Número de casillas de aspa no soportado: " + getCantidadCasillasAspa());
        }
    }

    // Getters y setters
    public LinkedList<Casilla> getCasillas() {
        return casillas;
    }

    public int getCantidadCasillasAspa() {
        return cantidadCasillasAspa;
    }

    public void setCantidadCasillasAspa(int cantidadCasillasAspa) {
        this.cantidadCasillasAspa = cantidadCasillasAspa;
    }

    public int getCantidadFichasAspa() {
        return cantidadFichasAspa;
    }

    public void setCantidadFichasAspa(int cantidadFichasAspa) {
        this.cantidadFichasAspa = cantidadFichasAspa;
    }

    public int getCanJugadores() {
        return canJugadores;
    }

    public void setCanJugadores(int canJugadores) {
        this.canJugadores = canJugadores;
    }

    public EstadoDelJuego getEstadoDelJuego() {
        return estadoDelJuego;
    }

    public void setEstadoDelJuego(EstadoDelJuego estadoDelJuego) {
        this.estadoDelJuego = estadoDelJuego;
    }

}
