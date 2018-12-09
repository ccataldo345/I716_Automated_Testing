package invoice;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceRowGenerator {

    private InvoiceRowDao invoiceRowDao;
    // private BigDecimal amount = BigDecimal.valueOf(100);
    // private LocalDate dateStart;
    // private LocalDate dateEnd;


    // constructor
    public InvoiceRowGenerator(InvoiceRowDao dao) {
        this.invoiceRowDao = dao;
    }

    public void generateInvoiceRows(BigDecimal amount, LocalDate periodStart, LocalDate periodEnd) {
        // https://stackoverflow.com/questions/3395825/how-to-print-formatted-bigdecimal-values
        //String myAmount = NumberFormat.getCurrencyInstance().format(amount);
        //System.out.println("Total amount is: " + myAmount );

        List<InvoiceRow> invoiceRows = new ArrayList<>();

        invoiceRows.add(new InvoiceRow(amount, periodStart));
        invoiceRows.add(new InvoiceRow(amount, periodEnd));

        for (InvoiceRow i: invoiceRows) {

            invoiceRowDao.save(i);
        }

    }
}
