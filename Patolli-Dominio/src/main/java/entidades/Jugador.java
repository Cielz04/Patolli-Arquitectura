package entidades;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {

	private String nombre;
	private int fondo;
	private Color color;
        private List<Ficha> fichas = new ArrayList<>();
	
        
        public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}

	public Jugador() {
	}
        
        

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getFondo() {
		return fondo;
	}

	public void setFondo(int fondo) {
		this.fondo = fondo;
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

    public void setFichas() {
        for (int i = 0; i < 6; i++) {
            this.fichas.add(new Ficha(false,null,null));
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        return Objects.equals(this.color, other.color);
    }
    
    
        
        
}
