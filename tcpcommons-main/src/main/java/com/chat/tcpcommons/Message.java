package com.chat.tcpcommons;

import entidades.Jugador;
import java.io.Serializable;

/**
 *
 * @author felix
 */
public class Message implements Serializable {

    private MessageType messageType; // Tipo de mensaje
    private MessageBody content;          // Contenido del mensaje
    private Jugador sender;          // Jugador que envía el mensaje
    private Jugador receiver;

    // Constructor
    public Message(MessageBody content, Jugador sender, Jugador receiver, MessageType messageType) {
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    // Getters y Setters
    public MessageType getMessageType() {
        return messageType;
    }
    
    public void setContent(MessageBody content){
        this.content=content;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageBody getContent() {
        return content;
    }

    public Jugador getSender() {
        return sender;
    }

    public void setSender(Jugador sender) {
        this.sender = sender;
    }

    public Jugador getReceiver() {
        return receiver;
    }

    public void setReceiver(Jugador receiver) {
        this.receiver = receiver;
    }
    
    

    // Builder para crear mensajes de manera fluida
    public static class Builder {
        
        private Jugador receiver;
        private MessageType messageType;
        private MessageBody content;
        private Jugador sender;

        public Builder() {}
        
        public Builder body(MessageBody content) {
            this.content = content;
            return this;
        }
        
        public Builder sender(Jugador sender) {
            this.sender = sender;
            return this;
        }
        
        public Builder receiver(Jugador receiver) {
            this.receiver = receiver;
            return this;
        }
        
        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }
        
        public Message build() {
            return new Message(this.content, this.sender, this.receiver, this.messageType);
        }
    }
}
