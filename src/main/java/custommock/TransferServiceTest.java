package custommock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import common.*;

public class TransferServiceTest {

    @Test
    public void transfersWithCurrencyConversion() {

        TestableBankService bankService = new TestableBankService();
        TransferService transferService = new TransferService(bankService);

        // E_123 accounts currency is EUR
        // S_456 accounts currency is SEK

        // transfer 1 EUR from E_123 to S_456
        transferService.transfer(new Money(1, "EUR"), "E_123", "S_456");


        assertTrue(bankService.wasWithdrawCalledWith(new Money(1, "EUR"), "E_123"));
        assertFalse(bankService.wasWithdrawCalledWith(null, null));

        assertTrue(bankService.wasDepositCalledWith(new Money(10, "SEK"), "S_456"));
        assertFalse(bankService.wasDepositCalledWith(null, null));
    }

    @Test
    public void doesNotTransferWhenNoFounds() {

        TestableBankService bankService = new TestableBankService();
        bankService.setSufficientFundsAvailable(false);

        TransferService transferService = new TransferService(bankService);

        transferService.transfer(new Money(1, "EUR"), "E_123", "S_456");

        assertFalse(bankService.wasWithdrawCalled());
    }

}