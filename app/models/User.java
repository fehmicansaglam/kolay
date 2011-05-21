package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model {
    
	public String username;
    public String fullName;
	
    public User(String username, String fullName) {
		super();
		this.username = username;
		this.fullName = fullName;
	}
}
