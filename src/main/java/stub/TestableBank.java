package stub;

import common.Money;

public class TestableBank extends Bank {
    @Override
    public Money convert(Money money, String toCurrency) {
        if ("SEK".equals(money.getCurrency())) {
            return new Money(money.getAmount() / 10, "EUR");
        } else {
            return money;
        }
    }
}
