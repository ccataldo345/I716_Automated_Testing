package invoice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

public class InvoiceRowGeneratorTest {

    @Mock InvoiceRowDao dao;

    @Test
    public void dateTestThatNeedsABetterName() {

        BigDecimal amount = new BigDecimal(100);
        LocalDate periodStart = asDate("2017-01-01");
        LocalDate periodEnd = asDate("2017-02-02");

        // InvoiceRowGenerator generator = new InvoiceRowGenerator();

        // call generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that it produced two rows with dates 2017-01-01 and 2017-02-01
    }

    @Test
    public void amountTestThatNeedsABetterName() {

        BigDecimal amount = new BigDecimal(11);
        // LocalDate periodStart = ...
        // LocalDate periodEnd = ...

        // InvoiceRowGenerator generator = new InvoiceRowGenerator();

        // call generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that it produced three rows with amounts 3, 4 and 4
    }

    @Test
    public void testingDaoWithMockitoAndMatcherExample() {
        dao.save(new InvoiceRow(new BigDecimal(1), LocalDate.now()));

        verify(dao).save(argThat(getMatcherForAmount(1)));
    }

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private Matcher<InvoiceRow> getMatcherForAmount(final Integer amount) {
        // Example matcher for testing that argument has certain amount.

        return new TypeSafeMatcher<InvoiceRow>() {

            @Override
            protected boolean matchesSafely(InvoiceRow item) {
                return amount.equals(item.amount.intValue());
            }

            @Override
            public void describeTo(Description description) {
                String message = MessageFormat.format(
                        "InvoiceRow with amount {0}", amount);

                description.appendText(message);
            }
        };
    }

    private LocalDate asDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(string, formatter);
    }

}