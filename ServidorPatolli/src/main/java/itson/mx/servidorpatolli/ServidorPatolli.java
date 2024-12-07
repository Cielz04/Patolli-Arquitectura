package itson.mx.servidorpatolli;


import servidor.IPatolliServer;
import servidor.PatolliServer;

/**
 * Clase principal para iniciar el servidor de Patolli.
 * Esta clase crea una instancia del servidor de Patolli e inicializa el servidor.
 */
public class ServidorPatolli {
 /**
     * Método principal para iniciar el servidor de Patolli.
     * Este método crea una instancia del servidor {@link PatolliServer} y llama
     * al método {@code initServidor()} para inicializar el servidor.
     *
     * @param args los argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        IPatolliServer servidor = new PatolliServer();
        servidor.initServidor();
    }

}
