package com.chat.tcpcommons;


import com.chat.tcpcommons.Message;

/**
 *
 * @author Hector Espinoza
 */
public interface TemplateConnection {
    
    default void proccessMessage(Message message) {
        switch (message.getMessageType()) {
            case CONECTARSE ->onConectarse(message);
            case CREAR_SALA ->onCrearSala(message);
            case UNIRSE_SALA ->onUnirseSala(message);
            case PASAR_OPCIONES ->onPasarOpciones(message);
            case PASAR_CAMBIOS ->onPasarCambios(message);
            case PASAR_JUGADORES ->onPasarJugadores(message);
            case DESCONECTARSE ->onDisconnect(message);
        }
    }
    
    void onConectarse(Message message);
    
    void onCrearSala(Message message);
    
    void onUnirseSala(Message message);
    
    void onPasarOpciones(Message message);
    
    void onPasarJugadores(Message message);

    void onPasarCambios(Message message);
    
    default void onDisconnect(Message message){}
    
}
