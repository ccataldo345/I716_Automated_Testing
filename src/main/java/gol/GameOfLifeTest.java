package gol;


import static org.hamcrest.CoreMatchers.*;

import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class GameOfLifeTest {

    // The good place to start is to be able to mark cells as alive.
    @Test
    public void cellIsAlive() {
        Frame frame = new Frame(8, 4);
        frame.markAlive(1, 1);
        frame.markAlive(2, 2);
        frame.markAlive(7, 3);

        frame.toString();

        Assert.assertTrue(frame.isAlive(1, 1));
        Assert.assertTrue(frame.isAlive(2, 2));
        Assert.assertTrue(frame.isAlive(7, 3));
    }

    // Then it is possible to count alive neighbors.
    @Test
    public void countAliveNeighbours() {
        Frame frame = new Frame(8, 4);
        frame.markAlive(1, 1);   //corner cell
        frame.markAlive(2, 1);
        frame.markAlive(2, 2);
        frame.markAlive(1, 4);   //corner cell
        frame.markAlive(8, 4);   //corner cell

        frame.toString();

        Assert.assertThat(frame.getNeighbourCount(1, 1), is(2));
        Assert.assertThat(frame.getNeighbourCount(1, 4), is(0));
        Assert.assertThat(frame.getNeighbourCount(8, 4), is(0));

    }

    // Then try to calculate next frame
    // (at first something very simple)
    // Do not implement all the roles at once

    // If you need some helper method eg. Frame.toString() then write test to them as well.

    // It is strongly advised to use TDD.
    // It usually shows from the result whether TDD was used or not.

    @Test
    public void deadCellWithThreeNeighboursBecomesAlive() {
        Frame frame = new Frame(8, 4);
        frame.markAlive(2, 2);
        frame.markAlive(2, 3);
        frame.markAlive(3, 3);
        // frame.markAlive(3, 2); this must be dead!

        frame.toString();
        System.out.println(" Next frame:");
        System.out.println(" -------------------------");
        frame.nextFrame().toString();

        Assert.assertFalse(frame.isAlive(3, 2));
        Assert.assertTrue(frame.nextFrame().isAlive(3, 2));
    }

}