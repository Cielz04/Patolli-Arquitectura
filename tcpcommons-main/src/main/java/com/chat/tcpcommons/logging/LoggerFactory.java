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
