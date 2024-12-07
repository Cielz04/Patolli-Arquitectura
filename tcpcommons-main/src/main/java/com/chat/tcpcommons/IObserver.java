package com.chat.tcpcommons;

/**
 * La interfaz {@code IObserver} define el contrato para las clases que desean
 * recibir actualizaciones sobre eventos o cambios en el estado de un objeto
 * observado. Las clases que implementen esta interfaz deberán proporcionar
 * la implementación del método {@code onUpdate} para manejar las actualizaciones.
 * 
 * Este patrón de diseño es parte del patrón Observador, donde los objetos
 * observadores son notificados de cambios en los objetos observables.
 */
public interface IObserver {
    
    /**
     * Este método es invocado para notificar a un observador sobre una
     * actualización en el objeto observado.
     * 
     * @param obj El objeto que contiene la nueva información o el cambio de estado.
     */
    void onUpdate(Object obj);
}
