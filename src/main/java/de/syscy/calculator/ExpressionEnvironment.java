package de.syscy.calculator;

import java.util.HashMap;
import java.util.Map;

public class ExpressionEnvironment {
	private Map<String, Double> variableMap = new HashMap<>();

	public ExpressionEnvironment() {
		addVariable("pi", Math.PI);
		addVariable("e", Math.E);
	}

	public void addVariable(String name, double value) {
		variableMap.put(name.toLowerCase(), value);
	}

	public double getVariableValue(String name) {
		name = name.toLowerCase();

		if(!variableMap.containsKey(name)) {
			return 0.0;
		}

		return variableMap.get(name);
	}
}