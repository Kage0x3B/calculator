package de.syscy.calculator.expression;

import de.syscy.calculator.ExpressionEnvironment;

public class ConstantExpression extends Expression {
	private double value;

	public ConstantExpression(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public double calculate(ExpressionEnvironment environment) {
		return value;
	}

	@Override
	public Expression enforceOperatorPrecedence() {
		return this;
	}

	@Override
	public Expression simplify() {
		return this;
	}

	@Override
	public String toString() {
		return "Const{" + "v=" + value + '}';
	}
}