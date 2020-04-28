package ar.edu.unq.desapp.comprandoencasa.model;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrar {
    private List<User> users;

    public UserRegistrar() {
        users = new ArrayList<>();
    }

    public void register(User user) {
        if (existsSameUser(user)) {
            throw new RuntimeException("No se puede registrar con el mail, ya existe en el sistema.");
        }
        users.add(user);
    }

    private boolean existsSameUser(User userToFind) {
        return users.stream().anyMatch(user -> user.same(userToFind));
    }

    public boolean exists(User user) {
        return users.contains(user);
    }
}