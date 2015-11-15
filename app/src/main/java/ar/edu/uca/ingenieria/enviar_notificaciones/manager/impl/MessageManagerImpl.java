package ar.edu.uca.ingenieria.enviar_notificaciones.manager.impl;

import android.app.Notification;

import com.google.inject.Inject;

import java.util.List;

import ar.edu.uca.ingenieria.enviar_notificaciones.exception.NotificationException;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.MessageManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.NotificationCallback;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.Message;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.MessageService;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.WebServiceFactory;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Implementation of manager {@link ar.edu.uca.ingenieria.enviar_notificaciones.manager.MessageManager}
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class MessageManagerImpl implements MessageManager {

    private MessageService webService;

    @Inject
    public MessageManagerImpl(WebServiceFactory factory) {
        this.webService = factory.getWebService(MessageService.class);
    }

    @Override
    public void sendMessage(Message message,
                            final NotificationCallback<Message> messageCallback) {
        this.webService.sendMessage(message, new Callback<Message>() {
            @Override
            public void success(Message message, Response response) {
                messageCallback.onSuccess(message);
            }

            @Override
            public void failure(RetrofitError error) {
                messageCallback.onFailure(new NotificationException(
                        Message.class.getSimpleName(), error.getCause()));
            }
        });
    }

}
