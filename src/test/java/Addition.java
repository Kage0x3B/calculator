import de.syscy.calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Addition {
	@Test
	public void addition1() {
		assertEquals(Calculator.calculate("1+1"), 2);
	}

	@Test
	public void addition2() {
		assertEquals(Calculator.calculate("2+3"), 5);
	}

	@Test
	public void addition3() {
		assertEquals(Calculator.calculate("0+0"), 0);
	}

	@Test
	public void additionNegativeValues1() {
		assertEquals(Calculator.calculate("(-2)+1"), -1);
	}

	@Test
	public void additionNegativeValuesWithBrackets1() {
		assertEquals(Calculator.calculate("(-2)+(-3)"), -5);
	}
}
