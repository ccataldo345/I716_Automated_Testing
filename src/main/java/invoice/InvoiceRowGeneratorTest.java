package invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

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

        verify(dao).save(argThat(row -> row.amount.intValue() == 1));

        verify(dao).save(getMatcherForAmount(1));

        verify(dao).save(getMatcherForAmountWithMessage(1));
    }

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private InvoiceRow getMatcherForAmount(final Integer amount) {
        // Example matcher for testing that argument has certain amount.

        return argThat(row -> row.amount.intValue() == amount);
    }

    private InvoiceRow getMatcherForAmountWithMessage(final Integer amount) {
        // Example matcher for testing that argument has certain amount.
        // With message

        return argThat(new ArgumentMatcher<InvoiceRow>() {

            @Override
            public boolean matches(InvoiceRow row) {
                return amount.equals(row.amount.intValue());
            }

            @Override
            public String toString() {
                return MessageFormat.format(
                        "InvoiceRow with amount {0}", amount);
            }
        });
    }

    private LocalDate asDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(string, formatter);
    }

}