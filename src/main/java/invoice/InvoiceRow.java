package invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

class InvoiceRow {

    public final BigDecimal amount;
    public final LocalDate date;

    public InvoiceRow(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "InvoiceRow{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}