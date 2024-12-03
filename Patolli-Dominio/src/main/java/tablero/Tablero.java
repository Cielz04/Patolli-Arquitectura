package tablero;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Hector Espinoza
 */
public class Tablero implements Serializable{

    private LinkedList<Casilla> casillas; // Lista enlazada de casillas
    private int canJugadores=0;
    private int cantidadFichasAspa;


    public Tablero() {
        this.casillas = new LinkedList<>();
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

    public int getCanJugadores() {
        return canJugadores;
    }

    public void setCanJugadores(int canJugadores) {
        this.canJugadores = canJugadores;
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
                int[] orden = {27,25,23,21,19,17,15,13,11,9,7,5,3,1,0,2,4,6,8,10,12,14,16,18,20,22,24,26,112,
                    97,96,95,94,93,92,91,90,89,88,87,86,85,84,98,99,100,101,102,103,104,105,106,107,108,109,110,
                    111,114,28,30,32,34,36,38,40,42,44,46,48,50,52,54,55,53,51,49,47,45,43,41,39,37,35,33,31,29,
                    115,70,71,72,73,74,75,76,77,78,79,80,81,82,83,69,68,67,66,65,64,63,62,61,60,59,58,57,56,113};
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
        return cantidadFichasAspa;
    }

    public void setCantidadCasillasAspa(int cantidadFichasAspa) {
        this.cantidadFichasAspa = cantidadFichasAspa;
    }
    
    
    
}
