package com.chat.tcpcommons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author felix
 */
public abstract class Observable {

    protected List<IObserver> observers = new ArrayList<>();

    public void subscribe(IObserver observer) {
        this.observers.add(observer);
    }

    public void unsubscribe(IObserver observer) {
        this.observers.remove(observer);
    }

    public abstract void notifyObservers(Object obj);   
}
