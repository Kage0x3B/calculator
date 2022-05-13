package de.syscy.calculator.tokenizer;

import java.util.regex.Pattern;

public enum TokenType {
	NAME("([A-Za-z_])+"),
	NUMBER("(\\d*[.,]?[\\d]+)"),
	OPERATOR("(&&|\\|\\||\\*\\*|[+\\-\\*/%])"),
	BLOCK_START("[(]"),
	BLOCK_END("[)]");

	private Pattern regex;

	private TokenType(String regex) {
		this.regex = Pattern.compile(regex);
	}

	public Pattern getRegex() {
		return regex;
	}
}