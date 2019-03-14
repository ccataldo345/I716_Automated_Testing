package mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import custommock.TestableBankService;
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
        when(bankService.getAccountCurrency("S_456")).thenReturn("SEK");
        when(bankService.convert(new Money(1, "EUR"), "SEK")).
                thenReturn(new Money(10, "SEK"));
        when(bankService.convert(new Money(1, "EUR"), "EUR")).
                thenReturn(new Money(1, "EUR"));
        when(bankService.hasSufficientFundsFor(new Money(1, "EUR"), "E_123")).
                thenReturn(true);

        transferService.transfer(new Money(1, "EUR"), "E_123", "S_456");

        verify(bankService).withdraw(new Money(1, "EUR"), "E_123");
        verify(bankService).deposit(new Money(10, "SEK"), "S_456");
    }

    @Test
    public void transferWhenNotEnoughFunds() {
        // ...
        transferService.transfer(new Money(1, "EUR"), "E_123", "S_456");

        verify(bankService, never()).withdraw(anyMoney(), anyAccount());
        verify(bankService, never()).deposit(anyMoney(), anyAccount());

    }

    private Money anyMoney() {
        return (Money) any();
    }

    private String anyAccount() {
        return anyString();
    }
}