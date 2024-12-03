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
