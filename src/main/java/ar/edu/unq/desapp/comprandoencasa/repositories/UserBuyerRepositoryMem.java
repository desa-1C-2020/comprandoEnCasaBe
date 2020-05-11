package ar.edu.unq.desapp.comprandoencasa.repositories;

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
}
