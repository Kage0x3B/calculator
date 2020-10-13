package de.syscy.calculator.expression;

public class ConstantExpression extends Expression {
	private double value;

	public ConstantExpression(double value) {
		this.value = value;
	}

	@Override
	public double calculate() {
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