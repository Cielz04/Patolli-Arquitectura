package tablero;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Hector Espinoza
 */
public class Casilla extends JLabel {

    Casilla siguiente;

    public Casilla(String text) {
        super(text);
    }

    public Casilla(Icon image) {
        super(image);
    }
    
    public void quitarFondo(){
        this.setIcon(null);
        repaint();
    }

    public boolean isOcupada() {
        return this.getIcon() != null;  // Verifica si el icono no es null, lo que significa que hay una ficha
    }

    public Casilla getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Casilla siguiente) {
        this.siguiente = siguiente;
    }

}
