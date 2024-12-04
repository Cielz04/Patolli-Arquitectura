package Servidor;

import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import entidades.Jugador;
import tablero.Tablero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class ClientHandler implements Runnable {

   private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private SalaManager salaManager;  // Usamos SalaManager en lugar de Map
    private Tablero tablero;
    private List<ClientHandler> clientes;

    public ClientHandler(Socket socket, SalaManager salaManager) {
        this.socket = socket;
        this.salaManager = salaManager;  // Guardamos la referencia de SalaManager
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String mensajeRecibido = entrada.readLine();
                if (mensajeRecibido != null) {
                    procesarMensaje(mensajeRecibido);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al manejar la conexión con el cliente: " + e.getMessage());
        }
    }

    private void procesarMensaje(String mensaje) {
        if (mensaje.startsWith("ACTUALIZAR_TABLERO:")) {
            String datos = mensaje.substring("ACTUALIZAR_TABLERO:".length());
            actualizarTablero(datos);
        }
        // Otras lógicas de procesamiento de mensajes (por ejemplo, movimientos de fichas)
    }

    private void actualizarTablero(String datos) {
        // Lógica para actualizar el tablero en la interfaz gráfica del cliente
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);  // Enviar un mensaje al cliente
    }
}
