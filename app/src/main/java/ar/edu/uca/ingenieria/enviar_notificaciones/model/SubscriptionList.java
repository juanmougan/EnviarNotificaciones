package ar.edu.uca.ingenieria.enviar_notificaciones.model;

/**
 * Models a predefined list of Students who will receive a Message
 *
 * Created by juanmougan@gmail.com on 10/11/15.
 */
public class SubscriptionList {

    private int id;
    private String name;

    public SubscriptionList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
