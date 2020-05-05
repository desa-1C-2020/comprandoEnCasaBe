package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommerceRepositoryMem implements CommerceRepository {

    private final List<Commerce> repo;

    public CommerceRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public List<Commerce> getAll() {
        return repo;
    }

    public void add(Commerce commerce) {
        this.repo.add(commerce);
    }
}