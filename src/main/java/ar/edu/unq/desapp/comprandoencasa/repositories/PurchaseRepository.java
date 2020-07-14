package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;

public interface PurchaseRepository {
    void save(Purchase purchase);
}
