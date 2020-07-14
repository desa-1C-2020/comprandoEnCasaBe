package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleRegisterRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.PurchaseRegisterRepositoryJpa;

import java.util.List;

public class SaleRegisterRepositoryImpl implements SaleRegisterRepository {
    private PurchaseRegisterRepositoryJpa repoJpa;

    public static SaleRegisterRepository create(PurchaseRegisterRepositoryJpa purchaseRegisterRepositoryJpa) {
        SaleRegisterRepositoryImpl purchaseRegisterRepository = new SaleRegisterRepositoryImpl();
        purchaseRegisterRepository.repoJpa = purchaseRegisterRepositoryJpa;
        return purchaseRegisterRepository;
    }

    @Override
    public void save(SaleRegister saleRegister) {
        repoJpa.save(saleRegister);
    }

    @Override
    public Optional<SaleRegister> findById(Long saleId) {
        return Optional.create(repoJpa.findById(saleId));
    }

    @Override
    public List<SaleRegister> findAllByCommerce(Commerce commerce) {
        return repoJpa.findByCommerce(commerce);
    }
}
