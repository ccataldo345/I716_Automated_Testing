package stack;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackTest {

    @Test
    public void newStackHasNoElements() {
        Stack stack = new Stack(100);

        assertThat(stack.getSize(), is(0));
    }

    @Test
    public void pushTwoElements_checkStackSizeIsTwo() {
        Stack stack = new Stack(100);
        stack.push(1);
        stack.push(2);

        assertThat(stack.getSize(), is(2));
    }
/*
    @Test
    public void pushTwoElementPopTwoElements_checkStackSizeIsZero() {
        Stack stack = new Stack(100);
        stack.push(1);
        stack.push(2);
        stack.pop;
        stack.pop;

        assertThat(stack.getSize(), is(0));
    }
*/

}