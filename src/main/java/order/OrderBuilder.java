package order;

public class OrderBuilder {

    public OrderBuilder withNumber(String number) {
        throw new IllegalStateException("not implemented");
    }

    public OrderBuilder withDate(String dateAsString) {
        throw new IllegalStateException("not implemented");
    }

    public OrderBuilder addItem(String name, double price) {
        throw new IllegalStateException("not implemented");
    }

    public OrderBuilder isFilled() {
        throw new IllegalStateException("not implemented");
    }

    public Order build() {
        throw new IllegalStateException("not implemented");
    }
}