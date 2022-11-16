package policy.management.unit;

import org.junit.jupiter.api.Test;
import policy.management.domain.Policy;
import policy.management.exception.CustomException;
import policy.management.util.DateStringConversion;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PolicyUnitTests {

    @Test
    void validDateTest() {
        Policy policyInvalidDate = new Policy(DateStringConversion.convertStringToDate("11.10.2020"), null);
        Policy policyValidDate = new Policy(LocalDate.now(), null);

        assertDoesNotThrow(() -> {
            policyValidDate.isAValidStartDateDate();
        });
        assertThrows(CustomException.class, () -> {
            policyInvalidDate.isAValidStartDateDate();
        });

    }

}
