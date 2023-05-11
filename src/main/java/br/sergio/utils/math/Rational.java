package br.sergio.utils.math;

import java.util.Objects;

public final class Rational extends Number implements Cloneable, Comparable<Rational> {
	
	private final int num, denom;
	
	public Rational(int num, int denom) {
		MathException.equalsToZero(denom, "denom");
		if(denom < 0) {
			num = -num;
			denom = -denom;
		}
		int gcd = MathUtils.gcd(num, denom);
		this.num = num / gcd;
		this.denom = denom / gcd;
	}
	
	public Rational(int num) {
		this(num, 1);
	}
	
	public Rational add(Rational rational) {
		int newNum, newDenom;
		if(denom == rational.denom) {
			newNum = num + rational.num;
			newDenom = denom;
		} else {
			newNum = num * rational.denom + rational.num * denom;
			newDenom = denom * rational.denom;
		}
		return new Rational(newNum, newDenom);
	}
	
	public Rational subtract(Rational rational) {
		return add(new Rational(-rational.num, rational.denom));
	}
	
	public Rational multiply(Rational rational) {
		return new Rational(num * rational.num, denom * rational.denom);
	}
	
	public Rational divide(Rational rational) {
		return multiply(rational.getInverse());
	}
	
	public boolean isInteger() {
		return denom == 1;
	}
	
	public static int[] getNumerators(Rational[] array) {
		int[] numerators = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			numerators[i] = array[i].num;
		}
		return numerators;
	}
	
	public static int[] getDenominators(Rational[] array) {
		int[] denominators = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			denominators[i] = array[i].denom;
		}
		return denominators;
	}
	
	@Override
	public Rational clone() {
		return new Rational(num, denom);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof Rational rational) {
			return num == rational.num && denom == rational.denom;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(num, denom);
	}
	
	@Override
	public String toString() {
		return isInteger() ? String.valueOf(num) : num + "/" + denom;
	}
	
	@Override
	public int compareTo(Rational o) {
		int lcm = MathUtils.lcm(denom, o.denom);
		return lcm / denom * num - lcm / o.denom * o.num;
	}
	
	public Rational pow(int k) {
		int n, d;
		if(k < 0) {
			if(num == 0) {
				throw new MathException("0^negative");
			} else {
				n = denom;
				d = num;
				k = -k;
			}
		} else if(k > 0) {
			n = num;
			d = denom;
		} else {
			if(num == 0) {
				throw new MathException("0^0");
			} else {
				return new Rational(1);
			}
		}
		int newNum = (int) MathUtils.pow(n, k);
		int newDenom = (int) MathUtils.pow(d, k);
		return new Rational(newNum, newDenom);
	}
	
	public static Rational valueOf(String value) {
		if(value.matches("([-]\\d+|\\d+)[/]([-]\\d+|\\d+)")) {
			String[] values = value.split("/");
			return new Rational(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
		} else if(value.matches("([-]\\d+|\\d+)")) {
			return new Rational(Integer.parseInt(value));
		} else {
			throw new IllegalArgumentException("value must be in format \"a\" or \"a/b\"");
		}
	}
	
	public Rational getInverse() {
		return new Rational(denom, num);
	}
	
	public int getNum() {
		return num;
	}
	
	public int getDenom() {
		return denom;
	}

	@Override
	public int intValue() {
		return num / denom;
	}

	@Override
	public long longValue() {
		return num / denom;
	}

	@Override
	public float floatValue() {
		return (float) num / denom;
	}

	@Override
	public double doubleValue() {
		return (double) num / denom;
	}
	
}
