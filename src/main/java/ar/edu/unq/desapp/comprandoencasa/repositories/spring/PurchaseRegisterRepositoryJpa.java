package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRegisterRepositoryJpa extends JpaRepository<SaleRegister, Long> {
    List<SaleRegister> findByCommerce(Commerce commerce);
}
