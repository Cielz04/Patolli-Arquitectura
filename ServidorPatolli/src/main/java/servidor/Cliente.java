package servidor;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Cliente {

    ClientConnection conexionCliente;
    
    public Cliente(FrmInicio frameInicio) {
        conexionCliente= new ClientConnection(frameInicio);
    }
    
    public void init(){
        conexionCliente.init();
    }
    
    public void sendMessage(Message mensaje){
        conexionCliente.sendMessage(mensaje);
    }
    
    public void disconnect(String codigoSala){
        MessageBody cuerpo = new MessageBody();
        cuerpo.setCodigoSala(codigoSala);
        
        MessageType tipo = MessageType.DESCONECTARSE;
        Message mensaje = new Message.Builder()
                .messageType(tipo)
                .build();
        conexionCliente.sendMessage(mensaje);
        conexionCliente.disconnect();
    }

}
