package com.chat.tcpcommons;

import entidades.Jugador;
import java.io.Serializable;
import tablero.Tablero;

public class Message implements Serializable {

    private MessageType messageType;  // Tipo de mensaje
    private MessageBody content;           // Contenido del mensaje (ahora genérico)
    private Jugador sender;           // Jugador que envía el mensaje
    private Jugador receiver;         // Jugador que recibe el mensaje

    // Constructor actualizado
    public Message(MessageType messageType, MessageBody content, Jugador sender, Jugador receiver) {
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    // Getters y Setters
    public MessageType getMessageType() {
        return messageType;
    }

    public void setContent(MessageBody content) {
        this.content = content;
    }

    public MessageBody getContent() {
        return content;
    }

    public Jugador getSender() {
        if (sender == null) {
            System.err.println("El jugador no está configurado correctamente.");
        }
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
            return new Message(this.messageType, this.content, this.sender, this.receiver);
        }
    }
}
