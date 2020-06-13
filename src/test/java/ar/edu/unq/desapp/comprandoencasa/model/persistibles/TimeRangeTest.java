package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.model.TimeRange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class TimeRangeTest {

    @Test
    public void whenCreateTimeRangeWithStartTimeSmallerThanEndTime_thenThetimeRangeIsCreated() {
        int startTime = 1;
        int endTime = 20;

        TimeRange timeRange = new TimeRange(startTime, endTime);

        assertThat(timeRange.getStartHour(), is(startTime));
        assertThat(timeRange.getStartMinute(), is(0));
        assertThat(timeRange.getEndtHour(), is(endTime));
        assertThat(timeRange.getEndMinute(), is(0));
    }

    @Test
    public void whenCreateTimeRangeWithStartTimeSmallerThanEndTimeAndEndTimeAs24_thenThetimeRangeIsCreated() {
        int startTime = 1;
        int endTime = 24;

        TimeRange timeRange = new TimeRange(startTime, endTime);

        assertThat(timeRange.getStartHour(), is(startTime));
        assertThat(timeRange.getStartMinute(), is(0));
        assertThat(timeRange.getEndtHour(), is(23));
        assertThat(timeRange.getEndMinute(), is(59));
    }

    @Test
    public void whenCreateTimeRangeWithStartTimeBiggerThanEnds_thenThetimeRangeIsNotCreated() {
        int startTime = 10;
        int endTime = 1;

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> new TimeRange(startTime, endTime))
            .withMessage("La hora de fin debe ser mayor que la hora de comienzo.");
    }

    @Test
    public void whenCreateTimeRangeWithStartTimeSmaller1_thenThetimeRangeIsNotCreated() {
        int startTime = -1;
        int endTime = 10;

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> new TimeRange(startTime, endTime))
            .withMessage("La hora de comienzo debe ser entre 1 y 23.");
    }

    @Test
    public void whenCreateTimeRangeWithStartTimeBigger24_thenThetimeRangeIsNotCreated() {
        int startTime = 25;
        int endTime = 10;

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> new TimeRange(startTime, endTime))
            .withMessage("La hora de comienzo debe ser entre 1 y 23.");
    }

    @Test
    public void whenCreateTimeRangeWithEndTimeSmaller1_thenThetimeRangeIsNotCreated() {
        int startTime = 1;
        int endTime = -1;

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> new TimeRange(startTime, endTime))
            .withMessage("La hora de fin debe ser entre 1 y 24.");
    }

    @Test
    public void whenCreateTimeRangeWithEndTimeBigger25_thenThetimeRangeIsNotCreated() {
        int startTime = 1;
        int endTime = 25;

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> new TimeRange(startTime, endTime))
            .withMessage("La hora de fin debe ser entre 1 y 24.");
    }
}