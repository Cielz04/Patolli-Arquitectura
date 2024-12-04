package com.chat.tcpcommons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import tablero.Tablero;

/**
 *
 * @author Hector Espinoza
 */
public class MessageBody implements Serializable{
    
    private String mensaje;
    private Tablero estadoTablero;

    public MessageBody() {
    }

    public MessageBody(String mensaje, Tablero estadoTablero) {
        this.mensaje = mensaje;
        this.estadoTablero = estadoTablero;
    }

    public MessageBody(String mensaje) {
        this.mensaje = mensaje;
    }

    public MessageBody(Tablero estadoTablero) {
        this.estadoTablero = estadoTablero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Tablero getEstadoTablero() {
        return estadoTablero;
    }

    public void setEstadoTablero(Tablero estadoTablero) {
        this.estadoTablero = estadoTablero;
    }
    
    
    

  
    
    
    
    
    
    
}
