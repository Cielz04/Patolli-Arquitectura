package entidades;

import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase Jugador. Representa a un jugador dentro del juego, con un nombre único
 * y un color que lo identifica. Implementa la interfaz {@link Serializable}
 * para permitir la serialización.
 *
 */
public class Jugador implements Serializable {

    private String nombre;
    private Color color;

    /**
     * Constructor por defecto. Crea un jugador sin inicializar el nombre ni el
     * color.
     */
    public Jugador() {
    }

    /**
     * Constructor de la clase Jugador.
     *
     * @param nombre el nombre del jugador.
     * @param color el color que identifica al jugador.
     */
    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre el nombre que se desea asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el color que identifica al jugador.
     *
     * @return el color del jugador.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color que identifica al jugador.
     *
     * @param color el color que se desea asignar.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Genera un código hash para el jugador, basado en su color.
     *
     * @return el código hash del jugador.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.color);
        return hash;
    }

    /**
     * Compara si este jugador es igual a otro objeto. Dos jugadores se
     * consideran iguales si sus colores son iguales.
     *
     * @param obj el objeto a comparar.
     * @return {@code true} si los jugadores tienen el mismo color;
     * {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        return Objects.equals(this.color, other.color);
    }

    /**
     * Representación en cadena del jugador. Incluye el nombre del jugador.
     *
     * @return una cadena con el nombre del jugador.
     */
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + '}';
    }

}
