package ar.edu.unq.desapp.comprandoencasa.model;

import java.time.LocalTime;

public class TimeRange {

    private final LocalTime start;
    private final LocalTime end;

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
}
