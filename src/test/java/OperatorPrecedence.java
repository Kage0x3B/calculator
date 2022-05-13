import de.syscy.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperatorPrecedence {
	@Test
	public void opPrecedence1() {
		double result = Calculator.calculate("1+2*3");

		assertEquals(result, 7);
	}

	@Test
	public void opPrecedence2() {
		double result = Calculator.calculate("2*3+1");

		assertEquals(result, 7);
	}
}
