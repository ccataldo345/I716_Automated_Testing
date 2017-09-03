package stringcalc;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    // "" -> 0
    // "1" -> 1
    // "1, 2" -> 3
    // null -> IllegalArgumentException

    @Test
    public void emptyString_returnsZero() {
        // StringCalculator c = new StringCalculator();

        // int result = c.add("");

        // assertThat(result, is(0));
    }

}
