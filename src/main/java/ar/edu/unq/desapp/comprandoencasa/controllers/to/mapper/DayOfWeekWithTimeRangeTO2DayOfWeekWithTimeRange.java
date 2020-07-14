package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.DayOfWeekWithTimeRangeTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.TimeRange;
import org.apache.commons.lang3.tuple.MutablePair;
import org.mapstruct.Mapper;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class DayOfWeekWithTimeRangeTO2DayOfWeekWithTimeRange implements MapperFunction<DayOfWeekWithTimeRangeTO, DayOfWeekWithTimeRange> {

    @Override
    public DayOfWeekWithTimeRange apply(DayOfWeekWithTimeRangeTO dayOfWeekWithTimeRangeTO) {
        if (dayOfWeekWithTimeRangeTO == null) {
            return null;
        }
        DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekWithTimeRangeTO.getDayOfWeek());

        return new DayOfWeekWithTimeRange(dayOfWeek, getTimeRangesFrom(dayOfWeekWithTimeRangeTO.getTimeRanges()));
    }

    private List<TimeRange> getTimeRangesFrom(List<MutablePair> ranges) {
        return ranges
            .stream()
            .map(pair -> new TimeRange((Integer) pair.getLeft(), (Integer) pair.getRight()))
            .collect(Collectors.toList());
    }
}