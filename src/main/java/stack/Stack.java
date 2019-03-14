package stack;

public class Stack {

    private int maxSize = 100;
    private int size = 0;
    private int peak = 0;

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
        peak = i;
    }

    public Integer pop() {
        if (size == 0) {
            throw new IllegalStateException();  // throw new ISE
        }
        size--;
        // System.out.println(stack[size]);
        return stack[size];
    }

    public Integer peak() {
        if (size == 0) {
            throw new IllegalStateException();  // throw new ISE
        }
        return peak;
    }

}
