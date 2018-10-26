package gol;

public class Frame {

    private boolean[][] cell;
    private int width;
    private int height;

    public Frame(int width, int height) {
        this.width = width +2;
        this.height = height +2;
        this.cell = new boolean[this.width][this.height];
    }

    @Override
    public String toString() {

        // return string that representing the frame
        String graph = "";

        // print columns number
        for (int col = 0; col < this.width-1; col++) System.out.print(" " + col + " ");
        System.out.println();

        // iterate through rows and columns, but exclude border
        for (int row = 1; row < this.height-1; row++) {
            for (int col = 1; col < this.width-1; col++) {
                if(col == 1) System.out.print(" " + row + " "); // print rows number
                // print matrix, X if cell is true
                graph = cell[col][row] ? " X " : " . ";
                System.out.print(graph);
                // System.out.print("(" + col  + "-" + row + "); ");
            }
            System.out.println();
        }
        System.out.println(" -------------------------");
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
        int count = 0;
        for (int row = -1; row <= 1; row++) {
            int currentRow = row + y;

            for (int col = -1; col <= 1; col++) {
                int currentCol = col + x;

                if (cell[currentCol][currentRow]) {
                    count++;
                }
            }
        }
        if (cell[x][y]) {
            count--;
        }
        // System.out.println(cell[x][y]);
        return count;
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
        gol.markAlive(2, 2);
        gol.toString();
        //System.out.println(gol.toString());
        //System.out.println((Math.random() < 0.5));  //return true or false
        System.out.println("Is 2-2 alive? >>> " + gol.isAlive(2,2));
        System.out.println("How many neighbours has cell 1-1? >>> " + gol.getNeighbourCount(1,1));
        System.out.println("How many neighbours has cell 2-2? >>> " + gol.getNeighbourCount(2,2));

    }
}

