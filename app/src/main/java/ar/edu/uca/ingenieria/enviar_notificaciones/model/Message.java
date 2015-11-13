package ar.edu.uca.ingenieria.enviar_notificaciones.model;

/**
 * Notification that will be sent to a given SubscriptionList
 *
 * Created by juanmougan@gmail.com on 10/11/15.
 */
public class Message {

    private String title;

    private String message;

    private SubscriptionList deliverToList;

    public Message(String title, String message, SubscriptionList deliverToList) {
        this.title = title;
        this.message = message;
        this.deliverToList = deliverToList;
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

    public SubscriptionList getDeliverToList() {
        return deliverToList;
    }

    public void setDeliverToList(SubscriptionList deliverToList) {
        this.deliverToList = deliverToList;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\tMessage: " + this.message + "\tDeliver to: "
                + this.deliverToList.toString();
    }
}
