package stack;

public class Stack {

    private int maxSize;
    private int size = 0;

    Integer[] stack = new Integer[maxSize];

    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }
}
