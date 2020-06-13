package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.DayWithRange;
import ar.edu.unq.desapp.comprandoencasa.model.DeliveryZoneProgrammed;
import ar.edu.unq.desapp.comprandoencasa.model.DistanceCalculator;
import ar.edu.unq.desapp.comprandoencasa.model.TimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryOptions {

    DistanceCalculator distanceCalculator;

    public DeliveryOptions(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public List<DayWithRange> optionsFor(ShoppingList shoppingList) {
        //buscar los ids de los comercios, con estos ids, tengo que buscar en las salidas programadas si entran todos
        //o casi todos, o sea todos menos 1.
        User user = shoppingList.getUser();
        List<Commerce> commerces = shoppingList.getCommerces();

        Long totalRoute = calculateTotalDistance(user.getAddress(), commerces);


        DeliveryZoneProgrammed deliveryRangesFor = getDeliveryRangesFor(commerces);
        return null;
    }

    private Long calculateTotalDistance(Address destination, List<Commerce> commerces) {
        List<Address> commercesAddress = commerces
            .stream()
            .map(commerce -> commerce.getAddress())
            .collect(Collectors.toList());


        return distanceCalculator.calculateTotalDistance(commercesAddress, destination);
    }

    private DeliveryZoneProgrammed getDeliveryRangesFor(List<Commerce> commerces) {

        LocalDateTime.now();

        LocalDate miercoles = LocalDate.of(2020, Month.MAY, 20);
        LocalDate jueves = LocalDate.of(2020, Month.MAY, 21);
        LocalDate viernes = LocalDate.of(2020, Month.MAY, 22);
        LocalDate sabado = LocalDate.of(2020, Month.MAY, 23);

        TimeRange morning = new TimeRange(8, 13);
        TimeRange afternoon = new TimeRange(15, 20);


        List<TimeRange> timeRanges = new ArrayList<>();
        List<TimeRange> otherTimeRanges = new ArrayList<>();
        timeRanges.add(morning);
        timeRanges.add(afternoon);

        otherTimeRanges.add(morning);

        //Esto podia recibir DayOfWeek y año y hacer las conversiones necesarias adentro...
        DayWithRange miercolesConRango = new DayWithRange(miercoles, timeRanges);
        DayWithRange juevesConRango = new DayWithRange(jueves, timeRanges);
        DayWithRange viernesConRango = new DayWithRange(viernes, timeRanges);
        DayWithRange sabadoConRango = new DayWithRange(sabado, otherTimeRanges);

        List<DayWithRange> diasQueVamos = new ArrayList<>();
        diasQueVamos.add(miercolesConRango);
        diasQueVamos.add(sabadoConRango);

        return new DeliveryZoneProgrammed(commerces, diasQueVamos);
    }


    //tengo un repo en donde hay deliverys a zonas programadas.
    //una lista de los comercios comprendidos por zona, para simplificar y no poner coordenadas.
    //y dia y rango horario que se va a esos lugares.
}
