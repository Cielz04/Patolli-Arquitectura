package itson.mx.servidorpatolli;

import servidor.Cliente;
import servidor.Servidor;
import java.io.IOException;
import javax.swing.JOptionPane;
import servidor.IPatolliServer;
import servidor.PatolliServer;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class ServidorPatolli {

    public static void main(String[] args) {
        IPatolliServer servidor=new PatolliServer();
        servidor.initServidor();
    }

}
