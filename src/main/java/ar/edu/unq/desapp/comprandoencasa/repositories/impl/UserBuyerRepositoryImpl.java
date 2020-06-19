package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserBuyerRepositoryJpa;

public class UserBuyerRepositoryImpl implements UserBuyerRepository {

    private UserBuyerRepositoryJpa repoJpa;

    public static UserBuyerRepositoryImpl create(UserBuyerRepositoryJpa repoJpa) {
        UserBuyerRepositoryImpl repo = new UserBuyerRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public void save(UserBuyer userBuyer) {
        repoJpa.saveAndFlush(userBuyer);
    }

    @Override
    public Optional<UserBuyer> findByUser(User user) {
        return null;
    }
}