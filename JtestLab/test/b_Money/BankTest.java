package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() throws Exception{
		setUp();
		assertEquals("SweBank",SweBank.getName());
	}

	@Test
	public void testGetCurrency() throws Exception{
		setUp();
		assertEquals(SEK,SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException,Exception {
		setUp();

		SweBank.openAccount("Test");
		//assertTrue(accountlist.get(accountid).timedPaymentExists("Test"));	
		try{
			SweBank.openAccount("Ulrika");
		}
		catch(AccountExistsException e){
			System.out.println("Caught the exception");
		}

	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException ,Exception{ //Failed at first
		setUp();
		SweBank.deposit("Ulrika", new Money(100,SEK));
		assertEquals(15,SweBank.getBalance("Ulrika"));
		
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException,Exception { //Failed at first
		setUp();
		SweBank.withdraw("Ulrika", new Money(200,SEK));
		assertEquals(-30,SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException,Exception { //Failed at first
		setUp();
		assertEquals(0,SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException,Exception { //Failed at first
		//Testing transfer between two banks and then between two accounts from same bank
		setUp();
		Money m = new Money(200,SEK);
		SweBank.deposit("Ulrika", m);				
		SweBank.transfer("Ulrika", Nordea, "Bob", m);
		assertEquals(30,Nordea.getBalance("Bob"));
		assertEquals(0,SweBank.getBalance("Ulrika"));
		
		SweBank.deposit("Ulrika", new Money(20,SEK));				
		SweBank.transfer("Ulrika", "Bob", new Money(20,SEK));
		assertEquals(30,SweBank.getBalance("Bob"));
		assertEquals(0,SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException,Exception {
		setUp();
		Account testAccount = new Account("name",SEK);
		SweBank.addTimedPayment("name", "payIdTest", 10, 20, new Money(10000000, SEK), Nordea, "Bob");//Add a timed payment to test addPayment() method
		assertTrue(testAccount.timedPaymentExists("payIdTest"));											//Now that TestId is added, use assert true with exist() method to check and see if it is added 
		SweBank.removeTimedPayment("name","payIdTest");														//Remove same test payment to test removePayment() method
		assertFalse(testAccount.timedPaymentExists("payIdTest"));											//Now that TestId is removed, use assert false with exist() method to check and see if it is removed 
		
	}
}
