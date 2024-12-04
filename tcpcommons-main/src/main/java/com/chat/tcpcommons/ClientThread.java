package com.chat.tcpcommons;

import Servidor.Servidor;
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
public class ClientThread implements Runnable {

    private Socket clienteSocket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClientThread(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        try {
            this.salida = new PrintWriter(clienteSocket.getOutputStream(), true);
            this.entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error al inicializar streams: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String mensaje;
        try {
            // Escuchar los mensajes del cliente
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido del cliente: " + mensaje);

                // Procesar el mensaje basado en el tipo de acción
                if (mensaje.startsWith("UNIRSE:")) {
                    String codigoPartida = mensaje.split(":")[1];
                    salida.println("Te has unido a la partida " + codigoPartida);
                } else if (mensaje.startsWith("CREAR:")) {
                    String configuracion = mensaje.split(":")[1];
                    salida.println("Partida creada con configuración: " + configuracion);
                } else if (mensaje.startsWith("ACCION:")) {
                    String accion = mensaje.split(":")[1];
                    salida.println("Acción procesada: " + accion);  // Responder al cliente con la acción procesada
                } else {
                    salida.println("Comando desconocido.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
            }
        }
    }

    // Método para enviar mensajes al cliente
    public void enviarMensaje(String mensaje) {
        if (salida != null) {
            salida.println(mensaje); // Envía el mensaje al cliente
        }
    }
}