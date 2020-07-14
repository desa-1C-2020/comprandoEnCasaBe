package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

public class PurchaseRepositoryMem implements PurchaseRepository {

    private final List<Purchase> repo;

    public PurchaseRepositoryMem() {
        this.repo = new ArrayList();
    }

    @Override
    public void save(Purchase purchase) {
        repo.add(purchase);
    }
}
