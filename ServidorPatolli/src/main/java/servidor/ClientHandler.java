package Servidor;

import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import entidades.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class ClientHandler implements Runnable {

   private Socket socket;
    private SalaManager salaManager;  // Gestión de salas
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClientHandler(Socket socket, SalaManager salaManager) {
        this.socket = socket;
        this.salaManager = salaManager;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Aquí iría la lógica de manejar la solicitud del cliente (crear sala, unirse, etc.)
            String mensajeRecibido;
            while ((mensajeRecibido = entrada.readLine()) != null) {
                // Ejemplo de cómo manejar la creación o unión a una sala
                if (mensajeRecibido.startsWith("CREAR_SALA")) {
                    // Lógica para crear la sala
                    String[] partes = mensajeRecibido.split(" ");
                    String codigoSala = partes[1];  // Obtener el código de la sala
                    int maxJugadores = Integer.parseInt(partes[2]);  // Obtener el número máximo de jugadores
                    salaManager.crearSala(codigoSala, maxJugadores);  // Delegamos la creación de la sala a SalaManager
                    salida.println("Sala creada con código: " + codigoSala);
                }

                if (mensajeRecibido.startsWith("UNIRSE_SALA")) {
                    // Lógica para unirse a una sala
                    String[] partes = mensajeRecibido.split(" ");
                    String codigoSala = partes[1];  // Obtener el código de la sala
                    Jugador jugador = new Jugador("Jugador 1");  // Aquí deberías obtener la información del jugador
                    String respuesta = salaManager.unirseASala(jugador, codigoSala);
                    salida.println(respuesta);  // Responder si la unión fue exitosa o no
                }
            }

        } catch (IOException e) {
            System.err.println("Error al manejar la conexión con el cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);  // Enviar un mensaje al cliente
    }
}
