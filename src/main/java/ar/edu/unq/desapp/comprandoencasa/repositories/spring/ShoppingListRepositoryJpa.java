package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepositoryJpa extends JpaRepository<ShoppingList, Long> {
}
