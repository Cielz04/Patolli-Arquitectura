package dibujado;

import entidades.Casilla;
import entidades.Ficha;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class DibujaFicha implements IDibujar {

    private Ficha ficha;
    private Color colorEquipo; // Permite que el color del círculo interior sea modificable

    public DibujaFicha(Ficha ficha) {
        this.ficha = ficha;
        this.colorEquipo = Color.RED;
    }

    // Método para establecer el color del círculo interior
    public void setColorEquipo(Color colorEquipo) {
        this.colorEquipo = colorEquipo;
    }

    @Override
    public void dibujar(Graphics2D g2d, Casilla casilla, int numCasillasAspa, int TAMANIOCASILLA) {
        if (casilla != null && ficha.getPosicionActual() != null && casilla.equals(ficha.getPosicionActual())) {
            // Coordenadas y tamaño de los círculos
            int x = casilla.getCoordenadaX();
            int y = casilla.getCoordenadaY();
            int diametroExterior = TAMANIOCASILLA; // Diámetro del círculo exterior (blanco)
            int diametroInterior = (int) (TAMANIOCASILLA * 0.6); // Círculo interior más pequeño, 70% del tamaño

            // Dibujar el círculo exterior (blanco)
            g2d.setColor(colorEquipo);
            Ellipse2D.Double circuloExterior = new Ellipse2D.Double(x, y, diametroExterior, diametroExterior);
            g2d.fill(circuloExterior);

            // Dibujar el círculo interior (color modificable)
            g2d.setColor(Color.WHITE);
            int interiorX = x + (diametroExterior - diametroInterior) / 2; // Centrar el círculo interior
            int interiorY = y + (diametroExterior - diametroInterior) / 2; // Centrar el círculo interior
            Ellipse2D.Double circuloInterior = new Ellipse2D.Double(interiorX, interiorY, diametroInterior, diametroInterior);
            g2d.fill(circuloInterior);
        }
    }

    public Ficha getFicha() {
        return ficha;
    }
}
