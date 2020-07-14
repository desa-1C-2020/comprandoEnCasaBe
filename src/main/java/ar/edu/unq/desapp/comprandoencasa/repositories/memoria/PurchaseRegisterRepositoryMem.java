package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PurchaseRegister;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRegisterRepository;

import java.util.ArrayList;
import java.util.List;

public class PurchaseRegisterRepositoryMem implements PurchaseRegisterRepository {

    private final List<PurchaseRegister> repo;

    public PurchaseRegisterRepositoryMem() {
        this.repo = new ArrayList();
    }

    @Override
    public void save(PurchaseRegister purchaseRegister) {
        repo.add(purchaseRegister);
    }
}
