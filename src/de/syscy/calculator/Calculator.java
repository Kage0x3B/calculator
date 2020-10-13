package de.syscy.calculator;

import de.syscy.calculator.expression.Expression;

import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String expressionString = "";

		while(!expressionString.equals("exit")) {
			System.out.print("> ");
			expressionString = scanner.nextLine();
			System.out.println();

			Tokenizer tokenizer = new Tokenizer();
			tokenizer.tokenize(expressionString);

			tokenizer.forEach(System.out::println);

			Parser parser = new Parser();
			Expression expression = parser.parse(tokenizer.toArray(new Token[0]));

			System.out.println("Expression: " + expression + " = " + expression.calculate());
		}
	}
}