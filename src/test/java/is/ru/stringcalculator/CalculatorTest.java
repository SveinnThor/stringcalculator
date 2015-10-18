package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
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
    public void testMultipleNumbers() {
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testMultipleNumbersNewDelim1() {
    	assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void testMultipleNumbersNewDelim2() {
    	assertEquals(6, Calculator.add("1,2\n3"));
    }

    @Test
    public void testMultipleNumbersNewDelim3() {
    	assertEquals(10, Calculator.add("1\n2,3\n4"));
    }

    @Test
    public void testMultipleNumbersNewDelim4() {
    	assertEquals(6, Calculator.add("1\n2\n3"));
    }

    @Test
    public void testDifferentDelim1() {
    	assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test
    public void testDifferentDelim2() {
    	assertEquals(6, Calculator.add("//:\n1:2:3"));
    }

    @Test
    public void testDifferentDelim3() {
    	assertEquals(6, Calculator.add("//*\n1*2*3"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage1() throws RuntimeException {

    thrown.expect(RuntimeException.class);
    thrown.expectMessage("Negatives not allowed: -4,-5");
    Calculator.add("2,-4,3,-5");
    }

    @Test
    public void shouldTestExceptionMessage2() throws RuntimeException {

    thrown.expect(RuntimeException.class);
    thrown.expectMessage("Negatives not allowed: -4,-5,-99");
    Calculator.add("2,-4,3,-5,-99");
    }

    @Test
    public void shouldTestExceptionMessage3() throws RuntimeException {

    thrown.expect(RuntimeException.class);
    thrown.expectMessage("Negatives not allowed: -5,-99,-75,-88");
    Calculator.add("2,-5,-99,13,18,74,-75,-88");
    }

    @Test
    public void ignoreNumbersLargerThan10001() {
        assertEquals(2, Calculator.add("1001,2"));
    }

    @Test
    public void ignoreNumbersLargerThan10002() {
        assertEquals(1001, Calculator.add("1001,2,999"));
    }

    @Test
    public void ignoreNumbersLargerThan10003() {
        assertEquals(1001, Calculator.add("1001,1000,1"));
    }

    @Test
    public void delimiterOfAnyLength() {
        assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void delimiterOfAnyLength1() {
        assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void delimiterOfAnyLength2() {
        assertEquals(6, Calculator.add("//[{{{]\n1{{{2{{{3"));
    }

    @Test
    public void delimiterOfAnyLength3() {
        assertEquals(6, Calculator.add("//[,{,]\n1,{,2,{,3"));
    }
}