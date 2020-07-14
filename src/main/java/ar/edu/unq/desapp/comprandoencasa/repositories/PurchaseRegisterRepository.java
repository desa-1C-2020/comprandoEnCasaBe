package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PurchaseRegister;

public interface PurchaseRegisterRepository {
    void save(PurchaseRegister purchaseRegister);
}
