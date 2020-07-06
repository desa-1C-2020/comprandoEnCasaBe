package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserRepositoryJpa;

public class UserRepositoryImpl implements UserRepository {

    private UserRepositoryJpa repoJpa;

    public static UserRepositoryImpl create(UserRepositoryJpa repoJpa) {
        UserRepositoryImpl repo = new UserRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.create(repoJpa.findById(userId));
    }

    @Override
    public User addUser(User user) {
        repoJpa.saveAndFlush(user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(repoJpa.findByEmail(email));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repoJpa.existsByEmail(email);
    }
}