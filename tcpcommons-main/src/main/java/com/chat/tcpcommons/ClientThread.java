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
public class ClientThread extends Observable implements Runnable, Serializable {

    private final Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Jugador jugador;
    private boolean connected;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connected = true;
        this.jugador = new Jugador();
        initializeStreams(); // Inicializar flujos de entrada y salida
    }

    private void initializeStreams() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
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
            processMessage(); // Procesar mensajes
            recibirMensaje();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processMessage() throws Exception {
        while (connected) {
            Message mensaje = (Message) in.readObject(); // Leer mensaje
            if (mensaje.getMessageType() == MessageType.DESCONECTARSE) {
                connected = false;
                disconnect();
            }
            notifyObservers(mensaje); // Notificar a los observadores (ControlPatolli)
        }
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message); // Enviar mensaje al servidor
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            sendMessage(new Message.Builder().sender(jugador).messageType(MessageType.DESCONECTARSE).build());
            connected = false;
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj)); // Notificar a los observadores
    }

    // Método para recibir el mensaje
    public Message recibirMensaje() {
        try {
            return (Message) in.readObject(); // Leer y retornar el mensaje recibido
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

//////////    private final Socket clientSocket;
//////////    private ObjectInputStream in;
//////////    private ObjectOutputStream out;
//////////    private Jugador jugador;
//////////    private boolean connected;
//////////
//////////    public ClientThread(Socket clientSocket) {
//////////        this.clientSocket = clientSocket;
//////////        this.connected = true;
//////////        this.jugador = new Jugador();
//////////        initializeStreams();
//////////    }
//////////
//////////    private void initializeStreams() {
//////////        try {
//////////            out = new ObjectOutputStream(clientSocket.getOutputStream());
//////////            in = new ObjectInputStream(clientSocket.getInputStream());
//////////        } catch (IOException e) {
//////////            connected = false;
//////////        }
//////////    }
//////////
//////////    public void setJugador(Jugador jugador) {
//////////        this.jugador = jugador;
//////////    }
//////////
//////////    public Jugador getJugador() {
//////////        return jugador;
//////////    }
//////////
//////////    @Override
//////////    public void run() {
//////////        try {
//////////            processMessage();
//////////        } catch (Exception ex) {
//////////            ex.printStackTrace();
//////////        }
//////////    }
//////////
//////////    private void processMessage() throws Exception {
//////////        while (connected) {
//////////            Message mensaje = (Message) in.readObject();
//////////            if (mensaje.getMessageType() == MessageType.DESCONECTARSE) {
//////////                connected = false;
//////////                disconnect();
//////////            }
//////////            notifyObservers(mensaje); // Notificar a los observadores (ControlPatolli)
//////////        }
//////////    }
//////////
//////////    public void sendMessage(Message message) {
//////////        try {
//////////            out.writeObject(message);
//////////            out.flush();
//////////        } catch (IOException ex) {
//////////            ex.printStackTrace();
//////////        }
//////////    }
//////////
//////////    public void disconnect() {
//////////        try {
//////////            sendMessage(new Message.Builder().sender(jugador).messageType(MessageType.DESCONECTARSE).build());
//////////            connected = false;
//////////            in.close();
//////////            out.close();
//////////            clientSocket.close();
//////////        } catch (IOException e) {
//////////            e.printStackTrace();
//////////        }
//////////    }
//////////
//////////    @Override
//////////    public void notifyObservers(Object obj) {
//////////        observers.forEach(o -> o.onUpdate(obj)); // Notificar a todos los observadores (ControlPatolli)
//////////    }
//    private final Socket clientSocket;
//    private ObjectInputStream in;
//    private ObjectOutputStream out;
//    private final IChatLogger logger = LoggerFactory.getLogger(getClass());
//    private Jugador jugador;
//    private boolean connected;
//
//    public ClientThread(Socket clientSocket) {
//        this.clientSocket = clientSocket;
//        this.connected = true;
//        this.jugador = new Jugador();
//        initializeStreams();
//    }
//
//    private void initializeStreams() {
//        try {
//            out = new ObjectOutputStream(clientSocket.getOutputStream());
//            in = new ObjectInputStream(clientSocket.getInputStream());
//        } catch (IOException e) {
//            logger.error("Error al inicializar flujos: " + e.getMessage());
//            connected = false;
//        }
//    }
//
//    public void setJugador(Jugador jugador) {
//        this.jugador = jugador;
//    }
//
//    public Jugador getJugador() {
//        return jugador;
//    }
//
//    @Override
//    public void run() {
//        try {
//            processMessage();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            logger.error(ex.getMessage());
//        }
//    }
//
//    private void processMessage() throws Exception{
//        while (connected) {
//            Message mensaje = (Message) in.readObject();
//            //if (mensaje.getMessageType() == TipoMensaje.CONECTARSE) {
//            //    usuario = mensaje.getSender();
//            //    mensaje.setMessageType(TipoMensaje.ACTUALIZAR_USUARIO);
//            //}
//            if (mensaje.getMessageType() == MessageType.DESCONECTARSE) {
//                connected = false;
//                disconnect();
//            }
//            notifyObservers(mensaje);
//        }
//    }
//
////    private void tirarDado() {
////        if (gameState.getJugadorEnTurno().equals(jugador)) {
////            int diceResult = (int) (Math.random() * 6) + 1;
////            logger.info("Jugador " + jugador.getNombre() + " tiró el dado: " + diceResult);
////
////            notifyObservers(new Message(
////                    MessageType.RESULTADO_DADO,
////                    "Resultado: " + diceResult,
////                    jugador
////            ));
////
////            gameState.siguienteTurno();
////            notifyObservers(new Message(
////                    MessageType.CAMBIO_TURNO,
////                    "Es el turno de: " + gameState.getJugadorEnTurno().getNombre(),
////                    null
////            ));
////        } else {
////            sendMessage(new Message(
////                    MessageType.ERROR,
////                    "No es tu turno.",
////                    null
////            ));
////        }
////    }
////
////    private void handleMovePiece(Message message) {
////        if (gameState.getJugadorEnTurno().equals(jugador)) {
////            // Procesar movimiento de ficha aquí
////            logger.info("Movimiento procesado para " + jugador.getNombre());
////        } else {
////            sendMessage(new Message(MessageType.ERROR, "No es tu turno.", null));
////        }
////    }
//
//    public void sendMessage(Message message) {
//        if (message != null) {
//            System.out.println("Mensaje enviado: " + message.getMessageType());
//        }
//        try {
//            out.writeObject(message);
//            out.flush();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            logger.error(ex.getMessage());
//        }
//    }
//
//    public void disconnect() {
//        try {
//            sendMessage(new Message.Builder().sender(jugador).messageType(MessageType.DESCONECTARSE).build());
//            connected = false;
//            in.close();
//            out.close();
//            clientSocket.close();
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        }
//    }
//
//    @Override
//    public void notifyObservers(Object obj) {
//        observers.forEach(o -> o.onUpdate(obj));
//
//    }
}
