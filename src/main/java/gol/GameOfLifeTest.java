package gol;


import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class GameOfLifeTest {

    // The good place to start is to be able to mark cells as alive.
    @Test
    public void cellIsAlive() {
        Frame frame = new Frame(8, 4);
        frame.markAlive(1,1);
        frame.markAlive(2,2);

        frame.toString();

        Assert.assertTrue(frame.isAlive(1, 1));
        Assert.assertTrue(frame.isAlive(2, 2));
    }

    // Then it is possible to count alive neighbors.
    @Test
    public void countAliveNeighbours() {
        Frame frame = new Frame(8, 4);
        frame.markAlive(0,0);
        frame.markAlive(1,1);
        frame.markAlive(2,1);
        frame.markAlive(1,2);

        frame.toString();

        Assert.assertThat(frame.getNeighbourCount(1, 1), is(3));
    }

    // Then try to calculate next frame
    // (at first something very simple)
    // Do not implement all the roles at once

    // If you need some helper method eg. Frame.toString() then write test to them as well.

    // It is strongly advised to use TDD.
    // It usually shows from the result whether TDD was used or not.

}