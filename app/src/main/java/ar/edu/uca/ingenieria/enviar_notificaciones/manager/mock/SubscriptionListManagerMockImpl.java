package ar.edu.uca.ingenieria.enviar_notificaciones.manager.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.uca.ingenieria.enviar_notificaciones.exception.NotificationException;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.NotificationCallback;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.SubscriptionListManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;

/**
 * Mock implementation
 *
 * Created by juanmougan@gmail.com on 11/11/15.
 */
public class SubscriptionListManagerMockImpl implements SubscriptionListManager {

    private Random rn = new Random();

    private List<SubscriptionList> lists = SubscriptionListManagerMockImpl.createSLs();

    public static List<SubscriptionList> createSLs() {
        List<SubscriptionList> lists = new ArrayList<>(3);
        lists.add(new SubscriptionList(1, "Lista Mock 1"));
        lists.add(new SubscriptionList(2, "Lista Mock 2"));
        lists.add(new SubscriptionList(99, "Lista Mock 99"));
        return lists;
    }

    /**
     * Simulates the probability of a failure
     * @return if the method should fail
     */
    private boolean shouldFail() {
        //int rnd = rn.nextInt(10);
        //return rnd % 2 == 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void getSubscriptionLists(final NotificationCallback<List<SubscriptionList>> notificationCallback) {
        if (shouldFail()) {
            notificationCallback.onFailure(new NotificationException("Simulated failure", null));
        } else {
            notificationCallback.onSuccess(lists);
        }
    }
}
