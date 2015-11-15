package ar.edu.uca.ingenieria.enviar_notificaciones.manager.impl;

import com.google.inject.Inject;

import java.util.List;

import ar.edu.uca.ingenieria.enviar_notificaciones.exception.NotificationException;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.NotificationCallback;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.SubscriptionListManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.SubscriptionListService;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.WebServiceFactory;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Implementation of manager {@link ar.edu.uca.ingenieria.enviar_notificaciones.manager.SubscriptionListManager}
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class SubscriptionListManagerImpl implements SubscriptionListManager {

    private SubscriptionListService webService;

    @Inject
    public SubscriptionListManagerImpl(WebServiceFactory factory) {
        this.webService = factory.getWebService(SubscriptionListService.class);;
    }

    public void getSubscriptionLists(final NotificationCallback<List<SubscriptionList>> notificationCallback) {
        this.webService.getSubscriptionLists(new Callback<List<SubscriptionList>>() {
            @Override
            public void success(List<SubscriptionList> subscriptionLists, Response response) {
                notificationCallback.onSuccess(subscriptionLists);
            }

            public void failure(RetrofitError error) {
                notificationCallback.onFailure(new NotificationException(
                        SubscriptionList.class.getSimpleName(), error.getCause()));
            }
        });
    }
}
