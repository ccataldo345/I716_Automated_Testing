package rpn;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RpnCalculatorTest {

    @Test
    public void newCalculatorHasZeroInItsAccumulator() {
        RpnCalculator c = new RpnCalculator();

        assertThat(c.getAccumulator(), is(0));
    }

    @Test
    public void possibleToSetAccumulator() {
        RpnCalculator c = new RpnCalculator();
        c.setAccumulator(1);

        assertThat(c.getAccumulator(), is(1));
    }


    @Test
    public void calculatorSupportsAddition() {
        RpnCalculator c = new RpnCalculator();
        c.setAccumulator(1);
        c.enter();

        c.setAccumulator(2);
        c.plus();

        assertThat(c.getAccumulator(), is(3));
    }

}

class RpnCalculator {

    private int accumulator = 0;

    Stack<Integer> stack = new Stack<>();

    public int getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(int number) {
        this.accumulator = number;
    }

    public void enter() {
        stack.push(accumulator);
    }

    public void plus() {
        accumulator += stack.pop();
    }

}