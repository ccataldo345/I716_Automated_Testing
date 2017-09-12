package mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import common.BankService;
import common.TransferService;
import common.Money;

@SuppressWarnings("unused")
public class TransferServiceTestMockito {

    BankService bankService = mock(BankService.class);
    TransferService transferService = new TransferService(bankService);

    @Test
    public void transferWithCurrencyConversion() {

        when(bankService.getAccountCurrency("E_123")).thenReturn("EUR");
        // when(...
        // what needs to be taught can be found in common.TransferService.transfer() method.

        // transfer 1 EUR from account E_123 to account S_456
        // E_123 accounts currency is EUR
        // S_456 accounts currency is SEK
        transferService.transfer(new Money(1, "EUR"), "E_123", "S_456");

        verify(bankService).withdraw(new Money(1, "EUR"), "E_123");
        // verify(bankService).deposit(...
    }

    @Test
    public void transferWhenNotEnoughFunds() {
        // ...
    }

    private Money anyMoney() {
        return (Money) any();
    }

    private String anyAccount() {
        return anyString();
    }
}