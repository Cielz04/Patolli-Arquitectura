package com.chat.tcpcommons;

import entidades.Jugador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import tablero.Tablero;

/**
 * Clase que representa el cuerpo del mensaje intercambiado entre el servidor y
 * el cliente en el contexto del juego. Esta clase contiene toda la información
 * relevante sobre el estado del juego, las posiciones de las fichas de los
 * jugadores, el mensaje a enviar, y otras propiedades relacionadas con el flujo
 * del juego.
 *
 * Esta clase implementa la interfaz {@link Serializable} para permitir su
 * serialización, facilitando su transmisión a través de la red.
 *
 */
public class MessageBody implements Serializable {

    private String mensaje;
    private int cantidadFichas;
    private List<Integer> fichasJugador1Posicion;
    private List<Integer> fichasJugador2Posicion;
    private List<Integer> fichasJugador3Posicion;
    private List<Integer> fichasJugador4Posicion;
    private int jugadorTurno = 0;
    private int cantidadJugadores;
    private boolean juegoTermino = false;
    private boolean juegoInicia = false;
    private int apuesta;
    private List<Integer> cantidadMontoJugadores;
    private int cantidadCasillasAspa;
    private List<Jugador> jugadores;
    private boolean salaEspera = true;
    private int numJugador;
    private Tablero tablero;
//    private Tablero estadoTablero;

    private MessageBody body;

    /**
     * Constructor que inicializa un objeto MessageBody con un tablero de juego.
     *
     * @param tablero El tablero actual del juego.
     */
    public MessageBody(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Constructor que inicializa un objeto MessageBody con un mensaje y un
     * tablero de juego.
     *
     * @param mensaje El mensaje a enviar.
     * @param tablero El tablero actual del juego.
     */
    public MessageBody(String mensaje, Tablero tablero) {
        this.mensaje = mensaje;
        this.tablero = tablero;
    }

    /**
     * Constructor por defecto, sin parámetros.
     */
    public MessageBody() {
    }

    /**
     * Obtiene el número de jugador asociado a este objeto MessageBody.
     *
     * @return El número de jugador.
     */
    public int getNumJugador() {
        return numJugador;
    }

    /**
     * Establece el número de jugador asociado a este objeto MessageBody.
     *
     * @param numJugador El número de jugador.
     */
    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }

    /**
     * Obtiene el cuerpo del mensaje actual.
     *
     * @return El objeto MessageBody actual.
     */
    public MessageBody getBody() {
        return body;
    }
//    public MessageBody(String mensaje, Tablero estadoTablero) {
//        this.mensaje = mensaje;
////        this.estadoTablero = estadoTablero;
//    }

    public MessageBody(String mensaje) {
        this.mensaje = mensaje;
    }

//    public MessageBody(Tablero estadoTablero) {
//        this.estadoTablero = estadoTablero;
//    }
    /**
     * Obtiene el mensaje asociado a este objeto MessageBody.
     *
     * @return El mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje asociado a este objeto MessageBody.
     *
     * @param mensaje El mensaje a establecer.
     */

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

//    public Tablero getEstadoTablero() {
//        return estadoTablero;
//    }
//    public void setEstadoTablero(Tablero estadoTablero) {
//        this.estadoTablero = estadoTablero;
//    }
    /**
     * Obtiene la cantidad de fichas del juego.
     *
     * @return La cantidad de fichas.
     */
    public int getCantidadFichas() {
        return cantidadFichas;
    }

    /**
     * Establece la cantidad de fichas del juego.
     *
     * @param cantidadFichas La cantidad de fichas a establecer.
     */
    public void setCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    /**
     * Obtiene la lista de posiciones de las fichas del jugador 1.
     *
     * @return La lista de posiciones de las fichas del jugador 1.
     */
    public List<Integer> getFichasJugador1Posicion() {
        return fichasJugador1Posicion;
    }

    /**
     * Establece la lista de posiciones de las fichas del jugador 1.
     *
     * @param fichasJugador1Posicion La lista de posiciones a establecer.
     */
    public void setFichasJugador1Posicion(List<Integer> fichasJugador1Posicion) {
        this.fichasJugador1Posicion = fichasJugador1Posicion;
    }

    /**
     * Obtiene la lista de posiciones de las fichas del jugador 2.
     *
     * @return La lista de posiciones de las fichas del jugador 2.
     */
    public List<Integer> getFichasJugador2Posicion() {
        return fichasJugador2Posicion;
    }

    /**
     * Establece la lista de posiciones de las fichas del jugador 2.
     *
     * @param fichasJugador2Posicion La lista de posiciones a establecer.
     */
    public void setFichasJugador2Posicion(List<Integer> fichasJugador2Posicion) {
        this.fichasJugador2Posicion = fichasJugador2Posicion;
    }

    /**
     * Obtiene la lista de posiciones de las fichas del jugador 3.
     *
     * @return La lista de posiciones de las fichas del jugador 3.
     */
    public List<Integer> getFichasJugador3Posicion() {
        return fichasJugador3Posicion;
    }

