import de.syscy.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Subtraction {
	@Test
	public void subtraction1() {
		double result = Calculator.calculate("1+1");

		assertEquals(result, 2);
	}

	@Test
	public void subtraction2() {
		double result = Calculator.calculate("2+3");

		assertEquals(result, 2);
	}
}
