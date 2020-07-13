package ar.edu.unq.desapp.comprandoencasa.support;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static final String dateTimePattern = "yyyyMMdd:HHmmss";
    public static final String hourPattern = "HH";
    public static final String minutePattern = "mm";

    public static LocalDateTime parseToDateTimeWithDefaultFormat(String dateToParse) {
        try {
            return LocalDateTime.parse(dateToParse, getDateTimeFormatter(dateTimePattern));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Fecha: " + dateToParse + " con formato incorrecto. Intente nuevamente.");
        }
    }

    public static String parseHourfrom(LocalTime timeToParse) {
        return getString(timeToParse, hourPattern);
    }

    public static String parseMinutesfrom(LocalTime timeToParse) {
        return getString(timeToParse, minutePattern);
    }

    private static String getString(LocalTime timeToParse, String minutePattern) {
        try {
            return timeToParse.format(getDateTimeFormatter(minutePattern));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("No se pudo parsear: " + timeToParse + ".");
        }
    }

    private static DateTimeFormatter getDateTimeFormatter(String dateTimePattern) {
        return DateTimeFormatter.ofPattern(dateTimePattern);
    }
}