package de.syscy.calculator.expression;

public abstract class Expression {
	public abstract double calculate();

	public abstract Expression enforceOperatorPrecedence();

	public abstract Expression simplify();
}