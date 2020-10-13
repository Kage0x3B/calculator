package de.syscy.calculator.expression;

public class BlockExpression extends Expression {
	private Expression innerExpression;

	public BlockExpression(Expression innerExpression) {
		this.innerExpression = innerExpression;
	}

	@Override
	public double calculate() {
		return innerExpression.calculate();
	}

	@Override
	public Expression enforceOperatorPrecedence() {
		innerExpression.enforceOperatorPrecedence();

		return this;
	}

	@Override
	public Expression simplify() {
		this.innerExpression = innerExpression.simplify();

		return this;
	}

	@Override
	public String toString() {
		return "B{" + "inner=" + innerExpression + '}';
	}
}