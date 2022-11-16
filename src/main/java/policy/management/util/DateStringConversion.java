package policy.management.util;

import org.springframework.http.HttpStatus;
import policy.management.exception.CustomException;
import policy.management.exception.error.ErrorEnum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateStringConversion {

    private DateStringConversion() {
    }

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static LocalDate convertStringToDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new CustomException(ErrorEnum.DATE_CONVERSION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static String convertDateToString(LocalDate date) {
        try {
            return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception e) {
            throw new CustomException(ErrorEnum.DATE_CONVERSION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
