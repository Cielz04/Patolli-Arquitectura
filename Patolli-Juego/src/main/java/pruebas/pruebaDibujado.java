/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dibujado.TableroCanvas;
import entidades.Casilla;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 *
 * @author Jesus
 */
public class pruebaDibujado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear la lista de casillas
        LinkedList<Casilla> casillas = new LinkedList<>();
        
        int numCasillasAspa = 20; // Establece un número apropiado de casillas para el aspa
        int anchoPantalla = 1500; // Ancho de la ventana

        // Crear el tableroCanvas
        TableroCanvas tablero = new TableroCanvas(casillas, numCasillasAspa, anchoPantalla);
        tablero.generarCasillas(); // Generar las casillas en el tablero

        // Crear el JFrame para mostrar el tablero
        JFrame ventana = new JFrame("Juego de Patolli");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(anchoPantalla, anchoPantalla); // Establece el tamaño de la ventana
        ventana.add(tablero); // Añadir el tableroCanvas a la ventana
        ventana.setVisible(true); // Hacer la ventana visible
        
        Casilla fichaJugador1 = casillas.get(10); // Por ejemplo, mover a la segunda casilla
        tablero.sacarFicha(fichaJugador1, Color.RED);
        
        Casilla fichaJugador2 = casillas.get(31); // Por ejemplo, mover a la segunda casilla
        tablero.sacarFicha(fichaJugador2, Color.BLUE);
        
        Casilla fichaJugador3 = casillas.get(52); // Por ejemplo, mover a la segunda casilla
        tablero.sacarFicha(fichaJugador3, Color.GREEN);
        
        Casilla fichaJugador4 = casillas.get(73); // Por ejemplo, mover a la segunda casilla
        tablero.sacarFicha(fichaJugador4, Color.YELLOW);
        
    }
    
}
