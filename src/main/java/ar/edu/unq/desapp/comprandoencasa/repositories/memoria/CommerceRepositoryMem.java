package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;

import java.util.ArrayList;
import java.util.List;

public class CommerceRepositoryMem implements CommerceRepository {

    private final List<Commerce> repo;

    public CommerceRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public List<Commerce> getAll() {
        return repo;
    }

    @Override
    public Optional<Commerce> getById(Long id) {
        return Optional.create(repo.stream().filter(commerce -> commerce.getId().equals(id)).findFirst());
    }

    @Override
    public void save(Commerce commerce) {
        this.repo.add(commerce);
    }
}