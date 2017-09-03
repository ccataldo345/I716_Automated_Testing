package string;

import java.util.List;

@SuppressWarnings("unused")
public class OrderService {

    private DataSource dataSource;

    public OrderService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Order> getFilledOrders() {
        throw new IllegalStateException("not implemented");
    }

    public List<Order> getOrdersOver(double amount) {
        throw new IllegalStateException("not implemented");
    }

    public List<Order> getOrdersSortedByDate() {
        throw new IllegalStateException("not implemented");
    }
}
