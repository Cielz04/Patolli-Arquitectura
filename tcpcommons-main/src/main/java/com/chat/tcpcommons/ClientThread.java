package com.chat.tcpcommons;

import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.*;


/**
 *
 * @author felix
 */
public class ClientThread extends Observable implements Runnable {
    private final Socket clientSocket;
    private final EstadoDelJuego gameState;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final IChatLogger logger = LoggerFactory.getLogger(getClass());
    private Jugador jugador;
    private boolean connected;

    public ClientThread(Socket clientSocket, EstadoDelJuego gameState) {
        this.clientSocket = clientSocket;
        this.gameState = gameState;
        this.connected = true;
        initializeStreams();
    }

    private void initializeStreams() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            logger.error("Error al inicializar flujos: " + e.getMessage());
            connected = false;
        }
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    @Override
    public void run() {
        try {
            logger.info("Cliente conectado: " + clientSocket.getInetAddress());

            
            jugador = (Jugador) in.readObject(); 
            synchronized (gameState) {
                gameState.getJugadores().add(jugador); 
                logger.info("Jugador registrado: " + jugador.getNombre());
            }

            while (connected) {
                Message message = (Message) in.readObject(); 
                processMessage(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void processMessage(Message message) {
        switch (message.getMessageType()) {
            case TIRAR_DADO:
                tirarDado();
                break;
            case MOVER_FICHA:
                handleMovePiece(message);
                break;
            case DISCONNECT:
                disconnect();
                break;
            default:
                logger.warning("Mensaje desconocido recibido: " + message);
        }
    }

    private void tirarDado() {
        if (gameState.getJugadorEnTurno().equals(jugador)) {
        int diceResult = (int) (Math.random() * 6) + 1;
        logger.info("Jugador " + jugador.getNombre() + " tiró el dado: " + diceResult);

        
        notifyObservers(new Message(
            MessageType.RESULTADO_DADO,
            "Resultado: " + diceResult,
            jugador
        ));

        gameState.siguienteTurno(); 
        notifyObservers(new Message(
            MessageType.CAMBIO_TURNO,
            "Es el turno de: " + gameState.getJugadorEnTurno().getNombre(),
            null
        ));
    } else {
        sendMessage(new Message(
            MessageType.ERROR,
            "No es tu turno.",
            null
        ));
    }
    }

    private void handleMovePiece(Message message) {
        if (gameState.getJugadorEnTurno().equals(jugador)) {
            // Procesar movimiento de ficha aquí
            logger.info("Movimiento procesado para " + jugador.getNombre());
        } else {
            sendMessage(new Message(MessageType.ERROR, "No es tu turno.", null));
        }
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            logger.error("Error al enviar mensaje: " + e.getMessage());
        }
    }

    public void disconnect() {
        connected = false;
        try {
            synchronized (gameState) {
                gameState.getJugadores().remove(jugador);
                logger.info("Jugador desconectado: " + jugador.getNombre());
            }
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            logger.error("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj));

    }
}
