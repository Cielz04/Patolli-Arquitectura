package com.chat.tcpcommons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hector Espinoza
 */
public class MessageBody implements Serializable{
    
    private String razonDesconexion;
    private String codigoSala;
    private List<Integer> montoJugadores= new ArrayList<>();
    private int jugador;
    private int tamaño;
    private int monto; 
    private int fichas;
    private int jugadores;
    private boolean existeSala;
    private List<Integer> fichasJugador2Posicion= new ArrayList<>();
    private List<Integer> fichasJugador1Posicion= new ArrayList<>();
    private List<Integer> fichasJugador3Posicion= new ArrayList<>();
    private List<Integer> fichasJugador4Posicion= new ArrayList<>();


    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public List<Integer> getMontoJugadores() {
        return montoJugadores;
    }

    public void setMontoJugadores(List<Integer> montoJugadores) {
        this.montoJugadores = montoJugadores;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public List<Integer> getFichasJugador2Posicion() {
        return fichasJugador2Posicion;
    }

    public void setFichasJugador2Posicion(List<Integer> fichasJugador2Posicion) {
        this.fichasJugador2Posicion = fichasJugador2Posicion;
    }

    public List<Integer> getFichasJugador1Posicion() {
        return fichasJugador1Posicion;
    }

    public void setFichasJugador1Posicion(List<Integer> fichasJugador1Posicion) {
        this.fichasJugador1Posicion = fichasJugador1Posicion;
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

    public boolean isExisteSala() {
        return existeSala;
    }

    public void setExisteSala(boolean existeSala) {
        this.existeSala = existeSala;
    }

    public String getRazonDesconexion() {
        return razonDesconexion;
    }

    public void setRazonDesconexion(String razonDesconexion) {
        this.razonDesconexion = razonDesconexion;
    }
    
}
