package ar.edu.uca.ingenieria.enviar_notificaciones.manager;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.Message;

/**
 * Provides operations on {@link ar.edu.uca.ingenieria.enviar_notificaciones.model.Message}
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public interface MessageManager {

    void sendMessage(Message message, final NotificationCallback<Message> messageCallback);

}
