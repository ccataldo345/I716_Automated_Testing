package string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unused")
public class OrderService {

    private DataSource dataSource;
    private List<Order> filledOrders = new ArrayList<>();

    public OrderService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Order> getFilledOrders() {
        //throw new IllegalStateException("not implemented");
        // returns orders on which isFilled() returns true

        List<Order> orders = dataSource.getOrders();

        for (Order order : orders) {
            if (order.isFilled()) {
                filledOrders.add(order);
            }
        }
        return filledOrders;
    }

    public List<Order> getOrdersOver(double amount) {
        // throw new IllegalStateException("not implemented");

        List<Order> orders = dataSource.getOrders();

        for (Order order : orders) {
            if (order.getTotal() > 100.0) {
                filledOrders.add(order);
            }
        }
        return filledOrders;
    }

    public List<Order> getOrdersSortedByDate() {
        // throw new IllegalStateException("not implemented");

        List<Order> orders = dataSource.getOrders();

        for (Order order : orders) {
                filledOrders.add(order);
        }
        filledOrders.sort(Comparator.comparing(Order::getOrderDate));
        // ref: https://stackoverflow.com/questions/16252269/how-to-sort-an-arraylist

        return filledOrders;
    }
}