package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepositoryJpa extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
}
