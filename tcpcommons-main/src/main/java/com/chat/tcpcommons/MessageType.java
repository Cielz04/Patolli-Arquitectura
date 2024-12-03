package com.chat.tcpcommons;

/**
 *
 * @author felix
 */
public enum MessageType {
    CONECTARSE(1),
    DESCONECTARSE(2),
    CREAR_SALA(3),
    UNIRSE_SALA(4),
    PASAR_OPCIONES(5),
    PASAR_CAMBIOS(6),
    PASAR_JUGADORES(7),
    ESTADO_TABLERO(8);
    
    private final int type;
    
    private MessageType(int type) {
        this.type = type;
    } 
    
    
     
}
