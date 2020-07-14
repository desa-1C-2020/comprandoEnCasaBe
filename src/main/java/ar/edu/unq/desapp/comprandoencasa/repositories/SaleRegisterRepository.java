package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;

import java.util.List;

public interface SaleRegisterRepository {
    void save(SaleRegister saleRegister);

    Optional<SaleRegister> findById(Long saleId);

    List<SaleRegister> findAllByCommerce(Commerce commerce);
}
