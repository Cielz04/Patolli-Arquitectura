package Control;

import com.chat.tcpcommons.Message;

/**
 *
 * @author esmeraldamolinaestrada
 */
public interface IControlPatolli {
    
    public void conectarse();
    public void desconectar(String codigoSala, int miJugador);
    public void enviarMensaje(Message mensaje);
    
}
