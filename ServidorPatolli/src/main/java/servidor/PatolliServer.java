package servidor;

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

}
