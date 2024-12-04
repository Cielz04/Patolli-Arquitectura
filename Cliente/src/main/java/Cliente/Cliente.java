package Cliente;

import com.chat.tcpcommons.ClientThread;
import com.chat.tcpcommons.ConnectionTemplate;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import entidades.Tablero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Cliente {

    private String host;
    private int puerto;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private EstadoDelJuego estadoDelJuego;  // Objeto que gestiona el estado del juego
    private Tablero tablero;  // El tablero que se debe actualizar

    public Cliente(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
        estadoDelJuego = new EstadoDelJuego();  // Inicializa el estado del juego
    }

    // Método para conectar al servidor
    public void conectar() {
        try {
            socket = new Socket(host, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            System.out.println("Conectado al servidor en " + host + ":" + puerto);
            escucharMensajes();  // Escuchar mensajes del servidor en un hilo separado
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método que escucha los mensajes del servidor en un hilo independiente
    private void escucharMensajes() {
        new Thread(() -> {
            try {
                while (true) {
                    // Recibe los mensajes enviados por el servidor
                    Object mensaje = entrada.readObject();
                    if (mensaje instanceof Message) {
                        procesarMensaje((Message) mensaje);  // Procesa el mensaje
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al recibir mensaje: " + e.getMessage());
            }
        }).start();
    }

    // Procesar el mensaje recibido del servidor
    private void procesarMensaje(Message mensaje) {
        if (mensaje.getMessageType() == MessageType.ACTUALIZAR_TABLERO) {
            MessageBody body = mensaje.getBody();
            String datos = body.getCodigoSala();  // Suponiendo que el cuerpo contiene el código de la sala o los datos del tablero
            actualizarTablero(datos);
        }
    }

    // Método para actualizar el tablero con los datos recibidos
    private void actualizarTablero(String datos) {
        // Aquí puedes actualizar las casillas, los jugadores, o cualquier otro aspecto del tablero
        // Este es un ejemplo básico, puedes personalizarlo según la lógica de tu aplicación
        JOptionPane.showMessageDialog(null, "Actualizando tablero con los datos: " + datos, "Actualizar", JOptionPane.INFORMATION_MESSAGE);

        // Ejemplo de cómo actualizar el estado del juego
        estadoDelJuego.setTablero(tablero);  // Establece el tablero actualizado en el estado del juego
    }

    // Método para enviar un mensaje al servidor
    public void enviarMensaje(String mensaje) {
        try {
            if (salida != null) {
                MessageBody messageBody = new MessageBody();
                messageBody.setCodigoSala(mensaje);  // Usar el setter para asignar el valor

                // Crear el mensaje con el tipo correspondiente
                Message mensajeObj = new Message(MessageType.ACTUALIZAR_TABLERO, messageBody);
                salida.writeObject(mensajeObj);  // Enviar el mensaje como objeto
                salida.flush(); // Aseguramos que se haya enviado
            }
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
        }
    }

    // Método para crear una sala
    public void crearSala(String codigoSala, int maxJugadores) {
        try {
            // Crear el MessageBody con los datos de la sala
            MessageBody body = new MessageBody();
            body.setCodigoSala(codigoSala);
            body.setMaxJugadores(maxJugadores);  // Número máximo de jugadores

            // Crear el Message con el tipo correspondiente
            Message mensaje = new Message.Builder()
                    .messageType(MessageType.CREAR_SALA) // Tipo de mensaje
                    .body(body)
                    .build();

            // Enviar el mensaje al servidor usando ObjectOutputStream
            salida.writeObject(mensaje);  // Enviar el mensaje como objeto
            salida.flush();  // Asegurarse de que se envíe el mensaje
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje para crear la sala: " + e.getMessage());
        }
    }

    // Método para unirse a una sala
    public void unirseAPartida(String codigoSala) {
        try {
            // Crear el MessageBody con el código de la sala
            MessageBody body = new MessageBody();
            body.setCodigoSala(codigoSala);

            // Crear el Message con el tipo correspondiente
            Message mensaje = new Message.Builder()
                    .messageType(MessageType.UNIRSE_SALA) // Tipo de mensaje
                    .body(body)
                    .build();

            // Enviar el mensaje al servidor usando ObjectOutputStream
            salida.writeObject(mensaje);  // Enviar el mensaje como objeto
            salida.flush();  // Asegurarse de que se envíe el mensaje
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje para unirse a la partida: " + e.getMessage());
        }
    }

    // Método para desconectarse del servidor
    public void desconectar() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (entrada != null) {
                entrada.close();
            }
            if (salida != null) {
                salida.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public String recibirMensaje() {
    try {
        // Recibir el mensaje como objeto desde el servidor
        Object mensaje = entrada.readObject();
        if (mensaje instanceof String) {
            return (String) mensaje;  // Si es un String, lo devolvemos
        }
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error al recibir mensaje: " + e.getMessage());
    }
    return null;
}
}
