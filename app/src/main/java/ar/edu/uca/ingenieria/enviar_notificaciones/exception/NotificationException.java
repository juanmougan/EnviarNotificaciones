package ar.edu.uca.ingenieria.enviar_notificaciones.exception;

/**
 * Business exception for this application
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class NotificationException extends RuntimeException {
    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
