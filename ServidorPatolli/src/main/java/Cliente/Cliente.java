package Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Cliente {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String nombre;

    public Cliente(String host, int puerto) {
       try {
            socket = new Socket(host, puerto);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = reader.readLine()) != null) {
                        System.out.println("Servidor: " + mensaje);
                    }
                } catch (IOException e) {
                    System.err.println("Conexión perdida: " + e.getMessage());
                } finally {
                    cerrarConexion();
                }
            }).start();
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    // Enviar mensaje al servidor
    public void enviarMensaje(String mensaje) {
        try {
            writer.write(mensaje);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
        }
    }

    // Método para unirse a la partida
    public void unirsePartida(String codigo) {
        try {
            enviarMensaje(codigo);
            System.out.println("Intentando unirse a la partida con código: " + codigo);

           
            String respuesta = reader.readLine(); 
            System.out.println(respuesta);
        } catch (IOException e) {
            System.err.println("Error al enviar el código de la partida: " + e.getMessage());
        }
    }

   
    public void cerrarConexion() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

}
