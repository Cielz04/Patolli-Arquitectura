/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author felix
 */

public class ConnectionTemplate {

    private Socket socket;
    private String serverAddress;
    private int serverPort;

    public ConnectionTemplate(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public Socket connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        return socket;
    }
}
