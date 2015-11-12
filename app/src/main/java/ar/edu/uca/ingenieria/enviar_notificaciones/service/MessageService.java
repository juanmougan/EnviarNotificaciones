package ar.edu.uca.ingenieria.enviar_notificaciones.service;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.Message;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Interface for the Message service
 *
 * Created by juanmougan@gmail.com on 10/11/15.
 */
public interface MessageService {

    @POST("/notifications")
    Response sendMessage(@Body Message message);

}
