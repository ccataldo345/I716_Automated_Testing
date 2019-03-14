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

    private BankService mockBankService = mock(BankService.class);

    @Test
    public void transferSuccessScenario() {
        // ...
        TransferService transferService = new TransferService(mockBankService);
        when(mockBankService.getBalance("123")).thenReturn(1000);
        transferService.transferMoney(10, "123", "456");
        verify(mockBankService).transfer(10, "123", "456");

    }

    @Test
    public void transferringNegativeAmountFails() {
        // ...
        TransferService transferService = new TransferService(mockBankService);
        transferService.transferMoney(-1, "123", "456");
        verify(mockBankService, never()).getBalance(anyString());
    }

    @Test
    public void transferFailsWhenNotEnoughFunds() {
        // ...
        TransferService transferService = new TransferService(mockBankService);
        when(mockBankService.getBalance("123")).thenReturn(5);
        transferService.transferMoney(10, "123", "456");
        verify(mockBankService, never()).transfer(anyInt(), anyString(), anyString());
        //verify(mockBankService, never()).transfer(0, "123", "456");
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
