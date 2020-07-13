package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class DayOfWeekWithTimeRangeTest {

    @Test
    public void whenMatchDayOfWeekWithGivenDateTime_thenReturnTrue() {
        assertThatTheGivenDateTimeMatchWithDayWithRange("2020-07-13", 8, true);
    }

    @Test
    public void whenDayOfGivenDateTimeNotMatchWithRange_thenReturnFalse() {
        assertThatTheGivenDateTimeMatchWithDayWithRange("2020-07-12", 8, false);
    }

    @Test
    public void whenTimeOfGivenDateTimeNotMatchWithRange_thenReturnFalse() {
        assertThatTheGivenDateTimeMatchWithDayWithRange("2020-07-13", 12, false);
    }

    private void assertThatTheGivenDateTimeMatchWithDayWithRange(String givenDate, int givenHour, boolean expectedResult) {
        TimeRange morning = new TimeRange(8, 12);
        TimeRange afternoon = new TimeRange(15, 18);
        List ranges = new ArrayList();
        ranges.add(morning);
        ranges.add(afternoon);
        DayOfWeekWithTimeRange dayOfWeek = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY, ranges);
        LocalDateTime dayToMatch = LocalDateTime.of(LocalDate.parse(givenDate), LocalTime.of(givenHour, 1));

        boolean match = dayOfWeek.match(dayToMatch);

        assertThat(match, is(expectedResult));
    }

}