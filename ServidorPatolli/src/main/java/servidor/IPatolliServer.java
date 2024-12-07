package servidor;

import java.util.Observer;

/**
 * Interfaz que define las operaciones necesarias para un servidor de Patolli.
 * Esta interfaz permite al servidor gestionar los observadores (como clientes)
 * y notificarles sobre los cambios en el estado del juego.
 *
 * Los métodos proporcionados en esta interfaz permiten inicializar el servidor,
 * agregar y eliminar observadores, y notificar a los observadores cuando el
 * estado del servidor cambia.
 */
public interface IPatolliServer {

    /**
     * Inicializa el servidor, preparándolo para aceptar conexiones de clientes.
     * Este método puede incluir la configuración de puertos, la creación de
     * hilos de servidor, y la configuración inicial del estado del juego.
     */
    void initServidor();

    /**
     * Agrega un observador al servidor. El observador puede ser, por ejemplo,
     * un cliente que desee recibir notificaciones sobre los cambios en el
     * estado del juego.
     *
     * @param o el observador que se va a agregar.
     */
    void agregarObservador(Observer o);

    /**
     * Elimina un observador previamente agregado. Después de llamar a este
     * método, el observador dejará de recibir notificaciones sobre el estado
     * del servidor.
     *
     * @param o el observador que se va a eliminar.
     */
    void eliminarObservador(Observer o);

    /**
     * Notifica a todos los observadores registrados sobre un cambio en el
     * estado del servidor. Los observadores recibirán un objeto que representa
     * el nuevo estado.
     *
     * @param estado el nuevo estado que debe ser notificado a los observadores.
     */
    void notificarObservadores(Object estado);

}
