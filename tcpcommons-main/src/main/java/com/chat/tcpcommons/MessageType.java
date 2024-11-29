package com.chat.tcpcommons;

/**
 *
 * @author felix
 */
public enum MessageType {
    NOTIFICACION,
    TIRAR_DADO,         // Mensaje para tirar el dado
    MOVER_FICHA,        // Mensaje para mover una ficha
    CONNECTION_MESSAGE, // Mensaje de conexión inicial
    USERS_UPDATE,       // Actualización de usuarios
    CAMBIO_TURNO,       // Notificación de cambio de turno
    RESULTADO_DADO,     // Resultado de tirar el dado
    DISCONNECT,         // Desconexión del cliente
    ERROR              // Mensaje de error
    
    
     
}
