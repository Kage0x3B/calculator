package de.syscy.calculator.tokenizer;

import java.util.LinkedList;
import java.util.regex.Matcher;

public class Tokenizer extends LinkedList<Token> {
	private static final String CLEAN_REGEX = "[^\\w\\d.,+\\-*/%()&|_]";

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

		fixBlockTokens();
	}

	public void fixBlockTokens() {
		int blockCounter = 0;

		for(Token token : this) {
			if(token.getType() == TokenType.BLOCK_START) {
				blockCounter++;
			} else if(token.getType() == TokenType.BLOCK_END) {
				blockCounter--;
			}
		}

		for(int i = 0; i < blockCounter; i++) {
			add(new Token(TokenType.BLOCK_END, ")"));
		}
	}
}