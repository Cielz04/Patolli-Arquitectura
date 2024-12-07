package com.chat.tcpcommons;

/**
 *
 * @author felix
 */
public enum MessageType {
    CONECTARSE(1),
    DESCONECTARSE(2),
    UNIRSE_SALA(3),
    CONFIGURAR_TABLERO(4),
    TABLERO_ACTUALIZADO(5),
    ERROR(6);
    
    private final int type;
    
    private MessageType(int type) {
        this.type = type;
    } 
    
    
     
}
