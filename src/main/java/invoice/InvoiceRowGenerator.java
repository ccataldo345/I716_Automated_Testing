
package invoice;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class InvoiceRowGenerator {
    private static final int PAYMENT_DAY = 10;
    private static final int MIN_PAYMENT = 3;
    private InvoiceRowDao invoiceRowDao;

    public InvoiceRowGenerator(InvoiceRowDao dao) {
        this.invoiceRowDao = dao;
    }

    public List<InvoiceRow> generateInvoiceRows(BigDecimal amount, LocalDate periodStart, LocalDate periodEnd) {
        validateAmount(amount);
        validateDateOrder(periodStart, periodEnd);

        List<LocalDate> dates = getPaymentDays(periodStart, periodEnd);
        List<BigDecimal> amounts = getAmounts(amount, BigDecimal.valueOf(dates.size()));
        List<InvoiceRow> invoiceRows = getListOfInstallments(dates, amounts);

        for (InvoiceRow invoiceRow : invoiceRows) {
            invoiceRowDao.save(invoiceRow);
            System.out.println(invoiceRow);
        }

        return invoiceRows;
    }

    private List<InvoiceRow> getListOfInstallments(List<LocalDate> dates, List<BigDecimal> payments) {
        List<InvoiceRow> installments = new ArrayList<>();
        for (int i = 0; i < payments.size(); i++) {
            InvoiceRow installment = new InvoiceRow(payments.get(i), dates.get(i));
            installments.add(installment);
        }
        return installments;
    }

    private List<BigDecimal> getAmounts(BigDecimal amount, BigDecimal numberOfPayments) {
        List<BigDecimal> payments = new ArrayList<>();

        if (amount.intValue() < MIN_PAYMENT) {
            //if amount less than minimal payment so we have only one payment equal to amount
            payments.add(amount);
        } else {
            //Getting one payment by dividing the amount on the number of payments
            //RoundingMode.DOWN - when you divide two integers have the same behavior
            BigDecimal onePayment = amount.divide(numberOfPayments, RoundingMode.DOWN);
            while (onePayment.intValue() < MIN_PAYMENT) {
                /*if a value of one payment less than minimal payment we should decrease
                the number of payments until one payment became more or equals to minimal
                payment */
                numberOfPayments = numberOfPayments.subtract(BigDecimal.valueOf(1));
                onePayment = amount.divide(numberOfPayments, RoundingMode.DOWN);
            }
            //now we can generate all payments
            payments = generateAmounts(onePayment, numberOfPayments.intValue());

            int remainder = amount.remainder(numberOfPayments).intValue();
            if (remainder > 0) {
                //if we have remainder we should distribute it between other payments
                updateAmounts(remainder, payments);
            }
        }
        return payments;
    }

    private List<BigDecimal> generateAmounts(BigDecimal onePayment, int numberOfPayments) {
        List<BigDecimal> payments = new ArrayList<>();
        for (int i = 0; i < numberOfPayments; i++) {
            payments.add(onePayment);
        }
        return payments;
    }

    private List<BigDecimal> updateAmounts(int remainder, List<BigDecimal> payments) {
        for (int i = 0; i < remainder; i++) {
            BigDecimal updatedValue = payments.get(i).add(BigDecimal.valueOf(1));
            payments.set(i, updatedValue);
        }
        Collections.reverse(payments);
        return payments;
    }

    private List<LocalDate> getPaymentDays(LocalDate periodStart, LocalDate periodEnd) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate firstDateOfPayment = getFirstDateOfPayment(periodStart);
        dates.add(firstDateOfPayment);

        LocalDate nextPaymentDate = firstDateOfPayment.plusMonths(1).withDayOfMonth(PAYMENT_DAY);
        while (nextPaymentDate.isBefore(periodEnd) || nextPaymentDate.isEqual(periodEnd)) {
            dates.add(nextPaymentDate);
            nextPaymentDate = nextPaymentDate.plusMonths(1);
        }
        return dates;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.intValue() < 0) {
            throw new IllegalArgumentException(
                    "The amount is negative!");
        }
    }

    private void validateDateOrder(LocalDate periodStart, LocalDate periodEnd) {
        if (periodStart.isAfter(periodEnd)) {
            throw new IllegalArgumentException(
                    "The start period date is after the end period date!");
        }
    }

    private LocalDate getFirstDateOfPayment(LocalDate periodStart) {
        if (periodStart.isBefore(periodStart.withDayOfMonth(PAYMENT_DAY))) {
            return periodStart.withDayOfMonth(PAYMENT_DAY);
        }
        return periodStart;
    }
}
