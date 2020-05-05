package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

public interface UserRepository {
    Optional<User> findBy(Long userId);

    void update(User user);
}
