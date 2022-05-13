import de.syscy.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Division {
	@Test
	public void division1() {
		double result = Calculator.calculate("6/2");

		assertEquals(result, 3);
	}

	@Test
	public void divisionByZero() {
		assertThrows(RuntimeException.class, () -> {
			Calculator.calculate("2/0");
		});
	}
}
