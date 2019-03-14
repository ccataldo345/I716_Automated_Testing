package order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private boolean filled;
    private String number;
    private Date orderDate;
    private List<LineItem> lineItems = new ArrayList<>();

    public Order(String number, Date date) {
        this.number = number;
        this.orderDate = date;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public String getNumber() {
        return number;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void addItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public int getTotal() {
        int total = 0;

        for (LineItem lineItem : lineItems)
            total += lineItem.getPrice();

        return total;
    }

    @Override
    public String toString() {
        return "Order [filled=" + filled + ", number=" + number
                + ", orderDate=" + orderDate + "]";
    }
}
