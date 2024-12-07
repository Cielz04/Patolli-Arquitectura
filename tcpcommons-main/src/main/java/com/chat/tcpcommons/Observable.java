package com.chat.tcpcommons;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que implementa el patrón de diseño Observer.
 *
 * <p>
 * La clase {@code Observable} permite a los objetos interesados (observadores)
 * suscribirse para recibir notificaciones de cambios o eventos relevantes.</p>
 *
 * <p>
 * Los objetos que hereden de esta clase deberán implementar el método
 * {@link #notifyObservers(Object)}, que define cómo y cuándo se notifica a los
 * observadores.</p>
 *
 * <p>
 * Este diseño es útil para sistemas que requieren comunicación entre
 * componentes de manera desacoplada.</p>
 *
 */
public abstract class Observable {

    protected List<IObserver> observers = new ArrayList<>();

    /**
     * Suscribe un observador para que reciba notificaciones de cambios.
     *
     * @param observer El objeto que implementa la interfaz {@link IObserver} y
     * desea recibir notificaciones.
     */
    public void subscribe(IObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Cancela la suscripción de un observador, dejando de recibir
     * notificaciones.
     *
     * @param observer El objeto que implementa la interfaz {@link IObserver} y
     * desea dejar de recibir notificaciones.
     */
    public void unsubscribe(IObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Método abstracto que debe ser implementado por las clases hijas para
     * definir la lógica de notificación a los observadores.
     *
     * @param obj El objeto que contiene la información relevante para los
     * observadores. Puede ser {@code null} si no es necesario pasar información
     * adicional.
     */
    public abstract void notifyObservers(Object obj);
}
