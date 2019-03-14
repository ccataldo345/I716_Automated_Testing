package order;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    @Test
    public void shouldFindFilledOrders() {
        OrderService orderService = orderService()
                .add(anOrder().withNumber("A"))
                .add(anOrder().withNumber("B").isFilled())
                .build();

        List<Order> filledOrders = orderService.getFilledOrders();

        assertThat(filledOrders.size(), is(1));
        assertThat(filledOrders.get(0).getNumber(), is("B"));
    }

    @Test
    public void shouldFindOrdersOverOverCertainAmount() {
        OrderService orderService = orderService()
                .add(anOrder().addItem("pen", 1).addItem("paper", 5))
                .add(anOrder().addItem("desk", 30))
                .build();

        List<Order> orders = orderService.getOrdersOver(10);

        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getTotal(), is(30));
    }

    @Test
    public void shouldFindOrdersSortedByDate() {
        OrderService orderService = orderService()
                .add(anOrder().withDate("2014-01-01"))
                .add(anOrder().withDate("2013-01-01"))
                .build();

        List<Order> orders = orderService.getOrdersSortedByDate();

        assertThat(orders.size(), is(2));
        assertThat(orders.get(0).getOrderDate(), is(asDate("2013-01-01")));
        assertThat(orders.get(1).getOrderDate(), is(asDate("2014-01-01")));
    }

    private OrderServiceBuilder orderService() {
        return new OrderServiceBuilder();
    }

    private OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public static Date asDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
