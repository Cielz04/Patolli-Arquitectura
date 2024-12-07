package servidor;

import java.util.Observer;

/**
 *
 * @author Hector Espinoza
 */
public interface IPatolliServer {
    
    void initServidor();
    void agregarObservador(Observer o);
    void eliminarObservador(Observer o);
    void notificarObservadores(Object estado);
    
}
