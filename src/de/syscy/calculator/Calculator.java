package de.syscy.calculator;

import de.syscy.calculator.expression.Expression;
import de.syscy.calculator.tokenizer.Token;
import de.syscy.calculator.tokenizer.Tokenizer;

import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ExpressionEnvironment expressionEnvironment = new ExpressionEnvironment();
		String expressionString = "";

		while(!expressionString.equals("exit")) {
			System.out.print("> ");
			expressionString = scanner.nextLine();
			System.out.println();

			if(expressionString.toLowerCase().startsWith("def ")) {
				String defString = expressionString.substring(4);

				String[] defSplit = defString.split("=", 2);

				if(defSplit.length != 2) {
					System.err.println("Invalid format for defining variables, use 'def name=value'");

					continue;
				}

				String defName = defSplit[0].trim();
				String defValueString = defSplit[1];

				try {
					double defValue = Double.parseDouble(defValueString);

					expressionEnvironment.addVariable(defName, defValue);

					System.out.println(defName + " => " + defValue);
				} catch(NumberFormatException ex) {
					System.err.println("Invalid number");
				}

				continue;
			}

			Tokenizer tokenizer = new Tokenizer();
			tokenizer.tokenize(expressionString);

			tokenizer.forEach(System.out::println);

			Parser parser = new Parser();
			Expression expression = parser.parse(tokenizer.toArray(new Token[0]));

			System.out.println("Expression: " + expression + " = " + expression.calculate(expressionEnvironment));
		}
	}
}