package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@Table(name = "day_of_week_with_time_range")
public class DayOfWeekWithTimeRange extends PersistibleSupport {
    private DayOfWeek dayOfWeek;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeRange> timeRanges;

    public DayOfWeekWithTimeRange(DayOfWeek dayOfWeek, List<TimeRange> timeRanges) {
        this.dayOfWeek = dayOfWeek;
        this.timeRanges = timeRanges;
    }

    public DayOfWeekWithTimeRange() {
    }

    public boolean match(LocalDateTime suggestedDateTime) {
        DayOfWeek suggestedDay = suggestedDateTime.getDayOfWeek();
        int suggestedHour = suggestedDateTime.getHour();
        int suggestedMinute = suggestedDateTime.getMinute();
        return this.dayOfWeek.equals(suggestedDay)
            && timeRanges
            .stream()
            .anyMatch(range -> range.isInRange(suggestedHour, suggestedMinute));
    }

    public String getDayOfWeek() {
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<TimeRange> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(List<TimeRange> timeRanges) {
        this.timeRanges = timeRanges;
    }

    public String rangeToString() {
        String startDayOfWeek = getDayOfWeek() + ": \n";
        List<String> rangesString = timeRanges.stream().map(TimeRange::stringValue).collect(Collectors.toList());
        String rangesJoinning = String.join("\n", rangesString);
        return startDayOfWeek + rangesJoinning;
    }

    public LocalDateTime getNextDayOpen() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        int tomorrowDay = tomorrow.getDayOfWeek().getValue();
        int plusDays = 1;
        while (tomorrowDay != dayOfWeek.getValue()) {
            tomorrow = tomorrow.plusDays(plusDays);
            tomorrowDay = tomorrow.getDayOfWeek().getValue();
            plusDays++;
        }
        LocalTime localTime = getTimeRange();
        return LocalDateTime.of(tomorrow.toLocalDate(), localTime.plusSeconds(1));
    }

    private LocalTime getTimeRange() {
        int selectedHour = IntStream.range(0, 23)
            .filter(hour -> isInRange(hour))
            .findFirst().getAsInt();
        return LocalTime.of(selectedHour, 0);
    }

    private boolean isInRange(int hour) {
        return timeRanges.stream().anyMatch(timeRange -> timeRange.isInRange(hour, 0));
    }
}