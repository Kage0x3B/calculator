package de.syscy.calculator.expression;

import de.syscy.calculator.ExpressionEnvironment;

public class OperationExpression extends Expression {
	private OperationType operationType;
	private Expression leftValue;
	private Expression rightValue;

	public OperationExpression(OperationType operationType, Expression leftValue, Expression rightValue) {
		this.operationType = operationType;
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}

	@Override
	public double calculate(ExpressionEnvironment environment) {
		return operationType.execute(leftValue.calculate(environment), rightValue.calculate(environment));
	}

	@Override
	public Expression enforceOperatorPrecedence() {
		boolean changed = true;

		while(changed) {
			changed = false;

			if(rightValue instanceof OperationExpression) {
				OperationExpression rightOp = (OperationExpression) rightValue;

				if(rightOp.operationType.getOperatorPrecedence() > this.operationType.getOperatorPrecedence()) {
					this.leftValue = new OperationExpression(this.operationType, this.leftValue, rightOp.leftValue);
					this.operationType = rightOp.operationType;
					this.rightValue = rightOp.rightValue;

					changed = true;
				}
			}
		}

		this.leftValue = leftValue.enforceOperatorPrecedence();
		this.rightValue = rightValue.enforceOperatorPrecedence();

		return this;
	}

	@Override
	public Expression simplify() {
		this.leftValue = this.leftValue.simplify();
		this.rightValue = this.rightValue.simplify();

		if(leftValue instanceof ConstantExpression && rightValue instanceof ConstantExpression) {
			return new ConstantExpression(operationType.execute(((ConstantExpression) leftValue).getValue(), ((ConstantExpression) rightValue).getValue()));
		}

		return this;
	}

	@Override
	public String toString() {
		return "OP{" + "type=" + operationType + ", l=" + leftValue + ", r=" + rightValue + '}';
	}
}