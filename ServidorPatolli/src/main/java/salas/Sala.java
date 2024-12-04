package salas;

import com.chat.tcpcommons.ClientThread;
import java.util.ArrayList;
import java.util.List;
import tablero.Tablero;

/**
 *
 * @author Hector Espinoza
 */
public class Sala {

    private String codigo;
    private Tablero tablero; // Estado del tablero
    private List<ClientThread> jugadores; // Clientes en la sala

    public Sala(Sala sala) {
        this.codigo = sala.codigo;
        this.tablero = new Tablero(); // Inicializa el tablero
        this.jugadores = new ArrayList<>();
    }
    
    
    

    public Sala(String codigo) {
        this.codigo = codigo;
        this.tablero = new Tablero(); // Inicializa el tablero
        this.jugadores = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public List<ClientThread> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(ClientThread jugador) {
        jugadores.add(jugador);
    }

}
