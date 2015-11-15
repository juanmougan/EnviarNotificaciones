package ar.edu.uca.ingenieria.enviar_notificaciones.manager.mock;

import java.util.Random;

import ar.edu.uca.ingenieria.enviar_notificaciones.exception.NotificationException;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.MessageManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.NotificationCallback;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.Message;

/**
 * Mock implementation
 *
 * Created by juanmougan@gmail.com on 15/11/15.
 */
public class MessageManagerMockImpl implements MessageManager {

    private Random rn = new Random();

    /**
     * Simulates the probability of a failure
     * @return if the method should fail
     */
    private boolean shouldFail() {
        int rnd = rn.nextInt(10);
        return rnd % 2 == 0;
    }

    @Override
    public void sendMessage(Message message, NotificationCallback<Message> messageCallback) {
        if (shouldFail()) {
            messageCallback.onFailure(new NotificationException("Simulated failure", null));
        } else {
            messageCallback.onSuccess(message);
        }
    }
}