    /**
     * Establece la lista de posiciones de las fichas del jugador 3.
     *
     * @param fichasJugador3Posicion La lista de posiciones a establecer.
     */
    public void setFichasJugador3Posicion(List<Integer> fichasJugador3Posicion) {
        this.fichasJugador3Posicion = fichasJugador3Posicion;
    }

    /**
     * Obtiene la lista de posiciones de las fichas del jugador 4.
     *
     * @return La lista de posiciones de las fichas del jugador 4.
     */
    public List<Integer> getFichasJugador4Posicion() {
        return fichasJugador4Posicion;
    }

    /**
     * Establece la lista de posiciones de las fichas del jugador 4.
     *
     * @param fichasJugador4Posicion La lista de posiciones a establecer.
     */
    public void setFichasJugador4Posicion(List<Integer> fichasJugador4Posicion) {
        this.fichasJugador4Posicion = fichasJugador4Posicion;
    }

    /**
     * Obtiene el número de turno del jugador actual.
     *
     * @return El número de turno.
     */
    public int getJugadorTurno() {
        return jugadorTurno;
    }

    /**
     * Establece el número de turno del jugador actual.
     *
     * @param jugadorTurno El número de turno a establecer.
     */
    public void setJugadorTurno(int jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    /**
     * Obtiene la cantidad de jugadores que participan en el juego.
     *
     * @return La cantidad de jugadores.
     */
    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    /**
     * Establece la cantidad de jugadores que participan en el juego.
     *
     * @param cantidadJugadores La cantidad de jugadores a establecer.
     */
    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    /**
     * Determina si el juego ha terminado.
     *
     * @return {@code true} si el juego ha terminado, {@code false} en caso
     * contrario.
     */
    public boolean isJuegoTermino() {
        return juegoTermino;
    }

    /**
     * Establece si el juego ha terminado.
     *
     * @param juegoTermino {@code true} si el juego ha terminado, {@code false}
     * en caso contrario.
     */
    public void setJuegoTermino(boolean juegoTermino) {
        this.juegoTermino = juegoTermino;
    }

    /**
     * Determina si el juego ha comenzado.
     *
     * @return {@code true} si el juego ha comenzado, {@code false} en caso
     * contrario.
     */
    public boolean isJuegoInicia() {
        return juegoInicia;
    }

    /**
     * Establece si el juego ha comenzado.
     *
     * @param juegoInicia {@code true} si el juego ha comenzado, {@code false}
     * en caso contrario.
     */
    public void setJuegoInicia(boolean juegoInicia) {
        this.juegoInicia = juegoInicia;
    }

    /**
     * Obtiene la apuesta realizada en el juego.
     *
     * @return El valor de la apuesta.
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Establece el valor de la apuesta en el juego.
     *
     * @param apuesta El valor de la apuesta a establecer.
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Obtiene la lista con los montos de cada jugador.
     *
     * @return Una lista de enteros que representa los montos de los jugadores.
     */
    public List<Integer> getCantidadMontoJugadores() {
        return cantidadMontoJugadores;
    }

    /**
     * Establece la lista con los montos de cada jugador.
     *
     * @param cantidadMontoJugadores Una lista de enteros que representa los
     * montos de los jugadores.
     */

    public void setCantidadMontoJugadores(List<Integer> cantidadMontoJugadores) {
        this.cantidadMontoJugadores = cantidadMontoJugadores;
    }

    /**
     * Obtiene la cantidad de casillas que componen cada aspa del tablero.
     *
     * @return La cantidad de casillas por aspa.
     */
    public int getCantidadCasillasAspa() {
        return cantidadCasillasAspa;
    }

    /**
     * Establece la cantidad de casillas que componen cada aspa del tablero.
     *
     * @param cantidadCasillasAspa La cantidad de casillas por aspa a
     * establecer.
     */
    public void setCantidadCasillasAspa(int cantidadCasillasAspa) {
        this.cantidadCasillasAspa = cantidadCasillasAspa;
    }

    /**
     * Obtiene la lista de jugadores que participan en el juego.
     *
     * @return Una lista de objetos {@link Jugador}.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Establece la lista de jugadores que participan en el juego.
     *
     * @param jugadores Una lista de objetos {@link Jugador} que representan a
     * los jugadores.
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Indica si el juego está en sala de espera.
     *
     * @return {@code true} si el juego está en sala de espera, {@code false} en
     * caso contrario.
     */
    public boolean isSalaEspera() {
        return salaEspera;
    }

    /**
     * Establece si el juego está en sala de espera.
     *
     * @param salaEspera {@code true} para indicar que el juego está en sala de
     * espera, {@code false} en caso contrario.
     */
    public void setSalaEspera(boolean salaEspera) {
        this.salaEspera = salaEspera;
    }

    /**
     * Obtiene el tablero asociado al estado del juego.
     *
     * @return Un objeto {@link Tablero} que representa el tablero del juego.
     */
    public Tablero getTablero() {
        return tablero;
    }

}
