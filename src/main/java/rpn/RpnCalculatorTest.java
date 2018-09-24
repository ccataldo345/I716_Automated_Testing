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

    @Test
    public void calculatorSupportsSubtraction() {
        RpnCalculator c = new RpnCalculator();
        c.setAccumulator(3);
        c.enter();

        c.setAccumulator(1);
        c.minus();

        assertThat(c.getAccumulator(), is(2));
    }

    @Test
    public void calculatorSupportsOneParentheses() {
        RpnCalculator c = new RpnCalculator();
        c.setAccumulator(1);
        c.enter();
        c.setAccumulator(2);
        c.plus();
        c.enter();
        c.setAccumulator(4);
        c.multiply();

        assertThat(c.getAccumulator(), is(12));
    }

    @Test
    public void calculatorSupportsTwoParentheses() {
        RpnCalculator c = new RpnCalculator();
        c.setAccumulator(4);
        c.enter();
        c.setAccumulator(3);
        c.plus();
        c.enter();
        c.setAccumulator(2);
        c.enter();
        c.setAccumulator(1);
        c.plus();
        c.multiply();

        assertThat(c.getAccumulator(), is(21));
    }

    @Test
    public void calculatorSupportsRPNStringInput() {
        RpnCalculator c = new RpnCalculator();

        assertThat(c.evaluate("5 1 2 + 4 * + 3 +"), is(20));
        // ent 5 ent 1 ent 2 plus ent 4 mul plus ent 3 plus
        // rule: enter before each number
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

    public void minus() {
        accumulator = stack.pop() - accumulator;
    }

    public void multiply() {
        accumulator *= stack.pop();
    }

    public int evaluate(String expression) {
        for (String elem : expression.split(" ")) {
            if ("+".equals(elem)) {
                plus();
            }
            else if ("-".equals(elem)) {
                minus();
            }
            else if ("*".equals(elem)) {
                multiply();
            }
            else {
                enter();
                setAccumulator(Integer.parseInt(elem));    // convert String to int
            }
        }

        return accumulator;
    }

}