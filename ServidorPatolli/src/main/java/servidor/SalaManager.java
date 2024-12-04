package Servidor;

import entidades.Jugador;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class SalaManager {

    private Map<String, Sala> salas;

    public SalaManager() {
        this.salas = new HashMap<>();
    }

    // Método para crear una sala
    public void crearSala(String codigoSala, int maxJugadores) {
        if (!salas.containsKey(codigoSala)) {
            Sala nuevaSala = new Sala(codigoSala, maxJugadores);
            salas.put(codigoSala, nuevaSala);
            System.out.println("Sala creada con código: " + codigoSala);
        } else {
            System.out.println("La sala con el código " + codigoSala + " ya existe.");
        }
    }

    // Método para unirse a una sala
    public String unirseASala(Jugador jugador, String codigoSala) {
        Sala sala = salas.get(codigoSala);

        if (sala == null) {
            return "La sala no existe.";
        }

        if (sala.tieneEspacio()) {
            sala.agregarJugador(jugador);  // Agregar el jugador a la sala
            return "Unido a la sala con éxito.";
        } else {
            return "La sala está llena.";
        }
    }

    // Obtener una sala por su código
    public Sala obtenerSala(String codigoSala) {
        return salas.get(codigoSala);
    }
}
