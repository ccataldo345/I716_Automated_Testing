package stringcalc;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTests {

    @Test
    public void commonCases() {
        int result = 1;

        assertTrue(true);

        assertFalse(false);

        assertEquals(1, result);

        assertThat(result, is(1));

        assertThat(result, is(not(0)));

        assertThat(null, nullValue());

        assertThat("", notNullValue());
    }

    @Test
    public void exceptionalCases() {
        assertThrows(IllegalStateException.class, () -> {
            throw new IllegalStateException();
        });
    }

}
