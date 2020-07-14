package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepositoryJpa extends JpaRepository<Purchase, Long> {
}
