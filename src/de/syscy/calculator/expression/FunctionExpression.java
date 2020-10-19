package de.syscy.calculator.expression;

import de.syscy.calculator.ExpressionEnvironment;

import java.util.function.Function;

public class FunctionExpression extends Expression {
	private final String name;
	private final Function<Double, Double> function;
	private Expression innerExpression;

	public FunctionExpression(String name, Function<Double, Double> function, Expression innerExpression) {
		this.name = name;
		this.function = function;
		this.innerExpression = innerExpression;
	}

	@Override
	public double calculate(ExpressionEnvironment environment) {
		return function.apply(innerExpression.calculate(environment));
	}

	@Override
	public Expression enforceOperatorPrecedence() {
		return this;
	}

	@Override
	public Expression simplify() {
		this.innerExpression = this.innerExpression.simplify();

		if(this.innerExpression instanceof ConstantExpression) {
			return new ConstantExpression(function.apply(((ConstantExpression) this.innerExpression).getValue()));
		}

		return this;
	}

	@Override
	public String toString() {
		return "Func{" + "f=" + name + ", inner=" + innerExpression + '}';
	}
}