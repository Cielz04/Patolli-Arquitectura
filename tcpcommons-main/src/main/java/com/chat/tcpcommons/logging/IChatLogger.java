/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chat.tcpcommons.logging;

/**
 *
 * @author felix
 */
public interface IChatLogger {
    void log(String message);
    void error(String message);
    void info(String message);
    void warning(String message);
}
