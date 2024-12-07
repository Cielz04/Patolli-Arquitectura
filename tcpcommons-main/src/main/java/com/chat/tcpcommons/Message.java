package com.chat.tcpcommons;

import entidades.Jugador;
import java.io.Serializable;
import tablero.Tablero;

/**
 * La clase {@code Message} representa un mensaje que se intercambia entre
 * jugadores en el sistema. Este mensaje puede contener diversos tipos de
 * información como el tipo de mensaje, el contenido del mensaje, y las
 * referencias a los jugadores involucrados (emisor y receptor). Además, soporta
 * un patrón de diseño Builder para facilitar la creación de mensajes.
 *
 * La clase implementa {@link Serializable} para permitir que los mensajes sean
 * transmitidos a través de redes o guardados en almacenamiento persistente.
 */
public class Message implements Serializable {

    private MessageType messageType;
    private MessageBody content;
    private Jugador sender;
    private Jugador receiver;
    private MessageBody body;

    /**
     * Constructor de la clase {@code Message}.
     *
     * @param messageType El tipo de mensaje (ej. ERROR, MOVIMIENTO, etc.)
     * @param content El contenido del mensaje, que describe la acción o el
     * dato.
     * @param sender El jugador que envía el mensaje.
     * @param receiver El jugador que recibe el mensaje.
     */
    public Message(MessageType messageType, MessageBody content, Jugador sender, Jugador receiver) {
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Obtiene el tipo de mensaje.
     *
     * @return El tipo de mensaje.
     */
    public MessageType getMessageType() {
        return messageType;
    }

    // Constructor adicional para manejar el número del jugador
    public Message(int numJugador) {
        this.numJugador = numJugador;
    }

    /**
     * Establece el contenido del mensaje. Si el contenido es nulo, se asigna un
     * valor por defecto.
     *
     * @param content El contenido del mensaje.
     */
    public void setContent(MessageBody content) {
        if (content == null) {
            System.err.println("Advertencia: Se está intentando asignar un contenido nulo.");
            this.content = new MessageBody("Contenido por defecto");
        } else {
            this.content = content;
        }
    }

    /**
     * Obtiene el contenido del mensaje.
     *
     * @return El contenido del mensaje.
     */
    public MessageBody getContent() {
        return content;
    }

    /**
     * Obtiene el jugador que envía el mensaje.
     *
     * @return El jugador emisor del mensaje.
     */
    public Jugador getSender() {
        if (sender == null) {
            System.err.println("El jugador no está configurado correctamente.");
        }
        return sender;
    }

    /**
     * Establece el jugador que envía el mensaje.
     *
     * @param sender El jugador que envía el mensaje.
     */
    public void setSender(Jugador sender) {
        this.sender = sender;
    }

    /**
     * Obtiene el jugador que recibe el mensaje.
     *
     * @return El jugador receptor del mensaje.
     */
    public Jugador getReceiver() {
        return receiver;
    }

    /**
     * Obtiene el cuerpo del mensaje.
     *
     * @return El cuerpo del mensaje.
     */
    public MessageBody getBody() {
        return body;
    }

    /**
     * Establece el jugador que recibe el mensaje.
     *
     * @param receiver El jugador que recibe el mensaje.
     */
    public void setReceiver(Jugador receiver) {
        this.receiver = receiver;
    }

    private int numJugador; // Número de jugado
    // Getter para obtener el número del jugador

    public int getNumJugador() {
        return numJugador;
    }

    /**
     * Clase interna que implementa el patrón Builder para crear mensajes de
     * forma fluida. Permite configurar el contenido del mensaje, el emisor, el
     * receptor y el tipo de mensaje antes de construir la instancia final del
     * mensaje.
     */
    public static class Builder {

        private Jugador receiver;
        private MessageType messageType;
        private MessageBody content;
        private Jugador sender;

        public Builder() {
        }

        /**
         * Establece el cuerpo del mensaje.
         *
         * @param content El contenido del mensaje.
         * @return La instancia del Builder.
         */
        public Builder body(MessageBody content) {
            this.content = content;
            return this;
        }

        /**
         * Establece el jugador que envía el mensaje.
         *
         * @param sender El jugador que envía el mensaje.
         * @return La instancia del Builder.
         */
        public Builder sender(Jugador sender) {
            this.sender = sender;
            return this;
        }

        /**
         * Establece el jugador que recibe el mensaje.
         *
         * @param receiver El jugador que recibe el mensaje.
         * @return La instancia del Builder.
         */
        public Builder receiver(Jugador receiver) {
            this.receiver = receiver;
            return this;
        }

        /**
         * Establece el tipo de mensaje.
         *
         * @param messageType El tipo de mensaje.
         * @return La instancia del Builder.
         */
        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }

        /**
         * Construye la instancia final del mensaje con los valores configurados
         * en el Builder.
         *
         * @return La instancia construida de {@code Message}.
         */
        public Message build() {
            return new Message(this.messageType, this.content, this.sender, this.receiver);
        }

    }

}
