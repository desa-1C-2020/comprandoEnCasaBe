package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.repositories.DeliveryRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.DeliveryRepositoryJpa;

import java.time.LocalDate;
import java.util.List;

public class DeliveryRepositoryImpl implements DeliveryRepository {

    private DeliveryRepositoryJpa repoJpa;

    public static DeliveryRepositoryImpl create(DeliveryRepositoryJpa repoJpa) {
        DeliveryRepositoryImpl repo = new DeliveryRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public void save(DeliveryRegister deliveryRegister) {
        repoJpa.saveAndFlush(deliveryRegister);
    }

    @Override
    public List<DeliveryRegister> findByDeliveryDateTime(LocalDate date) {
        return repoJpa.findByDeliverDate(date);
    }
}