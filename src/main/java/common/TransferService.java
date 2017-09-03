package common;

public class TransferService {

    private BankService bankService;

    public TransferService(BankService bankService) {
        this.bankService = bankService;
    }

    public void transfer(Money money, String fromAccount, String toAccount) {

        // convert to the currency of the account being reduced and reduce the account
        // if input currency happens already be the right one then the conversion has no effect
        // (converting 1 Euro to Euros is still 1 Euro)

        String fromCurrency = bankService.getAccountCurrency(fromAccount);
        Money withdrawAmountConverted = bankService.convert(money, fromCurrency);

        if (!bankService.hasSufficientFundsFor(withdrawAmountConverted, fromAccount))
            return;

        bankService.withdraw(withdrawAmountConverted, fromAccount);

        // convert to the currency of the account being increased and increase the account

        String toCurrency = bankService.getAccountCurrency(toAccount);
        Money depositAmountConverted = bankService.convert(money, toCurrency);
        bankService.deposit(depositAmountConverted, toAccount);
    }
}