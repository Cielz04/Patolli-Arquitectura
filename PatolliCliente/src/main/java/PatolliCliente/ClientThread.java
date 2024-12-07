package PatolliCliente;

import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.Observable;
import entidades.Jugador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ClientThread que maneja la conexión entre un cliente y el servidor en
 * un juego. Implementa las interfaces {@link Runnable}, {@link Serializable}, y
 * {@link IObserver}. Se utiliza un patrón de observador para notificar eventos
 * a los observadores registrados.
 */
public class ClientThread extends Observable implements Runnable, Serializable, IObserver {

    private static ClientThread hiloCliente;
    private final Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Jugador jugador;
    private boolean connected;
    private final List<IObserver> observers;
    private ClienteControlador cliente;
//    private boolean connected = true;

    /**
     * Constructor de la clase ClientThread. Inicializa un hilo cliente para
     * gestionar la conexión con el servidor y el jugador asociado.
     *
     * @param clientSocket el socket que representa la conexión del cliente con
     * el servidor.
     * @param jugador el objeto {@link Jugador} asociado a este cliente.
     */
    public ClientThread(Socket clientSocket, Jugador jugador) {
        this.clientSocket = clientSocket;
        this.jugador = jugador;
        this.connected = true;
        this.observers = new ArrayList<>();
        initializeStreams();
    }

    /**
     * Devuelve la instancia única de {@code ClientThread}, creando una nueva si
     * no existe.
     *
     * @param clientSocket el socket que representa la conexión del cliente con
     * el servidor.
     * @param jugador el objeto {@link Jugador} asociado a este cliente.
     * @return la instancia única de {@code ClientThread}.
     */
    public static ClientThread getInstance(Socket clientSocket, Jugador jugador) {
        if (hiloCliente == null) {
            hiloCliente = new ClientThread(clientSocket, jugador);
        }
        return hiloCliente;
    }

    /**
     * Inicializa los flujos de entrada y salida asociados al cliente. Establece
     * los streams en el orden correcto.
     */
    private void initializeStreams() {
        try {
            // Inicializar flujos en el orden correcto
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            connected = false;
            System.err.println("Error al inicializar los flujos del cliente: " + e.getMessage());
        }
    }

    /**
     * Método que ejecuta el hilo del cliente. Escucha mensajes del servidor y
     * los procesa. Notifica a los observadores cuando se reciben nuevos
     * mensajes.
     */
    @Override
    public void run() {

        try {

            while (connected) {
                Message mensaje = (Message) in.readObject();

                if (mensaje != null) {
                    System.out.println("Mensaje de servidor recibido " + mensaje.getContent().getMensaje());
                    notifyObservers(mensaje);

                }

            }
        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada al leer mensaje: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            connected = false; // Aquí decides desconectar si el error es crítico
        } finally {
            cerrarConexion();
        }
    }

    /**
     * Cierra la conexión del cliente, incluyendo los flujos de entrada/salida y
     * el socket.
     */
    private void cerrarConexion() {
        try {
            if (in != null) {
                in.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

//        try {
//
//            // Enviar mensaje de conexión al servidor
//            sendMessage(new Message.Builder()
//                    .messageType(MessageType.CONECTARSE)
//                    .sender(jugador)
//                    .body(new MessageBody("Solicitud de conexión"))
//                    .build());
//
//            subscribe(cliente);
//            observers.add(cliente);
//
//            // Escuchar mensajes del servidor
//            while (connected) {
//                try {
//                    Message mensaje = (Message) in.readObject();
//                    if (mensaje != null) {
//                        notifyObservers(mensaje); // Notificar al observador (ClienteControlador)
//                    }
//                } catch (ClassNotFoundException e) {
//                    System.err.println("Error al leer el mensaje: Clase no encontrada.");
//                } catch (IOException e) {
//                    System.err.println("Error de conexión al servidor: " + e.getMessage());
//                    break; // Salir del bucle si hay problemas de conexión
//                }
//            }
//        } finally {
//            disconnect(); // Siempre desconecta para limpiar recursos
//        }
//    }
    /**
     * Envía un mensaje al servidor a través del flujo de salida.
     *
     * @param mensaje el objeto {@link Message} que se enviará al servidor.
     */
    public void sendMessage(Message mensaje) {
        if (!connected) {
            System.err.println("El cliente no está conectado. No se puede enviar el mensaje.");
            return;
        }
        try {
            out.writeObject(mensaje);
            out.flush();
            System.out.println("Mensaje enviado al servidor: " + mensaje.getContent().getMensaje());
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
            connected = false;
        }
    }

//    public void sendMessage(Message mensaje) {
//        try {
//            out.writeObject(mensaje);
//            out.flush();
//        } catch (Exception e) {
//            System.err.println("Error al enviar mensaje: " + e.getMessage());
//        }
//    }
    /**
     * Desconecta el cliente, cerrando flujos y el socket.
     */
    public void disconnect() {
        try {
            connected = false;
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            System.err.println("Error al desconectar el cliente: " + e.getMessage());
        }
    }

    /**
     * Notifica a todos los observadores registrados un evento o mensaje
     * recibido.
     *
     * @param obj el objeto que se notificará a los observadores.
     */
    @Override
    public void notifyObservers(Object obj) {
        for (var observer : observers) {
            observer.onUpdate(obj);
        }
    }

    /**
     * Agrega un observador a la lista de observadores registrados.
     *
     * @param observer el observador que se desea agregar.
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Elimina un observador de la lista de observadores registrados.
     *
     * @param observer el observador que se desea eliminar.
     */
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * Obtiene el jugador asociado a este hilo cliente.
     *
     * @return el objeto {@link Jugador} asociado.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Método llamado cuando se recibe una actualización desde otro observable.
     *
     * @param obj el objeto que se actualizó.
     */
    @Override
    public void onUpdate(Object obj) {
        notifyObservers(obj);
    }
    
    /**
     * Devuelve el flujo de entrada del cliente.
     *
     * @return el flujo de entrada {@link ObjectInputStream}.
     */
    public ObjectInputStream getInputStream() {
        return in;
    }

}
