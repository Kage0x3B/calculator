package de.syscy.calculator;

import java.util.regex.Pattern;

public enum TokenType {
	NUMBER("(\\d*[.,]?[\\d]+)"),
	OPERATOR("[+\\-\\*/%]"),
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