package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static LocalDate parse(String str) {

        return  LocalDate.parse(str, formatter);
    }
    public static String formatDate(LocalDate localDate) {
        try{
            return formatter.format(localDate);
        }catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }
}
