package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

import java.util.ArrayList;
import java.util.List;

public class UserSellerRepositoryMem implements UserSellerRepository {
    private List<UserSeller> repo;

    public UserSellerRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public void save(UserSeller userSeller) {
        repo.add(userSeller);
    }

    @Override
    public Optional<UserSeller> findByUser(User user) {
        java.util.Optional<UserSeller> first = repo.stream().filter(userSeller -> userSeller.sameUser(user)).findFirst();
        return Optional.create(first);
    }

    @Override
    public UserSeller findByCommerce(Commerce commerce) {
        return null;
    }
}
