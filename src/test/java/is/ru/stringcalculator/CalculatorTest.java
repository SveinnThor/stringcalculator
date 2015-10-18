package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber1() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testOneNumber2() {
		assertEquals(5, Calculator.add("5"));
	}

	@Test
	public void testOneNumber3() {
		assertEquals(9, Calculator.add("9"));
	}

	@Test
	public void testOneNumber4() {
		assertEquals(99, Calculator.add("99"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }
}