package ar.edu.unq.desapp.comprandoencasa.model;

import java.time.LocalDate;
import java.util.List;

public class DayWithRange {

    private final LocalDate date;
    private final List<TimeRange> timeRange;

    public DayWithRange(LocalDate date, List<TimeRange> timeRange) {
        this.date = date;
        this.timeRange = timeRange;
    }
}
