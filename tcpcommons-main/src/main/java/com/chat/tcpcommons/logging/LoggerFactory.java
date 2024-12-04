/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons.logging;

/**
 *
 * @author felix
 */
public class LoggerFactory {
    
    public static IChatLogger getLogger(Class c) {
        return ChatLoggerProxy.getInstance(c);
    }
    
}
