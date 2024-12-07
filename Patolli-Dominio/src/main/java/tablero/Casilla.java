package tablero;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * Representa una casilla dentro del tablero de juego. La clase extiende
 * {@link JLabel} y permite gestionar aspectos visuales y de lógica de la
 * casilla. Cada casilla puede estar ocupada por un ícono que representa una
 * ficha y mantiene un enlace a la siguiente casilla.
 */
public class Casilla extends JLabel {

    Casilla siguiente;

    /**
     * Constructor que crea una casilla con texto.
     *
     * @param text el texto que se mostrará en la casilla.
     */
    public Casilla(String text) {
        super(text);
    }

    /**
     * Constructor que crea una casilla con un ícono.
     *
     * @param image el ícono que se mostrará en la casilla.
     */
    public Casilla(Icon image) {
        super(image);
    }

    /**
     * Elimina el ícono de la casilla, dejando el fondo vacío. Llama a
     * {@code repaint()} para actualizar la interfaz.
     */
    public void quitarFondo() {
        this.setIcon(null);
        repaint();
    }

    /**
     * Verifica si la casilla está ocupada.
     *
     * @return {@code true} si la casilla tiene un ícono asignado, {@code false}
     * en caso contrario.
     */
    public boolean isOcupada() {
        return this.getIcon() != null;  // Verifica si el icono no es null, lo que significa que hay una ficha
    }

    /**
     * Obtiene la casilla siguiente vinculada a esta casilla.
     *
     * @return la casilla siguiente.
     */
    public Casilla getSiguiente() {
        return siguiente;
    }

    /**
     * Establece la casilla siguiente vinculada a esta casilla.
     *
     * @param siguiente la casilla que se vinculará como siguiente.
     */
    public void setSiguiente(Casilla siguiente) {
        this.siguiente = siguiente;
    }

}
