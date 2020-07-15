package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.util.List;

public interface PurchaseRepository {
    void save(Purchase purchase);

    List<Purchase> findAllByUser(User user);
}
