package servidor;

import java.util.Observer;

/**
 * Implementación de la interfaz {@link IPatolliServer}, que representa un
 * servidor de Patolli. Esta clase gestiona la inicialización del servidor y la
 * conexión de los clientes, aunque los métodos relacionados con la notificación
 * a observadores aún no están implementados.
 *
 * El servidor se encarga de recibir conexiones, manejar el flujo de información
 * entre los jugadores y actualizar el estado del juego. Sin embargo, algunos
 * métodos de la interfaz {@link IPatolliServer} están actualmente sin
 * implementar y lanzan {@link UnsupportedOperationException}.
 *
 */
public class PatolliServer implements IPatolliServer {

    Servidor servidor;

    /**
     * Constructor de la clase {@link PatolliServer}. Inicializa una nueva
     * instancia de {@link Servidor}, que es responsable de la implementación de
     * la lógica central del juego de Patolli.
     */
    public PatolliServer() {
        servidor = new Servidor();
    }

    /**
     * Inicializa el servidor y comienza a escuchar las conexiones de los
     * clientes. Este método delega la responsabilidad de la inicialización del
     * servidor a la instancia {@link Servidor}.
     */
    @Override
    public void initServidor() {
        servidor.init();
    }

    /**
     * Notifica a los observadores sobre un cambio en el estado del servidor.
     * Este método aún no está implementado, por lo que lanza una excepción
     * {@link UnsupportedOperationException}.
     *
     * @param estado el nuevo estado que debe ser notificado a los observadores.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void notificarObservadores(Object estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Agrega un observador para que reciba notificaciones sobre el estado del
     * servidor. Este método aún no está implementado, por lo que lanza una
     * excepción {@link UnsupportedOperationException}.
     *
     * @param o el observador que se va a agregar.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void agregarObservador(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Elimina un observador previamente agregado para que ya no reciba
     * notificaciones. Este método aún no está implementado, por lo que lanza
     * una excepción {@link UnsupportedOperationException}.
     *
     * @param o el observador que se va a eliminar.
     * @throws UnsupportedOperationException si se invoca este método.
     */
    @Override
    public void eliminarObservador(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
