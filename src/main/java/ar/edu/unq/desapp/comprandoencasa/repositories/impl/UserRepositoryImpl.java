package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserRepositoryJpa;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private UserRepositoryJpa repoJpa;

    public static UserRepositoryImpl create(UserRepositoryJpa repoJpa) {
        UserRepositoryImpl repo = new UserRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public Optional<User> findById(String userId) {
        return null;
    }

    @Override
    public void addUser(User user) {
        repoJpa.save(user);
    }

    @Override
    public List<User> getAll() {
        return repoJpa.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return null;
    }
}