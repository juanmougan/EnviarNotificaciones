package ar.edu.uca.ingenieria.enviar_notificaciones.service;

import java.util.List;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface for the SubscriptionList service
 *
 * Created by juanmougan@gmail.com on 10/11/15.
 */
public interface SubscriptionListService {

    @GET("/subscriptionlists")
    void getSubscriptionLists(Callback<List<SubscriptionList>> subscriptionLists);

}
