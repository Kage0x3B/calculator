package de.syscy.calculator.expression;

import de.syscy.calculator.ExpressionEnvironment;

public class VariableExpression extends Expression {
	private String name;

	public VariableExpression(String name) {
		this.name = name;
	}

	@Override
	public double calculate(ExpressionEnvironment environment) {
		return environment.getVariableValue(name);
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
		return "Var{" + "n=" + name + "}";
	}
}