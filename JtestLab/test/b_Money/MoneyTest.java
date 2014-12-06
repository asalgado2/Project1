package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() throws Exception{ 		//setUp() throws an exception, therefore this method must throw an exception
		setUp();
		assertEquals(10000,SEK100.getAmount());	 //The amount in the object SEK100 should be returned from GetAmount() 
	}

	@Test
	public void testGetCurrency() throws Exception{ 	//setUp() throws an exception, therefore this method must throw an exception
		setUp();
		assertEquals(SEK,SEK100.getCurrency());  // The test case should return the currency in the SEK100 object from GetCurrency() method
	}

	@Test
	public void testToString() throws Exception{		//setUp() throws an exception, therefore this method must throw an exception
		setUp();
		assertEquals("0 SEK",SEK0.toString());			//compare toString() method with the sentence it should return
	}

	@Test
	public void testUniversalValue() throws Exception{	//setUp() throws an exception, therefore this method must throw an exception
		setUp();
		assertEquals(3000,EUR20.universalValue());   //Test case compares the Universal value of the Euro20 object to the returned value from the Universal Value()method
	}

	@Test
	public void testEqualsMoney() throws Exception{		//setUp() throws an exception, therefore this method must throw an exception
		setUp();
		assertTrue(SEK0.equals(EUR0)); //Test case asserts the condition that the value in SEKO object equals the value in EURO is true 
	}

	@Test
	public void testAdd() throws Exception{		
		setUp();
		assertEquals(17000,EUR20.add(SEK100).getAmount()); //Test case compares the Add() method with the amount of money it should return 
	}

	@Test
	public void testSub() throws Exception{
		setUp();
		assertEquals(17000,EUR20.sub(SEKn100).getAmount()); //Compare the Sub() method with the amount of money it should return by producing a positive value
		assertEquals(-300,SEK0.sub(EUR20).getAmount()); //Compare the Sub() method with the amount of money it should return by producing a negative value 
	}

	@Test 
	public void testIsZero() throws Exception{
		setUp();
		assertTrue(EUR0.isZero()); //Test case asserts that the "amount of money in the object EURO is zero" is true 
	}

	@Test
	public void testNegate() throws Exception{
		setUp();
		assertEquals(-1000,EUR10.negate().getAmount()); // Compare the Negate() method to the negative value it should return by using an object with a positive value 
		assertEquals(-10000,SEKn100.negate().getAmount()); //// Compare the Negate() method to the negative value it should return by using an object with a negative value 
	}

	@Test
	public void testCompareTo() throws Exception{
		setUp();
		assertEquals(-1,EUR10.compareTo(SEK100)); //Test the CompareTo() method by making the money in the object less valuable than the other object to produce a -1 value
		assertEquals(1,EUR10.compareTo(SEK0)); // Make the money in the object more valuable than the other object  to produce a value of 1
		assertEquals(0,EUR0.compareTo(SEK0));  //Make the money in the object equal the other object  to produce a 0 value 
	}
}
