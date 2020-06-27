package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;

import java.util.List;

public interface CommerceRepository {
    List<Commerce> getAll();

    Optional<Commerce> getById(Long id);

    //Tiene que reemplazar el que viene basicamente
    void update(Commerce commerce);

    void add(Commerce commerce);
}
