package stack;

public class Stack {

    private int maxSize = 100;
    private int size = 0;

    Integer[] stack = new Integer[maxSize];

    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }

    public void push(Integer i) {
        stack[size] = i;
        size++;
    }

}
