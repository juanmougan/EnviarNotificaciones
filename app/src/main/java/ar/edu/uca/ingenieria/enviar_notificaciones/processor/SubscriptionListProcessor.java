package ar.edu.uca.ingenieria.enviar_notificaciones.processor;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;

/**
 * Handles processing operations on data related to SubscriptionLists
 *
 * Created by juanmougan@gmail.com on 11/11/15.
 */
public interface SubscriptionListProcessor {

    SubscriptionList[] getSubscriptionListNames();

}
