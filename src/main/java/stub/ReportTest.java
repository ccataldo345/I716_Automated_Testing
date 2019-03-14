package stub;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import common.Money;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReportTest {

    @Test
    public void calculatesTotalFromAmounts() {
        Report report = new TestableReport();
        report.setBank(new TestableBank());

        Money total = report.getTotalIncomeBetween(null, null);

        assertThat(total, is(new Money(2, "EUR")));
    }

    @Test
    public void calculatesTotalFromAmountsUseAnonymousClasses() {
        Report report = new Report() {
            @Override
            protected List<Money> getIncomesBetween(Date startDate, Date endDate) {
                return Arrays.asList(new Money(1, "EUR"), new Money(10, "SEK"));
            }
        };

        report.setBank(new Bank() {
            @Override
            public Money convert(Money money, String toCurrency) {
                if ("SEK".equals(money.getCurrency())) {
                    return new Money(money.getAmount() / 10, "EUR");
                } else {
                    return money;
                }
            }
        });

        Money total = report.getTotalIncomeBetween(null, null);

        assertThat(total, is(new Money(2, "EUR")));
    }

}
