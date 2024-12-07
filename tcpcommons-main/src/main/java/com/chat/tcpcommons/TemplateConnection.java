package com.chat.tcpcommons;

import PatolliCliente.ClientThread;
import com.chat.tcpcommons.Message;

/**
 * Interfaz que define el esquema para procesar mensajes en una conexión
 * cliente-servidor.
 *
 * <p>
 * Esta interfaz proporciona un método principal
 * {@link #proccessMessage(Message, ClientThread)} que actúa como una plantilla
 * para manejar diferentes tipos de mensajes recibidos. Además, define métodos
 * abstractos que deben implementarse para gestionar cada tipo de mensaje.</p>
 *
 * <p>
 * Implementaciones de esta interfaz deben definir la lógica específica para
 * cada evento relacionado con los tipos de mensaje definidos en
 * {@link MessageType}.</p>
 */
public interface TemplateConnection {

    /**
     * Procesa un mensaje recibido y ejecuta la acción correspondiente según su
     * tipo.
     *
     * <p>
     * Este método utiliza un bloque {@code switch} basado en el tipo de mensaje
     * definido por {@link MessageType} y delega el manejo a métodos
     * específicos.</p>
     *
     * @param message El objeto {@link Message} recibido, que contiene los datos
     * del mensaje.
     * @param client El objeto {@link ClientThread} que representa al cliente
     * desde el cual se recibió el mensaje.
     */
    default void proccessMessage(Message message, ClientThread client) {
        switch (message.getMessageType()) {
            case CONECTARSE ->
                onConectarse(message, client);
            case DESCONECTARSE ->
                onDesconectarse(message, client);
            case UNIRSE_SALA ->
                onUnirseSala(message, client);
            case CONFIGURAR_TABLERO ->
                onConfigurarTablero(message, client);
            case TABLERO_ACTUALIZADO ->
                onActualizarTablero(message, client);
            case ERROR ->
                onError(message, client);
        }
    }

    /**
     * Maneja el evento de conexión de un cliente.
     *
     * @param message El mensaje recibido que contiene los detalles de la
     * conexión.
     * @param client El cliente que solicita la conexión.
     */
    void onConectarse(Message message, ClientThread client);

    /**
     * Maneja el evento de desconexión de un cliente.
     *
     * @param message El mensaje recibido que indica la desconexión.
     * @param client El cliente que se está desconectando.
     */
    void onDesconectarse(Message message, ClientThread client);

    /**
     * Maneja el evento de un cliente uniéndose a una sala.
     *
     * @param message El mensaje recibido que contiene los detalles de la sala.
     * @param client El cliente que solicita unirse a la sala.
     */
    void onUnirseSala(Message message, ClientThread client);

    /**
     * Maneja la configuración inicial del tablero del juego.
     *
     * @param message El mensaje recibido con los detalles de configuración del
     * tablero.
     * @param client El cliente que solicita configurar el tablero.
     */
    void onConfigurarTablero(Message message, ClientThread client);

    /**
     * Maneja las actualizaciones realizadas en el tablero del juego.
     *
     * @param message El mensaje recibido con los detalles de la actualización.
     * @param client El cliente que envía la actualización del tablero.
     */
    void onActualizarTablero(Message message, ClientThread client);

    /**
     * Maneja los mensajes que representan errores en la comunicación o en el
     * estado del juego.
     *
     * @param message El mensaje recibido que contiene los detalles del error.
     * @param client El cliente afectado por el error.
     */
    void onError(Message message, ClientThread client);

}
