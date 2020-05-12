package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;

import java.util.List;

public interface CommerceRepository {
    List<Commerce> getAll();

    Optional<Commerce> getById(String id);
}
