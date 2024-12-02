package Control;

import entidades.Jugador;

/**
 *
 * @author esmeraldamolinaestrada
 */
public interface IControlJugador {

    void anadirJugador(Jugador jugador);

    Jugador obtenerJugadorTurno();

    void cambiarSiguienteTurno();

    void cambiarJugadorTurno();
}
