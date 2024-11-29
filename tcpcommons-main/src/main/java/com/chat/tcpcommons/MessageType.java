/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons;

/**
 *
 * @author felix
 */
public enum MessageType {
    TIRAR_DADO,         // Mensaje para tirar el dado
    MOVER_FICHA,        // Mensaje para mover una ficha
    CONNECTION_MESSAGE, // Mensaje de conexión inicial
    USERS_UPDATE,       // Actualización de usuarios
    CAMBIO_TURNO,       // Notificación de cambio de turno
    RESULTADO_DADO,     // Resultado de tirar el dado
    DISCONNECT,         // Desconexión del cliente
    ERROR              // Mensaje de error
    
    
     
}
