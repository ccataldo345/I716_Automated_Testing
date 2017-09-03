package mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import common.Money;

@SuppressWarnings("unused")
public class TransferServiceTestMockitoSimple {

    // BankService and TransferService are at the end of this file

    @Test
    public void transferSuccessScenario() {
        // ...
    }

    @Test
    public void transferringNegativeAmountFails() {
        // ...
    }

    @Test
    public void transferFailsWhenNotEnoughFunds() {
        // ...
    }

}

interface BankService {

    int getBalance(String formAccount);

    void transfer(int amount, String formAccount, String toAccount);

}

class TransferService {

    private BankService bankService;

    public TransferService(BankService bankService) {
        this.bankService = bankService;
    }

    public void transferMoney(int amount, String formAccount, String toAccount) {

        if (amount <= 0 || formAccount.equals(toAccount))
            return;

        if (bankService.getBalance(formAccount) >= amount) {
            bankService.transfer(amount, formAccount, toAccount);
        }
    }
}
