package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	public static void index() {
		if (User.count() == 0) {
			new User("test", "Play Framework").save();
		}
		List users = User.findAll();
		render(users);
	}

	public static void users(String term) {
		List<User> users = User.find("upper(fullName) like upper(?)",
				term + "%").fetch();
		renderJSON(users);
	}

}