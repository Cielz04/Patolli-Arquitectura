package dibujado;

import entidades.Casilla;
import entidades.Ficha;
import entidades.LugarSemicirculo;
import entidades.LugarTriangulo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class TableroCanvas extends JPanel {

    private LinkedList<Casilla> casillas;
    private int numCasillasAspa;
    private int anchoPantalla;
    private final int TAMANIOCASILLA = 15;
    private List<JLabel> labelsCasillas; // Lista para manejar las casillas representadas como JLabel

    public TableroCanvas(LinkedList<Casilla> casillas, int numCasillasAspa, int anchoPantalla) {
        this.casillas = casillas;
        this.numCasillasAspa = numCasillasAspa;
        this.anchoPantalla = anchoPantalla;
        this.labelsCasillas = new LinkedList<>();

        // Inicializar el panel
        setLayout(null); // Usar un layout absoluto para posicionar las casillas
        setPreferredSize(new Dimension(anchoPantalla, anchoPantalla));
        generarCasillas(); // Generar las casillas
    }

    public LinkedList<Casilla> generarCasillas() {
        int tamanioCasilla = TAMANIOCASILLA;
        int casillaIndex = 0;
        // Ajustamos x para centrar el tablero
        int x = (anchoPantalla - TAMANIOCASILLA * numCasillasAspa) / 2;
        int y = x / 3;

        if (numCasillasAspa % 2 == 0) {
            // Verticales arriba izquierda
            agregarCasillaComoLabel(x, y, casillaIndex, LugarSemicirculo.TOP, null, "circulo");
            casillaIndex++;
            y += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            y += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.TOP_LEFT, "triangulo");
            casillaIndex++;
            y += tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 3; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                y += tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "centro");
            casillaIndex++;
            x -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "propia");
            casillaIndex++;
            x -= tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 4; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                x -= tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.LEFT_TOP, "triangulo");
            casillaIndex++;
            x -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            x -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, LugarSemicirculo.LEFT, null, "circulo");
            casillaIndex++;
            y += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "circulo");
            casillaIndex++;
            x += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            x += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.LEFT_BOTTOM, "triangulo");
            casillaIndex++;
            x += tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 3; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                x += tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "centro");
            casillaIndex++;
            y += tamanioCasilla;

            // Vertical abajo izquierda
            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "propia");
            casillaIndex++;
            y += tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 4; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                y += tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.BOTTOM_LEFT, "triangulo");
            casillaIndex++;
            y += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            y += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, LugarSemicirculo.BOTTOM, null, "circulo");
            casillaIndex++;
            x += tamanioCasilla;

            // Vertical abajo derecha
            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "circulo");
            casillaIndex++;
            y -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            y -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.BOTTOM_RIGHT, "triangulo");
            casillaIndex++;
            y -= tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 3; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                y -= tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "centro");
            casillaIndex++;
            x += tamanioCasilla;

            // Horizontal derecha abajo
            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "propia");
            casillaIndex++;
            x += tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 4; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                x += tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.RIGHT_BOTTOM, "triangulo");
            casillaIndex++;
            x += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            x += tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "circulo");
            casillaIndex++;
            y -= tamanioCasilla;

            // Horizontal derecha arriba
            agregarCasillaComoLabel(x, y, casillaIndex, LugarSemicirculo.RIGHT, null, "circulo");
            casillaIndex++;
            x -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
            casillaIndex++;
            x -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.RIGHT_TOP, "triangulo");
            casillaIndex++;
            x -= tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 3; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                x -= tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "centro");
            casillaIndex++;
            y -= tamanioCasilla;

            // Vertical arriba derecha
            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "propia");
            casillaIndex++;
            y -= tamanioCasilla;

            for (int i = 0; i < (numCasillasAspa / 2) - 4; i++) {
                agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
                casillaIndex++;
                y -= tamanioCasilla;
            }

            agregarCasillaComoLabel(x, y, casillaIndex, null, LugarTriangulo.TOP_RIGHT, "triangulo");
            casillaIndex++;
            y -= tamanioCasilla;

            agregarCasillaComoLabel(x, y, casillaIndex, null, null, "normal");
        }
        return casillas;
    }

    /**
     * Agrega una casilla representada como JLabel al panel.
     */
    private void agregarCasillaComoLabel(int x, int y, int casillaIndex, LugarSemicirculo semicirculo, LugarTriangulo triangulo, String tipo) {
        JLabel casillaLabel = new JLabel();
        casillaLabel.setBounds(x, y, TAMANIOCASILLA, TAMANIOCASILLA);
        casillaLabel.setOpaque(true);
        casillaLabel.setBackground(Color.LIGHT_GRAY); // Color por defecto para "normal"
        casillaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Ajustar color o comportamiento según el tipo de casilla
        switch (tipo) {
            case "circulo":
                casillaLabel.setBackground(Color.CYAN);
                break;
            case "triangulo":
                casillaLabel.setBackground(Color.YELLOW);
                break;
            case "centro":
                casillaLabel.setBackground(Color.GREEN);
                break;
            case "propia":
                casillaLabel.setBackground(Color.PINK);
                break;
            default:
                break;
        }

        casillaLabel.setText(String.valueOf(casillaIndex)); // Opcional: texto para identificar

        // Agregar el JLabel al panel y la lista
        this.add(casillaLabel);
        labelsCasillas.add(casillaLabel);

        // Crear y agregar la casilla lógica
        Casilla nuevaCasilla = new Casilla(casillaIndex ,x, y, casillaIndex, semicirculo, triangulo, tipo);
        casillas.add(nuevaCasilla);
    }

    /**
     * Posiciona una ficha en una casilla representada por JLabel.
     *
     * @param nuevaCasilla Casilla donde se colocará la ficha.
     * @param colorFicha Color de la ficha.
     */
    public void sacarFicha(Casilla nuevaCasilla, Color colorFicha) {
        // Encontrar la casilla correspondiente
        for (JLabel casillaLabel : labelsCasillas) {
            if (nuevaCasilla.getCoordenadaX() == casillaLabel.getX()
                    && nuevaCasilla.getCoordenadaY() == casillaLabel.getY()) {

                // Cambiar el color de fondo para representar la ficha
                casillaLabel.setBackground(colorFicha);
                break;
            }
        }
    }

    public LinkedList<Casilla> getCasillas() {
        return casillas;
    }
}
