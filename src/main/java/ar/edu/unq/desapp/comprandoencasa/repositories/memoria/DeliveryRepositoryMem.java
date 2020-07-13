package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.repositories.DeliveryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryRepositoryMem implements DeliveryRepository {
    private final List<DeliveryRegister> repo;

    public DeliveryRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public void save(DeliveryRegister deliveryRegister) {
        repo.add(deliveryRegister);
    }

    @Override
    public List<DeliveryRegister> findByDeliveryDateTime(LocalDate date) {
        return repo
            .stream()
            .filter(deliveryRegister -> deliveryRegister.getDeliverDate().isEqual(date))
            .collect(Collectors.toList());
    }

    public void clear() {
        repo.clear();
    }
}