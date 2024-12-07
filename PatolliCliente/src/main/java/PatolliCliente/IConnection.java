package PatolliCliente;

import com.chat.tcpcommons.Message;

/**
 * Interfaz IConnection. Define los métodos necesarios para gestionar la
 * conexión de un cliente, incluyendo el envío de mensajes, la inicialización y
 * la desconexión.
 *
 */
public interface IConnection {

    /**
     * Envía un mensaje al servidor o a otro cliente.
     *
     * @param message el mensaje a enviar, de tipo {@link Message}.
     */
    void sendMessage(Message message);
//    void closeInbox(InboxChat inbox);
//    void openInbox(User friend);

    /**
     * Inicializa la conexión y los recursos necesarios para el cliente.
     */
    void init();

    /**
     * Desconecta al cliente y libera los recursos asociados. Este método tiene
     * una implementación predeterminada que puede ser sobreescrita.
     */
    default void disconnect() {
    }
;
}
