package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PurchaseRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRegisterRepositoryJpa extends JpaRepository<PurchaseRegister, Long> {
}
