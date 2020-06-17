package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;

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
    public Optional<UserBuyer> findByUser(UserBasic userBasic) {
        java.util.Optional<UserBuyer> first = repo.stream().filter(userBuyer -> userBuyer.sameUser(userBasic)).findFirst();
        return Optional.create(first);

    }
}
