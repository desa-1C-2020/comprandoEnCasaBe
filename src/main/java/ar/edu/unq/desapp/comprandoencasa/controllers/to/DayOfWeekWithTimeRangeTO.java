package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

public class DayOfWeekWithTimeRangeTO {
    private int dayOfWeek; //1 lunes.. 7 domingo
    private List<MutablePair> timeRanges;

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<MutablePair> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(List<MutablePair> timeRanges) {
        this.timeRanges = timeRanges;
    }
}
