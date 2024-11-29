/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons;

import entidades.Jugador;
import java.io.Serializable;

/**
 *
 * @author felix
 */
public class Message implements Serializable {

    private MessageType messageType; // Tipo de mensaje
    private Object content;          // Contenido del mensaje
    private Jugador sender;          // Jugador que envía el mensaje

    // Constructor
    public Message(MessageType messageType, Object content, Jugador sender) {
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
    }

    // Getters y Setters
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Jugador getSender() {
        return sender;
    }

    public void setSender(Jugador sender) {
        this.sender = sender;
    }

    // Builder para crear mensajes de manera fluida
    public static class Builder {
        private MessageType messageType;
        private Object content;
        private Jugador sender;

        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }

        public Builder content(Object content) {
            this.content = content;
            return this;
        }

        public Builder sender(Jugador sender) {
            this.sender = sender;
            return this;
        }

        public Message build() {
            return new Message(messageType, content, sender);
        }
    }
}
