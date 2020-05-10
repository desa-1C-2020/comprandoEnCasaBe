package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryMem implements UserRepository {
    private final List<User> repo;

    public UserRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public Optional<User> findBy(String userId) {
        java.util.Optional<User> first = repo.stream().filter(user -> user.sameId(userId)).findFirst();
        return Optional.create(first);
    }

    @Override
    public void update(User user) {
        repo.contains(user);
    }

    @Override
    public void addUser(User user) {
        repo.add(user);
    }

    @Override
    public List<User> getAll() {
        return repo;
    }
}
