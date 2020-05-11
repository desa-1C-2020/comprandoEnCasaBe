package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.util.List;

public interface UserRepository {
    Optional<User> findBy(String userId);

    void update(User user);

    void addUser(User user);

    List<User> getAll();
}
