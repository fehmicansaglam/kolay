package controllers;

import java.util.List;

import models.Role;
import models.User;
import play.Logger;
import play.i18n.Messages;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
import play.mvc.Finally;

public class Application extends Controller {

    @Before
    static void beforeAction() {

        Logger.info("before action: %1$s", request.action);
    }

    @After
    static void afterAction() {

        Logger.info("after action: %1$s", request.action);
    }

    @Catch(NullPointerException.class)
    static void exception(Throwable t) {

        Logger.error("bir hata oluştu");
    }

    @Finally
    static void finalMethod() {

        Logger.info("final method");
    }

    public static void index() {

        if (User.count() == 0) {
            new User("test", "Play Framework").save();
        }
        List users = User.findAll();
        Role role = new Role();
        role.name = "test_role";
        System.out.println("role name: " + role.name);
        render(users);
    }

    public static void save(String name) {

        validation.required(name).message(Messages.get("validation.required.custom", "Custom field name"));
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            index();
        }
    }

    public static void users(String term) {

        List<User> users = User.find("upper(fullName) like upper(?)", term + "%").fetch();
        renderJSON(users);
    }

    public static void getUser(Long id) {

        notFoundIfNull(id);
        User user = User.findById(id);
        notFoundIfNull(user);
        renderJSON(user);
    }

    public static void createUser(String username, String fullName) {

        User user = new User(username, fullName).save();
        renderJSON(user);
    }

    public static void deleteUser(Long id) {

        notFoundIfNull(id);
        User user = User.findById(id);
        notFoundIfNull(user);
        user.delete();
        renderJSON(true);
    }

}