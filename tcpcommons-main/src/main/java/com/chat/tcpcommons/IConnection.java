package com.chat.tcpcommons;


/**
 *
 * @author felix
 */
public interface IConnection {
    
    void sendMessage(Message message);
    void init();
    default void disconnect() {};
}
