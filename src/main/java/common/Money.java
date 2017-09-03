package common;

public class Money {

    private int amount;
    private String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money plus(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("can't add different currencies");
        }

        return new Money(amount + money.amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money))
            return false;

        Money money = (Money) obj;
        return amount == money.amount && currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

}
