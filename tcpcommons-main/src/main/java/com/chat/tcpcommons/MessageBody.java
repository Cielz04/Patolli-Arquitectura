package com.chat.tcpcommons;

import entidades.Jugador;
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
    private int cantidadFichas;
    private List <Integer>  fichasJugador1Posicion;
    private List <Integer>  fichasJugador2Posicion;
    private List <Integer>  fichasJugador3Posicion;
    private List <Integer>  fichasJugador4Posicion;
    private int jugadorTurno = 0;
    private int cantidadJugadores;
    private boolean juegoTermino = false;
    private boolean juegoInicia = false;
    private int apuesta;
    private List <Integer> cantidadMontoJugadores;
    private int cantidadCasillasAspa;
    private List <Jugador> jugadores;
    private boolean salaEspera = true;
    private int numJugador; 
    private Tablero tablero;
//    private Tablero estadoTablero;

    
    private MessageBody body;

    public MessageBody(Tablero tablero) {
        this.tablero = tablero;
    }

    public MessageBody(String mensaje, Tablero tablero) {
        this.mensaje = mensaje;
        this.tablero = tablero;
    }

    public MessageBody() {
    }

   public int getNumJugador() {
        return numJugador;
    }

    // Setter para numJugador
    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }
   
    

    // Método getBody() que retorna el objeto MessageBody
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    


//    public Tablero getEstadoTablero() {
//        return estadoTablero;
//    }

//    public void setEstadoTablero(Tablero estadoTablero) {
//        this.estadoTablero = estadoTablero;
//    }

    public int getCantidadFichas() {
        return cantidadFichas;
    }

    public void setCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    public List<Integer> getFichasJugador1Posicion() {
        return fichasJugador1Posicion;
    }

    public void setFichasJugador1Posicion(List<Integer> fichasJugador1Posicion) {
        this.fichasJugador1Posicion = fichasJugador1Posicion;
    }

    public List<Integer> getFichasJugador2Posicion() {
        return fichasJugador2Posicion;
    }

    public void setFichasJugador2Posicion(List<Integer> fichasJugador2Posicion) {
        this.fichasJugador2Posicion = fichasJugador2Posicion;
    }

    public List<Integer> getFichasJugador3Posicion() {
        return fichasJugador3Posicion;
    }

    public void setFichasJugador3Posicion(List<Integer> fichasJugador3Posicion) {
        this.fichasJugador3Posicion = fichasJugador3Posicion;
    }

    public List<Integer> getFichasJugador4Posicion() {
        return fichasJugador4Posicion;
    }

    public void setFichasJugador4Posicion(List<Integer> fichasJugador4Posicion) {
        this.fichasJugador4Posicion = fichasJugador4Posicion;
    }

    public int getJugadorTurno() {
        return jugadorTurno;
    }

    public void setJugadorTurno(int jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public boolean isJuegoTermino() {
        return juegoTermino;
    }

    public void setJuegoTermino(boolean juegoTermino) {
        this.juegoTermino = juegoTermino;
    }

    public boolean isJuegoInicia() {
        return juegoInicia;
    }

    public void setJuegoInicia(boolean juegoInicia) {
        this.juegoInicia = juegoInicia;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public List<Integer> getCantidadMontoJugadores() {
        return cantidadMontoJugadores;
    }

    public void setCantidadMontoJugadores(List<Integer> cantidadMontoJugadores) {
        this.cantidadMontoJugadores = cantidadMontoJugadores;
    }

    public int getCantidadCasillasAspa() {
        return cantidadCasillasAspa;
    }

    public void setCantidadCasillasAspa(int cantidadCasillasAspa) {
        this.cantidadCasillasAspa = cantidadCasillasAspa;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean isSalaEspera() {
        return salaEspera;
    }

    public void setSalaEspera(boolean salaEspera) {
        this.salaEspera = salaEspera;
    }

    public Tablero getTablero() {
        return tablero;
    }
    
    
    
    
    
    

  
    
    
    
    
    
    
}
