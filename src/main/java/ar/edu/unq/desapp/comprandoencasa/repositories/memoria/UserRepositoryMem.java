package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryMem implements UserRepository {
    private final List<UserBasic> repo;

    public UserRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public Optional<UserBasic> findById(String userId) {
        java.util.Optional<UserBasic> first = repo.stream().filter(user -> user.sameId(userId)).findFirst();
        return Optional.create(first);
    }

    @Override
    public void addUser(UserBasic userBasic) {
        repo.add(userBasic);
    }

    @Override
    public List<UserBasic> getAll() {
        return repo;
    }

    @Override
    public Optional<UserBasic> findByEmail(String email) {
        return Optional.create(repo.stream().filter(user -> user.sameEmailRegistered(email)).findFirst());
    }
}
