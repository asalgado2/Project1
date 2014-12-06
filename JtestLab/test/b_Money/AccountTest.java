package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		
		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() throws Exception{											//Failed at first
		setUp();
		testAccount.addTimedPayment("TestId", 10, 20, new Money(10000000, SEK), SweBank, "AccountTest");//Add a timed payment to test addPayment() method
		assertTrue(testAccount.timedPaymentExists("TestId"));											//Now that TestId is added, use assert true with exist() method to check and see if it is added 
		testAccount.removeTimedPayment("TestId");														//Remove same test payment to test removePayment() method
		assertFalse(testAccount.timedPaymentExists("TestId"));											//Now that TestId is removed, use assert false with exist() method to check and see if it is removed 
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException,Exception {						//Failed at first
		setUp();
		assertFalse(testAccount.timedPaymentExists("test"));
		
	}

	@Test
	public void testAddWithdraw() throws Exception {													//Failed at first
		setUp();
		testAccount.withdraw(new Money(10, SEK));					//Withdraw 10 in SEK to test withDraw() method
		assertEquals(1499999,testAccount.getBalance().getAmount()); //Because getAmount returns an int, we use 1499999 instead of 1499998.5
	}
	
	@Test
	public void testGetBalance() throws Exception{														//Failed at first
		setUp();
		assertEquals(1500000,testAccount.getBalance().getAmount()); //Use chain method to obtain the money, and then use get method to obtain the amount
	}
}
