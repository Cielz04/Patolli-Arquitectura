
package Control;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.Message;
import servidor.Cliente;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Partida {
    
        Cliente cliente;
    
     public void conectarse(FrmInicio frameInicio) {
        cliente=new Cliente(frameInicio);
        cliente.init();
    }
    
    public void desconectar(String codigoSala, int miJugador){
        cliente.disconnect(codigoSala);
    }
    
    public void enviarMensaje(Message mensaje){
        cliente.sendMessage(mensaje);
    }
}
