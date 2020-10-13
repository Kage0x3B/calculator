package de.syscy.calculator;

import de.syscy.calculator.expression.ConstantExpression;
import de.syscy.calculator.expression.Expression;
import de.syscy.calculator.expression.OperationExpression;
import de.syscy.calculator.expression.OperationType;

public class Parser {
	private Token[] tokens;
	private int currentIndex = 0;

	public Expression parse(Token[] tokens) {
		this.tokens = tokens;

		return parseNext().enforceOperatorPrecedence();
		//TODO: Enable simplification again
		//return parseNext().simplify();
	}

	private Token previous() {
		if(currentIndex == 0) {
			return null;
		}

		return tokens[currentIndex - 1];
	}

	private Token current() {
		if(currentIndex >= tokens.length) {
			return null;
		}

		return tokens[currentIndex];
	}

	private Token next() {
		if(currentIndex >= tokens.length - 1) {
			return null;
		}

		return tokens[currentIndex + 1];
	}

	public Expression parseNext() {
		if(current() == null) {
			return null;
		}

		switch(current().getType()) {
			case NUMBER:
				Expression leftExpression = parseConstant();

				if(currentIndex >= tokens.length - 1) {
					return leftExpression;
				}

				currentIndex++;

				return parseOperation(leftExpression);
			case OPERATOR:
				throw new IllegalArgumentException("Expression without previous value");
			default:
				throw new RuntimeException("Operation '" + current().getType().name() + "' is not implemented");
		}
	}

	public Expression parseConstant() {
		if(current() == null) {
			return new ConstantExpression(0);
		}

		return new ConstantExpression(Double.parseDouble(current().getText()));
	}

	public Expression parseOperation(Expression leftExpression) {
		if(current() == null || current().getType() != TokenType.OPERATOR) {
			throw new IllegalArgumentException("Number must be followed by an operation");
		}

		OperationType operationType = OperationType.fromSymbol(current().getText().charAt(0));

		if(operationType == null) {
			throw new IllegalArgumentException("Invalid operation '" + current().getText().charAt(0) + "'");
		}

		currentIndex++;
		Expression rightExpression = parseNext();

		if(rightExpression == null) {
			throw new IllegalArgumentException("Operation has no right hand expression or number");
		}

		return new OperationExpression(operationType, leftExpression, rightExpression);
	}
}