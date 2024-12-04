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
private MessageType messageType;
    private MessageBody body;

    // Constructor, getters y setters
    public Message(MessageType messageType, MessageBody body) {
        this.messageType = messageType;
        this.body = body;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageBody getBody() {
        return body;
    }

    public void setBody(MessageBody body) {
        this.body = body;
    }

    public static class Builder {
        private MessageType messageType;
        private MessageBody body;

        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }

        public Builder body(MessageBody body) {
            this.body = body;
            return this;
        }

        public Message build() {
            return new Message(messageType, body);
        }
    }
}
