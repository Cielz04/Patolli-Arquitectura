package tablero;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class Casilla extends JLabel {

   
    private Casilla siguiente;

    // Constructor con texto
    public Casilla(String text) {
        super(text);
    }

    // Constructor vacío
    public Casilla() {
        super();
    }

    // Constructor con icono
    public Casilla(Icon image) {
        super(image);
    }

    // Quitar el fondo (icono) de la casilla
    public void quitarFondo() {
        this.setIcon(null);
        repaint();
    }

    // Verificar si la casilla está ocupada (si tiene un icono)
    public boolean isOcupada() {
        return this.getIcon() != null;
    }

    // Establecer el siguiente casilla
    public Casilla getSiguiente() {
        return siguiente;
    }

    // Establecer el siguiente casilla
    public void setSiguiente(Casilla siguiente) {
        this.siguiente = siguiente;
    }

    // Resaltar la casilla (si es necesario)
    public void resaltar(Color color) {
        this.setBackground(color);
        this.setOpaque(true);  // Hacer que el color de fondo sea visible
        repaint();
    }

    // Método para reiniciar la casilla (vaciar y quitar resalto)
    public void reset() {
        this.setIcon(null);  // Quitar cualquier ficha
        this.setBackground(null);  // Quitar color de fondo
        this.setOpaque(false);  // Quitar visibilidad de fondo
        repaint();
    }

    // Establecer un borde alrededor de la casilla (opcional)
    public void establecerBorde(Color color, int grosor) {
        this.setBorder(BorderFactory.createLineBorder(color, grosor));
    }

}
