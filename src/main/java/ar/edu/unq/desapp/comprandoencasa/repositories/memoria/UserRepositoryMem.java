package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
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
    public Optional<User> findById(Long userId) {
        java.util.Optional<User> first = repo.stream().filter(user -> user.sameId(userId)).findFirst();
        return Optional.create(first);
    }

    @Override
    public User addUser(User user) {
        repo.add(user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.create(repo.stream().filter(user -> user.sameEmailRegistered(email)).findFirst());
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repo.stream().anyMatch(user -> user.sameEmailRegistered(email));
    }
}
