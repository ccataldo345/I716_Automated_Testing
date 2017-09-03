package rpn;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RpnCalculatorTest {

    @Test
    public void newCalculatorHasZeroInItsAccumulator() {
        RpnCalculator c = new RpnCalculator();

        assertThat(c.getAccumulator(), is(0));
    }

}

class RpnCalculator {

    public Integer getAccumulator() {
        return null;
    }
}