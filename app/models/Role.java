package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Role extends Model {
	public String name;

	public void setName(String name) {
		this.name = "setter çağrıldı";
	}
	
	public String getName(){
		return "setter ve getter çağrıldı";
	}
}
