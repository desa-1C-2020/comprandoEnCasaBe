package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommerceRepositoryJpa extends JpaRepository<Commerce, Long> {
}
