package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserSellerRepositoryJpa;

public class UserSellerRepositoryImpl implements UserSellerRepository {

    private UserSellerRepositoryJpa repoJpa;

    public static UserSellerRepositoryImpl create(UserSellerRepositoryJpa repoJpa) {
        UserSellerRepositoryImpl repo = new UserSellerRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public void save(UserSeller userSeller) {
        repoJpa.save(userSeller);
    }

    @Override
    public Optional<UserSeller> findByUser(User user) {
        return Optional.ofNullable(repoJpa.findByUser(user));
    }
}