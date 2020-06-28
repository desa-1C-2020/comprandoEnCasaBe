package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.CommerceRepositoryJpa;

import java.util.List;

public class CommerceRepositoryImpl implements CommerceRepository {

    private CommerceRepositoryJpa repoJpa;

    public static CommerceRepositoryImpl create(CommerceRepositoryJpa repoJpa) {
        CommerceRepositoryImpl repo = new CommerceRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public List<Commerce> getAll() {
        return repoJpa.findAll();
    }

    @Override
    public Optional<Commerce> getById(Long id) {
        return Optional.create(repoJpa.findById(id));
    }

    @Override
    public void add(Commerce commerce) {
        repoJpa.saveAndFlush(commerce);
    }
}