package de.syscy.calculator.expression;

import de.syscy.calculator.ExpressionEnvironment;

public class BlockExpression extends Expression {
	private Expression innerExpression;

	public BlockExpression(Expression innerExpression) {
		this.innerExpression = innerExpression;
	}

	@Override
	public double calculate(ExpressionEnvironment environment) {
		return innerExpression.calculate(environment);
	}

	@Override
	public Expression enforceOperatorPrecedence() {
		innerExpression.enforceOperatorPrecedence();

		return this;
	}

	@Override
	public Expression simplify() {
		this.innerExpression = innerExpression.simplify();

		if(this.innerExpression instanceof ConstantExpression) {
			return this.innerExpression;
		}

		return this;
	}

	@Override
	public String toString() {
		return "B{" + "inner=" + innerExpression + '}';
	}
}