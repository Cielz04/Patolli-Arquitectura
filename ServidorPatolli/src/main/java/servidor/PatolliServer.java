package servidor;

import java.util.Observer;

/**
 *
 * @author Hector Espinoza
 */
public class PatolliServer implements IPatolliServer {

    Servidor servidor;

    public PatolliServer() {
        servidor = new Servidor();
    }

    @Override
    public void initServidor() {
        servidor.init();
    }

    @Override
    public void notificarObservadores(Object estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarObservador(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarObservador(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
