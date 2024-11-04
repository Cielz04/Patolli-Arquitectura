package dibujado;

import entidades.Casilla;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DibujaNormal implements IDibujar {

    @Override
    public void dibujar(Graphics2D g2d, Casilla casilla, int numCasillasAspa, int TAMANIOCASILLA) {
        if (casilla.getTipoCasilla().equalsIgnoreCase("normal")) {
            Rectangle rect = new Rectangle();
            g2d.setColor(Color.pink);
            rect.setBounds(casilla.getCoordenadaX(), casilla.getCoordenadaY(), TAMANIOCASILLA, TAMANIOCASILLA);
            g2d.fill(rect);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
        }
    }

}
