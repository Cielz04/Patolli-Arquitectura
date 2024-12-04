package itson.mx.servidorpatolli;

//import Cliente.Cliente;
import Servidor.Servidor;
/**
 *
 * @author esmeraldamolinaestrada
 */
public class ServidorPatolli {

    public static void main(String[] args) {
        Servidor servidor = new Servidor(50056);
        servidor.iniciar();
    }

}
