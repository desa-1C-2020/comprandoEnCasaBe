package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.InterfacedOptional;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.PurchaseTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.support.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseService {
    private UserFinder userFinder;
    private CommerceFinder commerceFinder;
    private Logger log = LoggerFactory.getLogger(PurchaseService.class);

    public PurchaseService(UserFinder userFinder, CommerceFinder commerceFinder) {
        this.userFinder = userFinder;
        this.commerceFinder = commerceFinder;
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
        return AllOpenDay(suggestedDateTime, commerces.get(0).getDaysAndHoursOpen().get(0));
    }

    private LocalDateTime AllOpenDay(LocalDateTime suggestedDateTime, DayOfWeekWithTimeRange rangeOfFirstCommerce) {
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
}
