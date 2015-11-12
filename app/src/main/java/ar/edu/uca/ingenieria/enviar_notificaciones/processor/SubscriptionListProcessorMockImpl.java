package ar.edu.uca.ingenieria.enviar_notificaciones.processor;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;

/**
 * Mock implementation
 *
 * Created by juanmougan@gmail.com on 11/11/15.
 */
public class SubscriptionListProcessorMockImpl implements SubscriptionListProcessor {

    private SubscriptionList[] lists = SubscriptionListProcessorMockImpl.createSLs();

    public static SubscriptionList[] createSLs() {
        SubscriptionList[] lists = new SubscriptionList[3];
        lists[0] = new SubscriptionList(1, "Lista Mock 1");
        lists[1] = new SubscriptionList(1, "Lista Mock 2");
        lists[2] = new SubscriptionList(99, "Lista Mock 99");
        return lists;
    }

    @Override
    public SubscriptionList[] getSubscriptionListNames() {
        return this.lists;
    }
}
