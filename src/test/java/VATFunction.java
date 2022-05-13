import de.syscy.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VATFunction {
	@Test
	public void testCalculateVat() {
		assertEquals(Calculator.calculate("calculateVat(100)"), 19.0);
	}

	@Test
	public void testCalculateVatAlias() {
		assertEquals(Calculator.calculate("vat(100)"), 19.0);
	}

	@Test
	public void testCalculateChangedVat() {
		assertEquals(Calculator.calculate("vat(100)"), 1.0);
	}
}
