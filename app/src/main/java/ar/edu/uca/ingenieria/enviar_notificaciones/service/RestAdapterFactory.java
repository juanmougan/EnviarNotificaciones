package ar.edu.uca.ingenieria.enviar_notificaciones.service;

import android.util.Log;

import com.google.gson.Gson;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static ar.edu.uca.ingenieria.enviar_notificaciones.service.ServiceConstants.*;
import static ar.edu.uca.ingenieria.enviar_notificaciones.service.ServiceConstants.HOST;

/**
 * Factory for Retrofit's webservice client
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class RestAdapterFactory {

    public static final String CONTENT_TYPE = "application/json";
    private static final String ENDPOINT = "http://" + HOST + ":" + PORT + SUFFIX;

    public RestAdapter createRestAdapter() {
        RequestInterceptor requestInterceptor = this.createRequestInterceptor();
        Log.d(RestAdapterFactory.class.getSimpleName(), "Using endpoint: " + ENDPOINT);

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new Gson()))
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(ENDPOINT).build();
    }

    private RequestInterceptor createRequestInterceptor() {
        return new RequestInterceptor() {
            public void intercept(RequestFacade request) {
                String contentTypeValue = CONTENT_TYPE;
                request.addHeader("Content-Type", contentTypeValue);
            }
        };
    }

}
