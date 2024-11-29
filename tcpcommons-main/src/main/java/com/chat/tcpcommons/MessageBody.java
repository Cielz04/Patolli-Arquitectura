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
    private List<Integer> fichasGatoPosicion= new ArrayList<>();
    private List<Integer> fichasConchaPosicion= new ArrayList<>();
    private List<Integer> fichasPiramidePosicion= new ArrayList<>();
    private List<Integer> fichasMazorcaPosicion= new ArrayList<>();


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

    public List<Integer> getFichasGatoPosicion() {
        return fichasGatoPosicion;
    }

    public void setFichasGatoPosicion(List<Integer> fichasGatoPosicion) {
        this.fichasGatoPosicion = fichasGatoPosicion;
    }

    public List<Integer> getFichasConchaPosicion() {
        return fichasConchaPosicion;
    }

    public void setFichasConchaPosicion(List<Integer> fichasConchaPosicion) {
        this.fichasConchaPosicion = fichasConchaPosicion;
    }

    public List<Integer> getFichasPiramidePosicion() {
        return fichasPiramidePosicion;
    }

    public void setFichasPiramidePosicion(List<Integer> fichasPiramidePosicion) {
        this.fichasPiramidePosicion = fichasPiramidePosicion;
    }

    public List<Integer> getFichasMazorcaPosicion() {
        return fichasMazorcaPosicion;
    }

    public void setFichasMazorcaPosicion(List<Integer> fichasMazorcaPosicion) {
        this.fichasMazorcaPosicion = fichasMazorcaPosicion;
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
