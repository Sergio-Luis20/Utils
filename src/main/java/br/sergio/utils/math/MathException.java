package br.sergio.utils.math;

import java.util.function.Predicate;

public class MathException extends RuntimeException {
	
	public MathException() {
		super();
	}
	
	public MathException(String message) {
		super(message);
	}
	
	public MathException(Throwable cause) {
		super(cause);
	}
	
	public MathException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public static void lessThanZero(double element, String name) {
		handle(element, x -> x < 0, name + " < 0");
	}
	
	public static void lessThanOrEqualsToZero(double element, String name) {
		handle(element, x -> x <= 0, name + " <= 0");
	}

	public static void equalsToZero(double element, String name) {
		handle(element, x -> x == 0, name + " = 0");
	}
	
	private static void handle(double element, Predicate<Double> condition, String message) {
		if(condition.test(element)) {
			throw new MathException(message);
		}
	}
	
}
