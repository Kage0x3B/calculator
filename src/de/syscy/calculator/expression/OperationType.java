package de.syscy.calculator.expression;

import java.util.function.BiFunction;

public enum OperationType {
	ADDITION('+', 0, Double::sum),
	SUBTRACTION('-', 0, (v1, v2) -> v1 - v2),
	MULTIPLICATION('*', 1, (v1, v2) -> v1 * v2),
	DIVISION('/', 1, (v1, v2) -> v1 / v2),
	MODULO('%', 1, (v1, v2) -> v1 % v2);

	private final char symbol;
	private final int operatorPrecedence;
	private final BiFunction<Double, Double, Double> executeFunction;

	OperationType(char symbol, int operatorPrecedence, BiFunction<Double, Double, Double> executeFunction) {
		this.symbol = symbol;
		this.operatorPrecedence = operatorPrecedence;
		this.executeFunction = executeFunction;
	}

	public static OperationType fromSymbol(char symbol) {
		for(OperationType operationType : values()) {
			if(operationType.symbol == symbol) {
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