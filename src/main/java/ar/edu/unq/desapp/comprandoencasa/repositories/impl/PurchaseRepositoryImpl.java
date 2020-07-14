package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.PurchaseRepositoryJpa;

import java.util.List;

public class PurchaseRepositoryImpl implements PurchaseRepository {
    private PurchaseRepositoryJpa repoJpa;

    public static PurchaseRepository create(PurchaseRepositoryJpa purchaseRegisterRepositoryJpa) {
        PurchaseRepositoryImpl purchaseRepositoryImpl = new PurchaseRepositoryImpl();
        purchaseRepositoryImpl.repoJpa = purchaseRegisterRepositoryJpa;
        return purchaseRepositoryImpl;
    }

    @Override
    public void save(Purchase purchase) {
        repoJpa.saveAndFlush(purchase);
    }

    @Override
    public List<Purchase> findAllByUser(User user) {
        return repoJpa.findAllByUser(user);
    }
}
