package ar.edu.uca.ingenieria.enviar_notificaciones.manager;

import java.util.List;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;

/**
 * Provides operations on {@link ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList}
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public interface SubscriptionListManager {

    void getSubscriptionLists(final NotificationCallback<List<SubscriptionList>> notificationCallback);

}
