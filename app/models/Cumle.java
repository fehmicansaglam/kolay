package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Cumle extends Model {

    public String cumle;

    public Cumle(String cumle) {

        this.cumle = cumle;
    }

}
