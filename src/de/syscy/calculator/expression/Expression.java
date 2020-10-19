package de.syscy.calculator.expression;

import de.syscy.calculator.ExpressionEnvironment;

public abstract class Expression {
	public abstract double calculate(ExpressionEnvironment environment);

	public abstract Expression enforceOperatorPrecedence();

	public abstract Expression simplify();
}