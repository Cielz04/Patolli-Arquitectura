package entidades;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Tablero {
    private LinkedList<Casilla> casillas;
    private LinkedList<JLabel> labelsCasillas;

    public Tablero() {
        casillas = new LinkedList<>();
        labelsCasillas = new LinkedList<>();
    }

    public LinkedList<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(LinkedList<Casilla> casillas) {
        this.casillas = casillas;
        generarLabelsParaCasillas(); // Actualiza los labels cuando se establecen nuevas casillas
    }

    /**
     * Genera las casillas del tablero y las almacena.
     */
    public void generarCasillas(int numCasillas, int anchoPantalla, int tamanioCasilla) {
        casillas.clear(); // Asegurarse de que la lista esté vacía antes de generar nuevas casillas

        int x = (anchoPantalla - tamanioCasilla * numCasillas) / 2;
        int y = x / 3;
        int casillaId = 0;

        for (int i = 0; i < numCasillas; i++) {
            Casilla nuevaCasilla = new Casilla(casillaId, x, y, casillaId, null, null, "normal");
            casillas.add(nuevaCasilla);
            x += tamanioCasilla; // Incremento en la posición horizontal
            casillaId++;
        }

        generarLabelsParaCasillas();
    }

    /**
     * Genera JLabel para representar cada casilla en el tablero.
     */
    private void generarLabelsParaCasillas() {
        labelsCasillas.clear();
        for (Casilla casilla : casillas) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground(Color.LIGHT_GRAY); // Color de fondo predeterminado
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setText(String.valueOf(casilla.getID())); // Muestra el ID de la casilla

            // Establece el tamaño y la posición del JLabel
            label.setBounds(casilla.getCoordenadaX(), casilla.getCoordenadaY(), 15, 15);
            label.setPreferredSize(new Dimension(15, 15));

            labelsCasillas.add(label);
        }
    }

    public LinkedList<JLabel> getLabelsCasillas() {
        return labelsCasillas;
    }
}

