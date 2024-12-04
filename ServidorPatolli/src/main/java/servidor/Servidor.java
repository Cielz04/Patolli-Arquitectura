package Servidor;

//import com.chat.tcpcommons.ClientThread;
import com.chat.tcpcommons.ClientThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Servidor {

    private List<ClientHandler> clientes;
    private SalaManager gestorDeSalas;  // Gestión de las salas

    public Servidor(int puerto) {
        clientes = new ArrayList<>();
        this.gestorDeSalas = new SalaManager();  // Inicializamos el gestor de salas
    }

    // Iniciar el servidor y aceptar conexiones de clientes
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(50065)) {
            System.out.println("Servidor iniciado");
            while (true) {
                Socket socket = serverSocket.accept();  // Aceptar una conexión entrante
                ClientHandler handler = new ClientHandler(socket, gestorDeSalas); // Pasamos el gestor de salas
                new Thread(handler).start();  // Crear un hilo para manejar al cliente
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Enviar actualizaciones a todos los clientes conectados
    public void enviarActualizacion(String mensaje) {
        for (ClientHandler cliente : clientes) {
            cliente.enviarMensaje(mensaje);
        }
    }
}
