package com.chat.tcpcommons;

import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import java.io.IOException;

/**
 *
 * @author felix
 */
public class ClientThread extends Observable implements Runnable, Serializable, IObserver {

    private transient Socket clientSocket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;
    private transient IChatLogger logger = LoggerFactory.getLogger(getClass());
    private Jugador jugador;  // Asociar un jugador al cliente
    private EstadoDelJuego gameState; // Referencia al estado del juego


    private boolean connected;

    public ClientThread(Socket clientSocket, EstadoDelJuego gameState) {
        this.clientSocket = clientSocket;
        this.gameState = gameState;
        this.connected = true;
        init();
    }
    
    public ClientThread(String host, int port) {
        try {
            connected = true;
            clientSocket = new Socket(host, port);
            init();
        } catch (Exception ex) {
            logger.error(String.format("Error al conectarse al servidor: %s", ex.getMessage()));
        }
    }
   

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj));
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void disconnect() {
        try {
            sendMessage(new Message.Builder().sender(jugador).messageType(MessageType.DISCONNECT).build());
            connected = false;
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            processMessage();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    private void init() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    private void processMessage() throws Exception {
        while (connected) {
            Message message = (Message) in.readObject();

            // Validar acciones de juego
            if (message.getMessageType() == MessageType.TIRAR_DADO || message.getMessageType() == MessageType.MOVER_FICHA) {
                if (gameState.getJugadorEnTurno().equals(jugador)) {
                    processGameAction(message);  // Procesa la acción si es el turno del jugador
                } else {
                    sendMessage(new Message.Builder()
                            .messageType(MessageType.ERROR)
                            .content("No es tu turno.")
                            .build());
                }
            }

            // Procesar desconexión
            if (message.getMessageType() == MessageType.DISCONNECT) {
                connected = false;
                disconnect();
            }

            notifyObservers(message);
        }
    }

    private void processGameAction(Message message) {
        // Lógica para procesar acciones de juego
        if (message.getMessageType() == MessageType.TIRAR_DADO) {
            int resultado = tirarDado();
            logger.info("Jugador " + jugador.getNombre() + " tiró el dado y obtuvo: " + resultado);

            // Notificar a todos sobre el resultado
            notifyObservers(new Message.Builder()
                    .messageType(MessageType.RESULTADO_DADO)
                    .content("Jugador " + jugador.getNombre() + " obtuvo: " + resultado)
                    .build());
        }

        // Al final del turno, cambiar al siguiente jugador
        gameState.siguienteTurno();
        notifyObservers(new Message.Builder()
                .messageType(MessageType.CAMBIO_TURNO)
                .content("Es el turno de: " + gameState.getJugadorEnTurno().getNombre())
                .build());
    }
    
    private int tirarDado() {
        return (int) (Math.random() * 6) + 1; // Número entre 1 y 6
    }

    @Override
    public void onUpdate(Object obj) {
        try {
            if (obj instanceof Message) {
                sendMessage((Message) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void enviarEstadoJuego(EstadoDelJuego estadoDelJuego) {
    try {
        out.writeObject(estadoDelJuego); // out es ObjectOutputStream
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
        logger.error("Error al enviar estado del juego: " + e.getMessage());
    }
}
}
