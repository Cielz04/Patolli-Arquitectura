
package Control;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.Message;
import java.io.IOException;
import java.net.Socket;
import servidor.Cliente;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Partida {
    private Cliente cliente;

    // Constructor que inicializa el cliente con una referencia a FrmInicio
    public void conectarse(FrmInicio frameInicio) {
        cliente = new Cliente(frameInicio);
        cliente.init();
    }

    public void desconectar(String codigoSala) {
        if (cliente != null) {
            cliente.disconnect(codigoSala); // Desconecta mediante ClientConnection
        }
    }

    public void enviarMensaje(Message mensaje) {
        if (cliente != null) {
            cliente.sendMessage(mensaje); // Envío del mensaje delegando en ClientConnection
        }
    }

    public Cliente getCliente() {
        return cliente;
    }
}

