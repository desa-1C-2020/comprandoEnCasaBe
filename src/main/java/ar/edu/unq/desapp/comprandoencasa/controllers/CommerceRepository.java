package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.model.Commerce;

import java.util.List;

public interface CommerceRepository {
    List<Commerce> getAll();
}
