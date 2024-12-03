
package Control;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.Message;
import java.io.IOException;
import java.net.Socket;
import servidor.Cliente;
import tablero.Tablero;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Partida {
    
    private Cliente cliente;
    private Tablero tablero; // Representa el estado local del tablero

    public void conectarse(FrmInicio frameInicio) {
        cliente = new Cliente(frameInicio);
        cliente.init();
    }

    public void desconectar(String codigoSala) {
        if (cliente != null) {
            cliente.disconnect(codigoSala);
        }
    }

    public void enviarMensaje(Message mensaje) {
        if (cliente != null) {
            cliente.sendMessage(mensaje);
        }
    }

    // Método para obtener el tablero
    public Tablero getTablero() {
        if (tablero == null) {
            tablero = new Tablero();
        }
        return tablero;
    }

    // Método para inicializar el tablero
    public void inicializarTablero(Tablero nuevoTablero) {
        this.tablero = nuevoTablero;
    }

    // Método para recibir mensajes
    public Message recibirMensaje() throws IOException {
        return cliente.recibirMensaje(); // Delegar la recepción al cliente
    }

    // Obtener el cliente
    public Cliente getCliente() {
        return cliente;
    }
    
//    private Cliente cliente;
//    private Tablero tablero; // Representa el estado local del tablero
//
//    public void conectarse(FrmInicio frameInicio) {
//        cliente = new Cliente(frameInicio);
//        cliente.init();
//    }
//
//    public void desconectar(String codigoSala) {
//        if (cliente != null) {
//            cliente.disconnect(codigoSala);
//        }
//    }
//
//    public void enviarMensaje(Message mensaje) {
//        if (cliente != null) {
//            cliente.sendMessage(mensaje);
//        }
//    }
//
//    // Método para acceder al tablero
//    public Tablero getTablero() {
//        if (tablero==null){
//            tablero = new Tablero();
//        }
//        return tablero;
//    }
//
//    // Método para inicializar el tablero
//    public void inicializarTablero(Tablero nuevoTablero) {
//        this.tablero = nuevoTablero;
//    }
//    
//    public Message recibirMensaje() {
//        try {
//            return cliente.recibirMensaje(); // Lógica de recepción desde el servidor
//        } catch (IOException e) {
//            System.err.println("Error al recibir mensaje: " + e.getMessage());
//            return null;
//        }
//    }
}

