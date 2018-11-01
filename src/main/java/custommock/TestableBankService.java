package custommock;

import common.BankService;
import common.Money;

public class TestableBankService implements BankService {

    private boolean withdrawCalled = false;
    private Money money;
    private String fromAccount;
    private String toAccount;
    public boolean areFundsAvailable = false;

    @Override
    public void withdraw(Money money, String fromAccount) {
        System.out.println("withdraw called with: " + money + " and " + fromAccount);

        // here the arguments (money and fromAccount) must be remembered
        withdrawCalled = true;
        this.money = money;
        this.fromAccount = fromAccount;
    }

    @Override
    public void deposit(Money money, String toAccount) {
        System.out.println("deposit called with: " + money + " and " + toAccount);

        // here the arguments (money and toAccount) must be remembered
        this.money = money;
        this.toAccount = toAccount;
    }

    public boolean wasWithdrawCalledWith(Money money, String account) {

        // here you have to compare current arguments with the ones remembered
        if (money == null || account == null) {
            return false;
        } else if (money.equals(this.money) && account.equals(this.fromAccount));
        return true;
    }

    public boolean wasDepositCalledWith(Money money, String account) {

        // here you have to compare current arguments with the ones remembered
        if (money == null || account == null) {
            return false;
        } else if (money.equals(this.money) && account.equals(this.toAccount));
        return true;
    }

    @Override
    public Money convert(Money money, String targetCurrency) {
        if (money.getCurrency().equals(targetCurrency)) return money;

        double rate = 1.0 / 10;

        return new Money((int) (money.getAmount() / rate), targetCurrency);
    }

    @Override
    public String getAccountCurrency(String account) {
        switch (account) {
            case "E_123":
                return "EUR";
            case "S_456":
                return "SEK";
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public boolean hasSufficientFundsFor(Money requiredAmount, String account) {
        return true;
    }

    public void setSufficientFundsAvailable(boolean areFundsAvailable) {
        // at the moment hasSufficientFundsFor() returns always true.
        // this method is for configuring how hasSufficientFundsFor() responds.
        this.areFundsAvailable = areFundsAvailable;
    }

    public boolean wasWithdrawCalled() {
        // This method should say whether withdraw() method was called
        // (without considering arguments)
        if (this.areFundsAvailable == false) return false;

        return withdrawCalled;
    }

}