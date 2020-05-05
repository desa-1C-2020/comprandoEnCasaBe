package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;

import java.util.List;

public interface CommerceRepository {
    List<Commerce> getAll();
}
