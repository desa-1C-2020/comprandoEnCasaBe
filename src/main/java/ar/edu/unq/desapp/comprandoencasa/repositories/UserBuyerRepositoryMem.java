package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;

import java.util.ArrayList;
import java.util.List;

public class UserBuyerRepositoryMem implements UserBuyerRepository {
    private List<UserBuyer> repo;

    public UserBuyerRepositoryMem() {
        repo = new ArrayList<>();
    }

    @Override
    public void save(UserBuyer userBuyer) {
        repo.add(userBuyer);
    }

    @Override
    public Optional<UserBuyer> findByUser(User user) {
        java.util.Optional<UserBuyer> first = repo.stream().filter(userBuyer -> userBuyer.sameUser(user)).findFirst();
        return Optional.create(first);

    }
}
