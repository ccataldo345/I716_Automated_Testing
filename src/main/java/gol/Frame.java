package gol;

public class Frame {

    public Frame(int width, int height) {
    }

    @Override
    public String toString() {

        // return string that representing the frame

        return null;
    }

    @Override
    public boolean equals(Object obj) {

        // Compares two frames

        // Frames are equal if they have the same size
        // and they have on corresponding positions cells with the same state (alive/dead).

        return false; // True if frames are equal
    }

    public Integer getNeighbourCount(int x, int y) {

        // Returns cells neighbor count
        // Possible range is 0-8

        return null;
    }

    public boolean isAlive(int x, int y) {

        // True if cell with coordinates x and y is alive

        return false;
    }

    public void markAlive(int x, int y) {
        // Marks cell with coordinates x and y as alive
    }

    public Frame nextFrame() {

        // Returns next frame

        // Create new frame that has the same size.
        // Calculate the new state of each cell from the old frame
        // and mark it in the new frame.
        // Return new frame.

        return null;
    }

}