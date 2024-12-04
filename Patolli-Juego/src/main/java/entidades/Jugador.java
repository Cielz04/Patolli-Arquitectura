package entidades;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {

    private String nombre;
    private int puntaje;         // Puntaje del jugador
    private Color color;         // Color del jugador
    private List<Ficha> fichas;  // Lista de fichas del jugador

    // Constructor con nombre, número de fichas y color
    public Jugador(String nombre, int numFichas, Color color) {
        this.nombre = nombre;
        this.puntaje = 0;  // Puntaje inicial
        this.color = color;  // Asignamos el color
        setFichas(numFichas);  // Inicializamos las fichas
    }

    // Constructor con nombre solo (asignamos color predeterminado)
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.color = Color.RED;  // Color predeterminado (puedes cambiarlo)
        this.puntaje = 0;
        this.fichas = new ArrayList<>();
    }

    // Método para establecer las fichas del jugador
    public void setFichas(int numFichas) {
        this.fichas = new ArrayList<>();  // Limpiar la lista de fichas
        for (int i = 0; i < numFichas; i++) {
            this.fichas.add(new Ficha(false, null, null));  // Agregar fichas vacías
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    // Método para incrementar el puntaje
    public void incrementarPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, color);  // Usamos 'nombre' y 'color' para comparación
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Jugador other = (Jugador) obj;
        return Objects.equals(this.nombre, other.nombre) && Objects.equals(this.color, other.color);
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + " | Color: " + color + " | Puntaje: " + puntaje;
    }
}
