package ar.edu.uca.ingenieria.enviar_notificaciones.service;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Factory for Retrofit's webservice client
 *
 * Created by juanmougan@gmail.com on 13/11/15.
 */
public class RestAdapterFactory {

    public static final String CONTENT_TYPE = "application/json";
    private static final String ENDPOINT = "http://localhost:3000";

    public RestAdapter createRestAdapter() {
        RequestInterceptor requestInterceptor = this.createRequestInterceptor();

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
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
