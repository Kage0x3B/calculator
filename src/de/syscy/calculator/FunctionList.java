package de.syscy.calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionList {
	private static final Map<String, Function<Double, Double>> functionMap = new HashMap<>();

	static {
		for(Method method : Math.class.getDeclaredMethods()) {
			Type[] parameters = method.getGenericParameterTypes();
			Type returnType = method.getReturnType();

			boolean validModifier = Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers());
			boolean validParameters = parameters.length == 1 && parameters[0].equals(double.class);
			boolean validReturnType = returnType.equals(double.class);

			if(validModifier && validParameters && validReturnType) {
				addFunction(method.getName(), (d) -> {
					try {
						return (double) method.invoke(Math.class, d);
					} catch(IllegalAccessException | InvocationTargetException ex) {
						ex.printStackTrace();

						return 0.0;
					}
				});
			}
		}
	}

	public static void addFunction(String name, Function<Double, Double> function) {
		functionMap.put(name.toLowerCase(), function);
	}

	public static Function<Double, Double> getFunction(String name) {
		name = name.toLowerCase();

		return functionMap.get(name);
	}
}