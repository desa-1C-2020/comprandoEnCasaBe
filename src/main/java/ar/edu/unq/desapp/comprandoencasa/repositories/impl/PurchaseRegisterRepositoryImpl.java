package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PurchaseRegister;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRegisterRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.PurchaseRegisterRepositoryJpa;

public class PurchaseRegisterRepositoryImpl implements PurchaseRegisterRepository {
    private PurchaseRegisterRepositoryJpa repoJpa;

    public static PurchaseRegisterRepository create(PurchaseRegisterRepositoryJpa purchaseRegisterRepositoryJpa) {
        PurchaseRegisterRepositoryImpl purchaseRegisterRepository = new PurchaseRegisterRepositoryImpl();
        purchaseRegisterRepository.repoJpa = purchaseRegisterRepositoryJpa;
        return purchaseRegisterRepository;
    }

    @Override
    public void save(PurchaseRegister purchaseRegister) {
        repoJpa.save(purchaseRegister);
    }
}
