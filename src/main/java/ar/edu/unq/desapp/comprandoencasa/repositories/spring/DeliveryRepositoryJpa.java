package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepositoryJpa extends JpaRepository<DeliveryRegister, Long> {
    List<DeliveryRegister> findByDeliverDate(LocalDate date);
}
