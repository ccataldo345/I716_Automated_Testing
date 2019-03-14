package string;

import java.text.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class OrderServiceTest {

    OrderService service = getOrderService();

    @Test
    public void shouldFindFilledOrders() {
        assertThat(numbersAsString(service.getFilledOrders()),
                is("N123, N124"));
    }

    @Test
    public void shouldFindOrdersOverOverCertainAmount() {
        assertThat(amountsAsString(service.getOrdersOver(100.0)),
                   is("290.0, 1025.0"));
    }

    @Test
    public void shouldFindOrdersSortedByDate() {
        assertThat(datesAsString(service.getOrdersSortedByDate()),
                   is("1991-10-06, 2000-07-06, 2013-11-21"));
    }

    private String numbersAsString(List<Order> filledOrders) {
        // return "not implemented";

        List<String> ordersNumber = new ArrayList<>();
        for(Order order: filledOrders) {
            ordersNumber.add(order.getNumber());
        }
        return String.join(", ", ordersNumber);
    }

    private String amountsAsString(List<Order> filledOrders) {
        // return "not implemented";

        List<String> ordersAmount = new ArrayList<>();
        for(Order order: filledOrders) {
            ordersAmount.add(String.valueOf(order.getTotal()));
        }
        return String.join(", ", ordersAmount);
    }

    private String datesAsString(List<Order> filledOrders) {
        // return "not implemented";

        List<String> ordersDate = new ArrayList<>();
        for(Order order: filledOrders) {
            ordersDate.add(asString(order.getOrderDate()));
            // doesn't work with String.valueOf, returns the standard date format,
            // so used static String asString(Date date) method
        }

        System.out.println(ordersDate);
        return String.join(", ", ordersDate);
    }

    private OrderService getOrderService() {
        // here is test data defined

        // total price: 27.2
        final Order order1 = new Order("N123", asDate("2013-11-21"));
        order1.addItem(new LineItem("pen", 0.6, 4));
        order1.addItem(new LineItem("paper", 3.1, 8));
        order1.setFilled(true);

        // total price: 290
        final Order order2 = new Order("N124", asDate("1991-10-06"));
        order2.addItem(new LineItem("e-reader", 100, 2));
        order2.addItem(new LineItem("book", 30, 3));
        order2.setFilled(true);

        // total price: 1025
        final Order order3 = new Order("N125", asDate("2000-07-06"));
        order3.addItem(new LineItem("cd", 5, 5));
        order3.addItem(new LineItem("desk", 100, 10));

        DataSource testDataSource = new DataSource() {

            @Override
            public List<Order> getOrders() {
                return Collections.unmodifiableList(Arrays.asList(order1, order2, order3));
            }

        };

        return new OrderService(testDataSource);
    }

    public static Date asDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
