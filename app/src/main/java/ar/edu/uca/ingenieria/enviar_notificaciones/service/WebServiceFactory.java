package ar.edu.uca.ingenieria.enviar_notificaciones.service;

/**
 * Factory for Retrofit's webservice client
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public interface WebServiceFactory {

    <T> T getWebService(Class<T> webServiceClass);

}
