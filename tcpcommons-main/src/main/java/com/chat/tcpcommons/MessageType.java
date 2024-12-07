package com.chat.tcpcommons;

/**
 * Enumeración que define los diferentes tipos de mensajes utilizados en el
 * sistema de chat TCP. Cada tipo de mensaje está asociado con un código
 * numérico para facilitar su identificación.
 *
 * <p>
 * Los valores disponibles son:</p>
 * <ul>
 * <li>{@link #CONECTARSE}: Representa un mensaje para establecer conexión
 * inicial.</li>
 * <li>{@link #DESCONECTARSE}: Representa un mensaje para desconectarse del
 * sistema.</li>
 * <li>{@link #UNIRSE_SALA}: Representa un mensaje para unirse a una sala de
 * juego.</li>
 * <li>{@link #CONFIGURAR_TABLERO}: Representa un mensaje para configurar el
 * tablero del juego.</li>
 * <li>{@link #TABLERO_ACTUALIZADO}: Representa un mensaje que indica que el
 * tablero ha sido actualizado.</li>
 * <li>{@link #ERROR}: Representa un mensaje para notificar un error.</li>
 * </ul>
 *
 * <p>
 * Cada tipo está asociado con un número entero único que puede ser utilizado
 * para la serialización o identificación del mensaje.</p>
 */
public enum MessageType {
    /**
     * Representa un mensaje para establecer conexión inicial. Código asociado:
     * 1.
     */
    CONECTARSE(1),
    /**
     * Representa un mensaje para desconectarse del sistema. Código asociado: 2.
     */
    DESCONECTARSE(2),
    /**
     * Representa un mensaje para unirse a una sala de juego. Código asociado:
     * 3.
     */
    UNIRSE_SALA(3),
    /**
     * Representa un mensaje para configurar el tablero del juego. Código
     * asociado: 4.
     */
    CONFIGURAR_TABLERO(4),
    /**
     * Representa un mensaje que indica que el tablero ha sido actualizado.
     * Código asociado: 5.
     */
    TABLERO_ACTUALIZADO(5),
    /**
     * Representa un mensaje para notificar un error. Código asociado: 6.
     */
    ERROR(6);

    private final int type;

    /**
     * Constructor privado para inicializar el tipo de mensaje con su código
     * asociado.
     *
     * @param type Código numérico del tipo de mensaje.
     */
    private MessageType(int type) {
        this.type = type;
    }

}
