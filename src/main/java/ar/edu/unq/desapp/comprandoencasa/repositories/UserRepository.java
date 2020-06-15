package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.util.List;

public interface UserRepository {
    Optional<User> findById(String userId);

    void addUser(User user);

    List<User> getAll();

    Optional<User> findByEmail(String email);
}
