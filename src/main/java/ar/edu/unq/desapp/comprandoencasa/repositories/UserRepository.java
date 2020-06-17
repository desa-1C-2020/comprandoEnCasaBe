package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;

import java.util.List;

public interface UserRepository {
    Optional<UserBasic> findById(String userId);

    void addUser(UserBasic userBasic);

    List<UserBasic> getAll();

    Optional<UserBasic> findByEmail(String email);
}
