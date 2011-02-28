package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Cumle extends Model {

    public String cumle;

    public Boolean enabled = Boolean.TRUE;

    public Cumle(String cumle, Boolean enabled) {

        this.cumle = cumle;
        this.enabled = enabled;
    }
}
