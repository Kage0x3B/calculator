package de.syscy.calculator;

import de.syscy.calculator.expression.*;
import de.syscy.calculator.tokenizer.Token;
import de.syscy.calculator.tokenizer.TokenType;

import java.util.Arrays;
import java.util.function.Function;

public class Parser {
	private Token[] tokens;
	private int currentIndex = 0;

	public Expression parse(Token[] tokens) {
		this.tokens = tokens;

		return parseNext().enforceOperatorPrecedence();
	}

	public Expression parseBlock(Token[] blockTokens) {
		this.tokens = blockTokens;

		return parseNext().enforceOperatorPrecedence();
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
			case NAME:
				Token nameToken = current();
				Token nextToken = next();

				if(nextToken != null && nextToken.getType() == TokenType.BLOCK_START) {
					Function<Double, Double> function = FunctionList.getFunction(nameToken.getText());

					if(function == null) {
						throw new IllegalArgumentException("Math function '" + nameToken.getText() + "' not found, check the supported functions or add a custom function");
					}

					currentIndex++;

					return new FunctionExpression(nameToken.getText(), function, parseNext());
				} else {
					Expression leftExpression = new VariableExpression(nameToken.getText());

					if(currentIndex >= tokens.length - 1) {
						return leftExpression;
					}

					currentIndex++;

					return parseOperation(leftExpression);
				}
			case NUMBER:
				Expression leftExpression = parseConstant();

				if(currentIndex >= tokens.length - 1) {
					return leftExpression;
				}

				currentIndex++;

				return parseOperation(leftExpression);
			case OPERATOR:
				throw new IllegalArgumentException("Expression without previous value");
			case BLOCK_START:
				int lastBlockEnd = -1;

				// The tokenizer automatically inserts missing brackets at the end, this code doesn't have to worry about that
				for(int i = currentIndex + 1; i < tokens.length; i++) {
					TokenType type = tokens[i].getType();

					if(type == TokenType.BLOCK_END) {
						lastBlockEnd = i;
					}
				}

				Token[] blockTokens = Arrays.copyOfRange(tokens, currentIndex + 1, lastBlockEnd);

				System.out.println("Block from " + (currentIndex + 1) + " to " + lastBlockEnd);

				Parser blockParser = new Parser();

				currentIndex = lastBlockEnd + 1;

				leftExpression = new BlockExpression(blockParser.parseBlock(blockTokens));

				return leftExpression;
			case BLOCK_END:
				throw new IllegalStateException("This should never be reached!");
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
			throw new IllegalArgumentException("Number must be followed by an operation at " + current() + " (index: " + currentIndex + ")");
		}

		OperationType operationType = OperationType.fromSymbol(current().getText());

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