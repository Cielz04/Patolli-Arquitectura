package Control;

import Pantallas.FrmInicio;
import com.chat.tcpcommons.Message;

/**
 *
 * @author esmeraldamolinaestrada
 */
public interface IControlCliente {
    
    public void conectarse(FrmInicio frameInicio);
    public void desconectar(String codigoSala, int miJugador);
    public void enviarMensaje(Message mensaje);
    
}
