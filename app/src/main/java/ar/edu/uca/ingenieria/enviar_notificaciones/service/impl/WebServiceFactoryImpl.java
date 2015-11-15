package ar.edu.uca.ingenieria.enviar_notificaciones.service.impl;

import com.google.inject.Inject;

import ar.edu.uca.ingenieria.enviar_notificaciones.service.RestAdapterFactory;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.WebServiceFactory;
import retrofit.RestAdapter;

/**
 * Generic factory for all webservices needed
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class WebServiceFactoryImpl implements WebServiceFactory {

    private RestAdapterFactory restAdapterFactory;

    @Inject
    public WebServiceFactoryImpl(RestAdapterFactory restAdapterFactory) {
        this.restAdapterFactory = restAdapterFactory;
    }

    public <T> T getWebService(Class<T> webServiceClass) {
        RestAdapter restAdapter = this.restAdapterFactory.createRestAdapter();
        return restAdapter.create(webServiceClass);
    }
}
