package ar.edu.uca.ingenieria.enviar_notificaciones.config;

import com.google.inject.AbstractModule;

import ar.edu.uca.ingenieria.enviar_notificaciones.manager.MessageManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.SubscriptionListManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.impl.MessageManagerImpl;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.impl.SubscriptionListManagerImpl;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.WebServiceFactory;
import ar.edu.uca.ingenieria.enviar_notificaciones.service.impl.WebServiceFactoryImpl;

/**
 * Provides all Guice bindings
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class WebServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(WebServiceFactory.class).to(WebServiceFactoryImpl.class);
        bind(SubscriptionListManager.class).to(SubscriptionListManagerImpl.class);
        bind(MessageManager.class).to(MessageManagerImpl.class);
    }
}
