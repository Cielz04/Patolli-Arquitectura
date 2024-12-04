package Servidor;

import entidades.Casilla;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import java.util.List;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class ControladorDeJuego {

  
    private static EstadoDelJuego estadoDelJuego;

    public static EstadoDelJuego getEstadoDelJuego() {
        if (estadoDelJuego == null) {
            estadoDelJuego = new EstadoDelJuego();
        }
        return estadoDelJuego;
    }

    public static void reiniciarEstadoDelJuego() {
        estadoDelJuego = new EstadoDelJuego(); // Crea una nueva instancia
    }
    
    public ControladorDeJuego(EstadoDelJuego estadoDelJuego) {
        if (estadoDelJuego == null) {
            throw new IllegalArgumentException("El estado del juego no puede ser nulo.");
        }
        this.estadoDelJuego = estadoDelJuego;
    }

    public void iniciarPartida() {
        if (estadoDelJuego.getJugadores().size() < 2) {
            System.out.println("No hay suficientes jugadores para comenzar la partida.");
            return;
        }

        // Iniciar la partida
        estadoDelJuego.iniciarPartida();
        System.out.println("La partida ha comenzado.");

        // Otros procesos de inicio (como manejar turnos o la lógica de juego)
    }

    public void manejarTurno() {
        // Lógica para manejar los turnos
    }

    public boolean verificarFinDePartida() {
        // Lógica para verificar el fin de la partida
        return false;
    }
}
