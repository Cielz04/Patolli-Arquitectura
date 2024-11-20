package Control;

import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hector Espinoza
 */
public class ControlJugador {
    
    private List <Jugador> jugadores;//Lista de jugadores en juego
    private static ControlJugador jugadoresControl;
    private Jugador jugadorTurno;
    private int turno=0;

    
    public ControlJugador() {
        jugadores = new ArrayList<>();
    }
    
    public static ControlJugador getInstance(){
        if (jugadoresControl==null){
            jugadoresControl = new ControlJugador();
        }
        return jugadoresControl;
    }
    
    
    public void anadirJugador (Jugador jugador){
        jugadores.add(jugador);
    }
    
    public Jugador obtenerJugadorTurno(){
        if (jugadorTurno == null){
            jugadorTurno = jugadores.get(0);
        }
        return jugadorTurno;
    }
    
    public void cambiarSiguienteTurno(){
        
        if (turno < jugadores.size()){
            turno++;
        }else if (turno == jugadores.size()){
            turno=0;
        }
        
    }
    
    public void cambiarJugadorTurno(){
        
        if (jugadorTurno==null){
            jugadorTurno = jugadores.get(turno);
        }else{
            jugadorTurno = jugadores.get(turno);
        }
        
    }
    
    
    
}
