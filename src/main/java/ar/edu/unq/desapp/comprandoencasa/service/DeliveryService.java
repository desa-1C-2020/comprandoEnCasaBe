package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.DeliveryRepository;

import java.time.LocalDate;
import java.util.List;

public class DeliveryService {
    private int maxDeliverysPerDay;
    private DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, int maxDeliverysPerDay) {
        this.deliveryRepository = deliveryRepository;
        this.maxDeliverysPerDay = maxDeliverysPerDay;
    }

    public DeliveryRegister reserveFor(User user) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate probableShippingDate = getProbableShippingDate(tomorrow);
        DeliveryRegister deliveryRegister = new DeliveryRegister(user, true, false, probableShippingDate);
        deliveryRepository.save(deliveryRegister);
        return deliveryRegister;
    }

    private LocalDate getProbableShippingDate(LocalDate date) {
        if (hasDeliverySlot(date)) {
            return date;
        } else {
            return getProbableShippingDate(date.plusDays(1));
        }
    }

    private boolean hasDeliverySlot(LocalDate date) {
        List<DeliveryRegister> deliveryRegisters = deliveryRepository.findByDeliveryDateTime(date);
        return deliveryRegisters.size() < maxDeliverysPerDay;
    }
}
