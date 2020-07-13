package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.InterfacedOptional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.support.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseService {
    private final LocalTime defaultDeliveryTime = LocalTime.of(18, 30);
    private DeliveryService deliveryService;
    private CommerceFinder commerceFinder;
    private UserFinder userFinder;
    private Logger log = LoggerFactory.getLogger(PurchaseService.class);

    public PurchaseService(CommerceFinder commerceFinder, UserFinder userFinder, DeliveryService deliveryService) {
        this.commerceFinder = commerceFinder;
        this.userFinder = userFinder;
        this.deliveryService = deliveryService;
    }

    public LocalDateTime getTakeAwayOptionFor(List<Long> commercesId, String suggestedDay) {
        LocalDateTime suggestedDateTime = DateUtils.parseToDateTimeWithDefaultFormat(suggestedDay);

        List<Commerce> commerces = commercesId
            .stream()
            .map(commerceId -> commerceFinder.findById(commerceId))
            .filter(InterfacedOptional::isPresent)
            .map(InterfacedOptional::get)
            .collect(Collectors.toList());

        commerces.stream().forEach(commerce -> log.info("Dias y horarios abierto: " + commerce.daysAndHoursOpenAsString()));
        boolean allOpenInSuggestedDay = canWithdrawThisDay(suggestedDateTime, commerces);
        if (allOpenInSuggestedDay) {
            return suggestedDateTime;
        }
        LocalDateTime nextDay = suggestedDateTime.plusDays(1);
        if (canWithdrawThisDay(nextDay, commerces)) {
            return nextDay;
        }
        return allOpenDay(suggestedDateTime, commerces.get(0).getDaysAndHoursOpen().get(0));
    }

    private LocalDateTime allOpenDay(LocalDateTime suggestedDateTime, DayOfWeekWithTimeRange rangeOfFirstCommerce) {
        //Default, si el dia sugerido por el cliente es viernes, sabado o domingo
        // le sumo 3 dias para que el dia default sea o lunes, martes o miercoles.
        if (suggestedDateTime.getDayOfWeek().getValue() > 5) {
            return suggestedDateTime.plusDays(3);
        }
        return rangeOfFirstCommerce.getNextDayOpen();
    }

    private boolean canWithdrawThisDay(LocalDateTime suggestedDateTime, List<Commerce> commerces) {
        return commerces.stream().allMatch(commerce -> commerce.isOpenIn(suggestedDateTime));
    }

    public LocalDateTime getDeliveryOption(Long userId) {
        User user = userFinder.findUserById(userId);
        DeliveryRegister deliveryRegister = deliveryService.reserveFor(user);
        LocalDate deliverDate = deliveryRegister.getDeliverDate();
        return LocalDateTime.of(deliverDate, defaultDeliveryTime);
    }
