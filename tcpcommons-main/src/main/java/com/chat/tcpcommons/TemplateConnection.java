package com.chat.tcpcommons;


import PatolliCliente.ClientThread;
import com.chat.tcpcommons.Message;

/**
 *
 * @author Hector Espinoza
 */
public interface TemplateConnection {
    
    default void proccessMessage(Message message, ClientThread client) {
        switch (message.getMessageType()) {
            case CONECTARSE ->onConectarse(message, client);
            case DESCONECTARSE ->onDesconectarse(message, client);
            case UNIRSE_SALA ->onUnirseSala(message, client);
            case CONFIGURAR_TABLERO ->onConfigurarTablero(message, client);
            case TABLERO_ACTUALIZADO ->onActualizarTablero(message, client);
            case ERROR ->onError(message, client);
        }
    }
    
    void onConectarse(Message message, ClientThread client);
    
    void onDesconectarse(Message message, ClientThread client);
    
    void onUnirseSala(Message message, ClientThread client);
    
    void onConfigurarTablero(Message message, ClientThread client);
    
    void onActualizarTablero(Message message, ClientThread client);

    void onError(Message message, ClientThread client);
    
}
