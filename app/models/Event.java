package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Event extends Model {
	public String title;
	@ManyToOne
	public User author;
}
