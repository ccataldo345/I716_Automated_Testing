package gol;

public class Frame {

    private boolean[][] cell;
    private int width;
    private int height;



    public Frame(int height, int width) {
        this.width = width;
        this.height = height;
        this.cell = new boolean[width][height];
    }

    @Override
    public String toString() {

        // return string that representing the frame
        String graph="";

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // print matrix, X if cell is true
                graph = cell[i][j] ? " X " : " O ";
                System.out.print(graph);
            }
            System.out.println();
        }
        return "";
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
        return cell[x][y];
    }

    public void markAlive(int x, int y) {
        // Marks cell with coordinates x and y as alive
        cell[x][y] = true;
    }

    public Frame nextFrame() {

        // Returns next frame

        // Create new frame that has the same size.
        // Calculate the new state of each cell from the old frame
        // and mark it in the new frame.
        // Return new frame.

        return null;
    }

    // CHRIS
    public static void main(String[] args) {
        Frame gol = new Frame(8, 4);
        gol.markAlive(2,2);
        gol.toString();
        //System.out.println(gol.toString());
        System.out.println((Math.random() < 0.5));  //return true or false

    }
}

