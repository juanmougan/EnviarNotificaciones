package ar.edu.uca.ingenieria.enviar_notificaciones.processor;

/**
 * Mock implementation
 *
 * Created by juanmougan@gmail.com on 11/11/15.
 */
public class SubscriptionListProcessorMockImpl implements SubscriptionListProcessor {

    private static String[] NAMES = {"Mock todos Info", "Mock industrial", "Mock Quimica General"};

    @Override
    public String[] getSubscriptionListNames() {
        return NAMES;
    }
}
