package stub;

import common.Money;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestableReport extends Report {
    @Override
    public List<Money> getIncomesBetween(Date startDate, Date endDate) {
        return Arrays.asList(new Money(1, "EUR"), new Money(10, "SEK"));
    }
}
