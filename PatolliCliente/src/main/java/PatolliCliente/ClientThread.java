package PatolliCliente;

import com.chat.tcpcommons.IObserver;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import com.chat.tcpcommons.Observable;
import entidades.Jugador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Observable implements Runnable, Serializable {

    private final Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Jugador jugador;
    private boolean connected;
    private final List<IObserver> observers;

    public ClientThread(Socket clientSocket, Jugador jugador) {
        this.clientSocket = clientSocket;
        this.jugador = jugador;
        this.connected = true;
        this.observers = new ArrayList<>();
        initializeStreams();
    }

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

    @Override
    public void run() {
        try {
            // Enviar mensaje de conexión al servidor
            sendMessage(new Message.Builder()
                    .messageType(MessageType.CONECTARSE)
                    .sender(jugador)
                    .body(new MessageBody("Solicitud de conexión"))
                    .build());

            // Escuchar mensajes del servidor
            while (connected) {
                try {
                    Message mensaje = (Message) in.readObject();
                    if (mensaje != null) {
                        notifyObservers(mensaje); // Notificar al observador (ClienteControlador)
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Error al leer el mensaje: Clase no encontrada.");
                } catch (IOException e) {
                    System.err.println("Error de conexión al servidor: " + e.getMessage());
                    break; // Salir del bucle si hay problemas de conexión
                }
            }
        } finally {
            disconnect(); // Siempre desconecta para limpiar recursos
        }
    }

    public void sendMessage(Message mensaje) {
        try {
            out.writeObject(mensaje);
            out.flush();
        } catch (Exception e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
        }
    }

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

    @Override
    public void notifyObservers(Object obj) {
        for (var observer : observers) {
            observer.onUpdate(obj);
        }
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public Jugador getJugador() {
        return jugador;
    }

}
