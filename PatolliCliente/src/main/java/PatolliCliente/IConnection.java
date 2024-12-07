package PatolliCliente;

import com.chat.tcpcommons.Message;


/**
 *
 * @author felix
 */
public interface IConnection {
    
    void sendMessage(Message message);
//    void closeInbox(InboxChat inbox);
//    void openInbox(User friend);
    void init();
    default void disconnect() {};
}
