/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons.logging;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author felix
 */
class ChatLoggerProxy implements IChatLogger, Serializable {

    private static IChatLogger instance;
    private IChatLogger logger;
    private Class clazz;

    private ChatLoggerProxy(Class clazz) {
        this.clazz = clazz;
        this.logger = ChatLoggerImpl.getInstance(clazz);
    }

    public static IChatLogger getInstance(Class clazz) {
        return instance != null ? instance : (instance = new ChatLoggerProxy(clazz));
    }

    @Override
    public void log(String message) {
        JOptionPane.showMessageDialog(null, message, "success", JOptionPane.DEFAULT_OPTION);
        logger.log(message);
    }

    @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(null, message, "error", JOptionPane.ERROR_MESSAGE);
        logger.error(message);
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message, "info", JOptionPane.INFORMATION_MESSAGE);
        logger.info(message);
    }

    @Override
    public void warning(String message) {
        JOptionPane.showMessageDialog(null, message, "warning", JOptionPane.WARNING_MESSAGE);
        logger.warning(message);
    }

}
