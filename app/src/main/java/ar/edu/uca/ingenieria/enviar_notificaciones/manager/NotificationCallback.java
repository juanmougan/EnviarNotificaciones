package ar.edu.uca.ingenieria.enviar_notificaciones.manager;

import ar.edu.uca.ingenieria.enviar_notificaciones.exception.NotificationException;
import retrofit.RetrofitError;

/**
 * Provides an asynchronous way to execute methods
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public interface NotificationCallback<T> {

    /**
     * Will be called if the invoked method executes successfully.<br>
     *
     * @param result - the result to be returned asynchronously.
     */
    void onSuccess(T result);

    /**
     * Will be called if the invoked method executes unsuccessfully.<br>
     *
     * @param error - the error that occurred while running the invoked method.
     */
    void onFailure(NotificationException ex);

}
