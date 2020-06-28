package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleableItemRepositoryJpa extends JpaRepository<SaleableItem, Long> {
}
