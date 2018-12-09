package invoice;

import org.apache.xalan.templates.ElemValueOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sun.awt.SunHints;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InvoiceRowGeneratorTest {

    @Mock InvoiceRowDao dao;

    @Test
    public void amountInTwoInstallments_verifyGeneratorProducesTwoRows() {

        BigDecimal amount = new BigDecimal(100);
        LocalDate periodStart = asDate("2017-01-01");
        // LocalDate periodEnd = asDate("2017-02-02");
        LocalDate periodEnd = asDate("2017-02-10");

        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that it produced two rows with dates 2017-01-01 and 2017-02-01 >>> NO
        //CC check that it produced two rows with dates 2017-01-10 and 2017-02-10

        // Which data would be written to the database that can be
        // tested by checking the calls and arguments to the mocked
        // InvoiceRowDao.save() method.

        dao.save(new InvoiceRow(amount, periodStart));
        dao.save(new InvoiceRow(amount, periodEnd));

        //verify(dao).save(argThat(row -> row.amount.intValue() == 100));

        //verify(dao).save(getMatcherForAmount(100));

        //verify(dao).save(getMatcherForAmountWithMessage(100));

    }

    @Test
    public void testingDaoWithMockitoAndMatcherExample() {
        dao.save(new InvoiceRow(new BigDecimal(1), LocalDate.now()));

        verify(dao).save(argThat(row -> row.amount.intValue() == 1));

        verify(dao).save(getMatcherForAmount(1));

        verify(dao).save(getMatcherForAmountWithMessage(1));
    }

    // CC TESTS:

    @Test
    public void periodStartsOn10th_verifyFirstInstallmentDateIsOn10th() {

        // test 01
        BigDecimal amount = new BigDecimal(10);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-02-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

    }

    @Test
    public void periodStartsAfter10th_verifyFirstInstallmentDateIsOnPeriodStart() {

        // test 02
        BigDecimal amount = new BigDecimal(10);
        LocalDate periodStart = asDate("2017-01-11");
        LocalDate periodEnd = asDate("2017-02-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

    }

    @Test
    public void periodStartsBefore10th_verifyFirstInstallmentDateIsOn10th() {

        // test 03
        BigDecimal amount = new BigDecimal(10);
        LocalDate periodStart = asDate("2017-01-09");
        LocalDate periodEnd = asDate("2017-02-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

    }

    @Test
    public void periodEndsBefore10th_verifyNoInstallmentInPeriodEndMonth() {

        // test 04
        BigDecimal amount = new BigDecimal(10);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-02-09");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

    }

    @Test
    public void periodSpansOverYearBoundary_verifyNoBugsInPayments() {

        // test 05
        BigDecimal amount = new BigDecimal(10);
        LocalDate periodStart = asDate("2017-12-01");
        LocalDate periodEnd = asDate("2018-02-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

    }

    @Test
    public void amountDividesEqually() {

        // test 06
        BigDecimal amount = new BigDecimal(9);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-03-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

    }

    @Test
    public void amountNotDividesEqually_verifyReminderIsAddedToLastInstallment() {

        // test 07
        BigDecimal amount = new BigDecimal(7);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-02-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that produced 2 rows with amounts 3 and 4

    }

    @Test
    public void amountNotDivideEqually_verifyReminderIsDividedBetweenLastInstallments() {

        // test 08
        BigDecimal amount = new BigDecimal(11);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-03-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that produced three rows with amounts 3, 4 and 4
    }

    @Test
    public void installmentMustBeAtLeast3Euro_verifySmallerInstallmentsAreNotPayed() {

        // test 09
        BigDecimal amount = new BigDecimal(6);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-03-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that produced 2 rows with amounts 3 and 3
    }

    @Test
    public void paymentIsLessThan3Euros_verifyTheOnlyInstallmentIsEqualToPayment() {

        // test 10
        BigDecimal amount = new BigDecimal(11);
        LocalDate periodStart = asDate("2017-01-10");
        LocalDate periodEnd = asDate("2017-03-10");
        InvoiceRowGenerator generator = new InvoiceRowGenerator(dao);

        generator.generateInvoiceRows(amount, periodStart, periodEnd);

        // check that produced 1 row with amount 2
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