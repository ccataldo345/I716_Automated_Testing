package stub;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import common.Money;

public class ReportTest {

    @Test
    public void calculatesTotalFromAmounts() {
        Report report = new Report();
        report.setBank(new Bank());

        Money total = report.getTotalIncomeBetween(null, null);

        assertThat(total, is(new Money(2, "EUR")));
    }

}
