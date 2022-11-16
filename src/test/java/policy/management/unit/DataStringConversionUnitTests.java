package policy.management.unit;

import org.junit.jupiter.api.Test;
import policy.management.exception.CustomException;
import policy.management.util.DateStringConversion;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataStringConversionUnitTests {

    @Test
    void convertStringToDateTest() {

        assertDoesNotThrow(() -> {
            DateStringConversion.convertStringToDate("11.10.2022");
        });
        assertThrows(CustomException.class, () -> {
            DateStringConversion.convertStringToDate("9-10.2022");
        });

    }

    @Test
    void convertDateToStringTest() {
        assertDoesNotThrow(() -> {
            DateStringConversion.convertDateToString(LocalDate.now());
        });
    }

}
