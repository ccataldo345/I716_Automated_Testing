package stub;

import java.util.Date;
import java.util.List;

import common.Money;

public class Report {

    private Bank bank;

    public Report(Bank bank) {
        this.bank = bank;
    }

    public Report() {}

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Money getTotalIncomeBetween(Date startDate, Date endDate) {
        List<Money> incomes = getIncomesBetween(startDate, endDate);

        Money total = new Money(0, "EUR");

        for (Money each : incomes) {
            total = total.plus(bank.convert(each, "EUR"));
        }

        return total;
    }

    protected List<Money> getIncomesBetween(Date startDate, Date endDate) {
        throw new IllegalStateException("can't connect..");
    }

}