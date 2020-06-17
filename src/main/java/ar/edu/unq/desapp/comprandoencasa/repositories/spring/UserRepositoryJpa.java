package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryJpa extends JpaRepository<UserBasic, Long> {
    List<UserBasic> findByName(String name);
}