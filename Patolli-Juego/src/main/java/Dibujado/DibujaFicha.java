package dibujado;

import entidades.Casilla;
import entidades.Ficha;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DibujaFicha implements IDibujar {
    private Ficha ficha;

    public DibujaFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public void dibujar(Graphics2D g2d, Casilla casilla, int numCasillasAspa, int TAMANIOCASILLA) {
        if (casilla.equals(ficha.getPosicionActual())) {
            // Establecer el color de la ficha
            g2d.setColor(Color.RED);
            // Dibujar la ficha en la posición de la casilla actual
            Rectangle rect = new Rectangle();
            rect.setBounds(casilla.getCoordenadaX(), casilla.getCoordenadaY(), TAMANIOCASILLA, TAMANIOCASILLA);
            g2d.fill(rect);
        }
    }
}
