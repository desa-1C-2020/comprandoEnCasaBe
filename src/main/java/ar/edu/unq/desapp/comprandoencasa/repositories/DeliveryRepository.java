package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepository {
    void save(DeliveryRegister deliveryRegister);

    List<DeliveryRegister> findByDeliveryDateTime(LocalDate date);
}
