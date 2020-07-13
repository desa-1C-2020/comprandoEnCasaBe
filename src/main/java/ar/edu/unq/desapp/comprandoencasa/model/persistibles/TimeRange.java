package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.DateUtils;
import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "time_range")
public class TimeRange extends PersistibleSupport {
    private LocalTime start;
    private LocalTime end;

    public TimeRange() {
    }

    public TimeRange(int starts, int ends) {
        if (starts < 1 || starts > 23) {
            throw new RuntimeException("La hora de comienzo debe ser entre 1 y 23.");
        }
        if (ends < 1 || ends > 24) {
            throw new RuntimeException("La hora de fin debe ser entre 1 y 24.");
        }
        if (starts >= ends) {
            throw new RuntimeException("La hora de fin debe ser mayor que la hora de comienzo.");
        }

        start = LocalTime.of(starts, 0);
        if (ends == 24) {
            end = LocalTime.of(23, 59);
        } else {
            end = LocalTime.of(ends, 0);
        }
    }

    public int getStartHour() {
        return start.getHour();
    }

    public int getStartMinute() {
        return start.getMinute();
    }

    public int getEndtHour() {
        return end.getHour();
    }

    public int getEndMinute() {
        return end.getMinute();
    }

    public boolean isInRange(int hour, int minute) {
        return getStartHour() <= hour
            && (getEndtHour() > hour || (getEndtHour() == hour && getEndMinute() == minute));
    }

    public String stringValue() {
        return "De " + DateUtils.parseHourfrom(start) + ":" + DateUtils.parseMinutesfrom(start) + " a "
            + DateUtils.parseHourfrom(end) + ":" + DateUtils.parseMinutesfrom(end) + " horas.";
    }
}