package stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {

    @Test
    public void newStackHasNoElements() {
        // - create stack and check that it is empty (has zero elements).
        Stack stack = new Stack(100);

        assertThat(stack.getSize(), is(0));
    }

    @Test
    public void pushIncrementsStackSize() {
        // - create stack, push two elements and check that it contains two elements (size is 2).
        Stack stack = new Stack(100);
        stack.push(1);
        stack.push(2);

        assertThat(stack.getSize(), is(2));
    }

    @Test
    public void popDecreasesStackSize() {
        // - create stack, push two elements, pop two elements and check that it contains zero elements.
        Stack stack = new Stack(100);
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();

        assertThat(stack.getSize(), is(0));
    }

    @Test
    public void lastElementPushedIsFirstPopped() {
        // - create stack, push two elements, pop two elements and check that these are the elements pushed before
        // and the order is correct.
        Stack stack = new Stack(100);
        stack.push(1);
        int pushFirst = stack.peak();
        stack.push(2);
        int pushSecond = stack.peak();
        int popFirst = stack.pop();
        int popSecond = stack.pop();

        // First in (pushFirst), last out (popSecond)
        assertThat(pushFirst, is(1));
        assertThat(pushFirst, is(popSecond));

        // Last-in-first-out (LIFO)
        // Last in (pushSecond), first out (popFirst)
        assertThat(pushSecond, is(2));
        assertThat(pushSecond, is(popFirst));

    }

    @Test
    public void peakIsLastPushedElement() {
        // - create stack, push two elements, peek at the topmost element and check that it is the last one pushed.
        Stack stack = new Stack(100);
        stack.push(1);
        stack.push(2);

        assertThat(stack.peak(), is(2));
    }

    @Test
    public void peakDoesNotDecreaseStackSize() {
        // - create stack, push two elements, peek at the topmost element and check that the stack contains two elements.
        Stack stack = new Stack(100);
        stack.push(1);
        stack.push(2);
        stack.peak();

        assertThat(stack.getSize(), is(2));
    }

    @Test
    public void emptyStackPopThrows() {
        // - create stack, pop one element and check that stack throws IllegalStateException.
        Stack stack = new Stack(100);

        assertThrows(IllegalStateException.class, () -> stack.pop());   // throws ISE when stack.pop() is executed
    }

    @Test
    public void emptyStackPeakThrows() {
        // - create stack, peek at the topmost element and check that stack throws IllegalStateException.
        Stack stack = new Stack(100);

        assertThrows(IllegalStateException.class, () -> stack.peak());
    }

}