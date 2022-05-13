package de.syscy.calculator.expression;

import java.util.function.BiFunction;

public enum OperationType {
	ADDITION("+", 0, Double::sum),
	SUBTRACTION("-", 0, (v1, v2) -> v1 - v2),
	MULTIPLICATION("*", 1, (v1, v2) -> v1 * v2),
	DIVISION("/", 1, (v1, v2) -> {
		if(v2 == 0) {
			throw new RuntimeException("Division by zero");
		}

		return v1 / v2;
	}),
	MODULO("%", 1, (v1, v2) -> v1 % v2),
	POW("**", 2, Math::pow),
	AND("&&", 3, (v1, v2) -> v1 > 0 && v2 > 0 ? 1.0 : 0.0),
	OR("||", 4, (v1, v2) -> v1 > 0 || v2 > 0 ? 1.0 : 0.0);

	private final String symbol;
	private final int operatorPrecedence;
	private final BiFunction<Double, Double, Double> executeFunction;

	OperationType(String symbol, int operatorPrecedence, BiFunction<Double, Double, Double> executeFunction) {
		this.symbol = symbol;
		this.operatorPrecedence = operatorPrecedence;
		this.executeFunction = executeFunction;
	}

	public static OperationType fromSymbol(String symbol) {
		for(OperationType operationType : values()) {
			if(operationType.symbol.equals(symbol)) {
				return operationType;
			}
		}

		return null;
	}

	public double execute(double v1, double v2) {
		return executeFunction.apply(v1, v2);
	}

	public int getOperatorPrecedence() {
		return operatorPrecedence;
	}
}