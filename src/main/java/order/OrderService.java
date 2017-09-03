package order;

import java.util.*;

public class OrderService {

    public DataSource dataSource;

    public OrderService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Order> getFilledOrders() {
        List<Order> filled = new ArrayList<>();
        for (Order order : dataSource.getOrders()) {
            if (order.isFilled()) {
                filled.add(order);
            }
        }

        return filled;
    }

    public List<Order> getOrdersOver(double amount) {
        List<Order> matchingOrders = new ArrayList<>();
        for (Order order : dataSource.getOrders()) {
            if (order.getTotal() > amount) {
                matchingOrders.add(order);
            }
        }

        return matchingOrders;
    }

    public List<Order> getOrdersSortedByDate() {
        List<Order> orders = new ArrayList<>(dataSource.getOrders());
        Collections.sort(orders, getOrderDateComparator());
        return orders;
    }

    private Comparator<Order> getOrderDateComparator() {
        return new Comparator<Order>() {

            @Override
            public int compare(Order o1, Order o2) {
                return o1.getOrderDate().compareTo(o2.getOrderDate());
            }

        };
    }
}
