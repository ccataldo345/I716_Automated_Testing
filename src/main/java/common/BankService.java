package common;

public interface BankService {

    /**
     * Withdraw the amount form the account.
     * @param amount - which amount
     * @param accountNumber - from which account
     */
    void withdraw(Money amount, String accountNumber);

    /**
     * Deposit the amount to the account.
     * @param amount - which amount
     * @param accountNumber - to which account
     */
    void deposit(Money amount, String accountNumber);

    /**
     * Convert the amount to target currency.
     * @param amount - amount to be converted
     * @param targetCurrency - to which currency
     * @return the converted amount
     */
    Money convert(Money amount, String targetCurrency);

    /**
     * Returns the currency of the account.
     * @param accountNumber - which account
     * @return currency of the account
     */
    String getAccountCurrency(String accountNumber);

    /**
     * Tells whether the account has required amount.
     * @param requiredAmount - how much
     * @param accountNumber - which account
     * @return true if there is enough money
     */
    boolean hasSufficientFundsFor(Money requiredAmount, String accountNumber);
}