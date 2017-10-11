package custommock;

import common.BankService;
import common.Money;

public class TestableBankService implements BankService {

    @Override
    public void withdraw(Money money, String fromAccount) {
        System.out.println("withdraw called with: " + money + " and " + fromAccount);

        // here the arguments (money and fromAccount) must be remembered

        throw new IllegalStateException("not implemented yet");
    }

    @Override
    public void deposit(Money money, String toAccount) {
        System.out.println("deposit called with: " + money + " and " + toAccount);

        // here the arguments (money and toAccount) must be remembered

        throw new IllegalStateException("not implemented yet");
    }

    public boolean wasWithdrawCalledWith(Money money, String account) {

        // here you have to compare current arguments with the ones remembered

        throw new IllegalStateException("not implemented yet");
    }

    public boolean wasDepositCalledWith(Money money, String account) {

        // here you have to compare current arguments with the ones remembered

        throw new IllegalStateException("not implemented yet");
    }

    @Override
    public Money convert(Money money, String targetCurrency) {
        if (money.getCurrency().equals(targetCurrency)) return money;

        double rate = 1.0/10;

        return new Money((int) (money.getAmount() / rate), targetCurrency);
    }

    @Override
    public String getAccountCurrency(String account) {
        switch (account) {
            case "E_123": return "EUR";
            case "S_456": return "SEK";
            default: throw new IllegalStateException();
        }
    }

    @Override
    public boolean hasSufficientFundsFor(Money requiredAmount, String account) {
        return true;
    }

    public void setSufficientFundsAvailable(boolean areFundsAvailable) {
        // at the moment hasSufficientFundsFor() returns always true.
        // this method is for configuring how hasSufficientFundsFor() responds.
    }

    public boolean wasWithdrawCalled() {
        // This method should say whether withdraw() method was called
        // (without considering arguments)

        throw new IllegalStateException("not implemented yet");
    }

}