package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBuyerRepositoryJpa extends JpaRepository<UserBuyer, Long> {
}
