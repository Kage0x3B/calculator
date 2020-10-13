package de.syscy.calculator;

import java.util.LinkedList;
import java.util.regex.Matcher;

public class Tokenizer extends LinkedList<Token> {
	private static final String CLEAN_REGEX = "[^\\w\\d.,+\\-*/%()]";

	public void tokenize(String expression) {
		clear();

		String cleanExpression = expression.replaceAll(CLEAN_REGEX, "");

		while(cleanExpression.length() > 0) {
			boolean found = false;

			for(TokenType tokenType : TokenType.values()) {
				Matcher matcher = tokenType.getRegex().matcher(cleanExpression);

				if(matcher.lookingAt()) {
					String text = matcher.group();

					add(new Token(tokenType, text));

					cleanExpression = cleanExpression.substring(text.length());

					found = true;

					break;
				}
			}

			if(!found) {
				System.err.println("Invalid character at '" + cleanExpression + "'");

				break;
			}
		}
	}
}