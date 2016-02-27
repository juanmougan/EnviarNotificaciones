package ar.edu.uca.ingenieria.enviar_notificaciones.model;

import com.google.gson.annotations.SerializedName;

/**
 * Notification that will be sent to a given SubscriptionList
 *
 * Created by juanmougan@gmail.com on 10/11/15.
 */
public class Message {

    private String title;

    private String message;

    @SerializedName("subscription_list")
    private int subscriptionListId;

    public Message(String title, String message, int subscriptionListId) {
        this.title = title;
        this.message = message;
        this.subscriptionListId = subscriptionListId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSubscriptionListId() {
        return subscriptionListId;
    }

    public void setSubscriptionListId(int subscriptionListId) {
        this.subscriptionListId = subscriptionListId;
    }

    @Override
    public String toString() {
        return "Título: " + this.title + "\tMensaje: " + this.message;
    }
}
