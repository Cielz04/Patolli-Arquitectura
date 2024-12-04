package entidades;

import java.awt.Color;

public class Ficha {

    private boolean activa;  // Indica si la ficha está activa o no
    private Color color;     // El color de la ficha (puedes asignar colores a cada ficha)
    private String tipo;     // Tipo de ficha (por ejemplo, "Reina", "Peón", etc.)

    // Constructor
    public Ficha(boolean activa, Color color, String tipo) {
        this.activa = activa;
        this.color = color;
        this.tipo = tipo;
    }

    // Getters y Setters
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo + " (" + color + ")";
    }
}