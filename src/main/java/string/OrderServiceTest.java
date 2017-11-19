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
                   is("..."));
    }

    @Test
    public void shouldFindOrdersSortedByDate() {
        assertThat(datesAsString(service.getOrdersSortedByDate()),
                   is("..."));
    }

    private String numbersAsString(List<Order> filledOrders) {
        return "not implemented";
    }

    private String amountsAsString(List<Order> filledOrders) {
        return "not implemented";
    }

    private String datesAsString(List<Order> filledOrders) {
        return "not implemented";
    }

    private OrderService getOrderService() {
        // here is test data defined

        final Order order1 = new Order("N123", asDate("2013-11-21"));
        order1.addItem(new LineItem("pen", 0.6, 4));
        order1.addItem(new LineItem("paper", 3.1, 8));
        order1.setFilled(true);

        final Order order2 = new Order("N124", asDate("1991-10-06"));
        order2.addItem(new LineItem("e-reader", 100, 2));
        order2.addItem(new LineItem("book", 30, 3));
        order2.setFilled(true);

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
