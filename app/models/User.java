package models;

import play.*;
import play.db.jpa.*;
import play.jobs.OnApplicationStart;

import javax.persistence.*;
import java.util.*;

@Entity(name = "users")
public class User extends Model {
	@Column(nullable = false, unique = true)
	public String username;
	public String fullName;
	public Date created;
	@ManyToMany
	public List<Role> roles = new ArrayList<Role>();
	@OneToMany(mappedBy = "author")
	public List<Event> events = new ArrayList<Event>();

	@PrePersist
	void prePersist() {
		this.created = new Date();
	}

	public User(String username, String fullName) {
		super();
		this.username = username;
		this.fullName = fullName;
	}
}
