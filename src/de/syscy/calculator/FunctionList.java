package de.syscy.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionList {
	private static final Map<String, Function<Double, Double>> functionMap = new HashMap<>();

	static {
		addFunction("sin", Math::sin);
		addFunction("asin", Math::asin);
		addFunction("sinh", Math::sinh);
		addFunction("cos", Math::cos);
		addFunction("acos", Math::acos);
		addFunction("cosh", Math::cosh);
		addFunction("tan", Math::tan);
		addFunction("atan", Math::atan);
		addFunction("tanh", Math::tanh);
		addFunction("abs", Math::abs);
		addFunction("cbrt", Math::cbrt);
		addFunction("ceil", Math::ceil);
		addFunction("floor", Math::floor);
		addFunction("exp", Math::exp);
		addFunction("sqrt", Math::sqrt);
	}

	public static void addFunction(String name, Function<Double, Double> function) {
		functionMap.put(name.toLowerCase(), function);
	}

	public static Function<Double, Double> getFunction(String name) {
		name = name.toLowerCase();

		return functionMap.get(name);
	}
}