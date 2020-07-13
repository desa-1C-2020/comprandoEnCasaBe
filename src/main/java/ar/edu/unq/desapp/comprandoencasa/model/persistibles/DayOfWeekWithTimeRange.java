package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "commerce")
public class DayOfWeekWithTimeRange extends PersistibleSupport {
    private DayOfWeek dayOfWeek;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeRange> timeRange;

    public DayOfWeekWithTimeRange(DayOfWeek dayOfWeek, List<TimeRange> timeRange) {
        this.dayOfWeek = dayOfWeek;
        this.timeRange = timeRange;
    }

    public boolean match(LocalDateTime suggestedDateTime) {
        DayOfWeek suggestedDay = suggestedDateTime.getDayOfWeek();
        int suggestedHour = suggestedDateTime.getHour();
        int suggestedMinute = suggestedDateTime.getMinute();
        return this.dayOfWeek.equals(suggestedDay)
            && timeRange
            .stream()
            .anyMatch(range -> range.isInRange(suggestedHour, suggestedMinute));
    }

    public String getDayOfWeek() {
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<TimeRange> getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(List<TimeRange> timeRange) {
        this.timeRange = timeRange;
    }
}