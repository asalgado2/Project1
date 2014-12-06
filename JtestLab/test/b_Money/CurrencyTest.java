package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() throws Exception {	//setUp() throws an exception, therefore this method must throw an exception
		setUp();									//Create Currency objects to test on
		assertEquals("SEK",SEK.getName());			//compare the name given to SEK in setUp() to the name returned from getName()
	}
	
	@Test
	public void testGetRate() throws Exception{ 	//setUp() throws an exception, therefore this method must throw an exception
		setUp();									//Create Currency objects to test on

		assertEquals(0.15,SEK.getRate(),0.1);		//compare the rate given to SEK in setUp() to the rate returned from getRate()
	}
	
	@Test
	public void testSetRate() throws Exception{ 	//setUp() throws an exception, therefore this method must throw an exception
		setUp();									//Create Currency objects to test on
		double d = 0.14;							//Create Double object to compare in assertEquals
		SEK.setRate(d);								//Test setRate() by trying to set a new rate
		assertEquals(0.14,SEK.getRate(),0.1);		//compare the rate inputed in setRate to the rate returned from getRate()
	}
	
	@Test
	public void testUniversalValue() { 			   
		assertEquals(150,EUR.universalValue(100));  //Convert the the amount of 100 EUR to its Universal Currency
	}												//Compare this value to the returned value from Universal Value()
	
	@Test
	public void testValueInThisCurrency() {				//Convert the amount of 1000 EUR to the currency DKK
		assertEquals(200,EUR.valueInThisCurrency(1000,DKK)); //The value of 1000 EUR should convert to 200 DKK
	}

}
