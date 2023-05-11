package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Polynomial implements Serializable, Cloneable {
	
	protected final double[] coefficients;
	
	public Polynomial(double... coefficients) {
		if(coefficients == null) {
			throw new NullPointerException("null array");
		}
		if(coefficients.length == 0) {
			throw new IllegalArgumentException("empty array");
		}
		if(coefficients[0] == 0 && coefficients.length != 1) {
			throw new IllegalArgumentException("first coefficient is zero for length greater than 1");
		}
		this.coefficients = coefficients.clone();
	}
	
	public double[] getCoefficients() {
		return coefficients.clone();
	}
	
	public double zero() {
		return coefficients[coefficients.length - 1];
	}
	
	public double function(double x) {
		double result = zero();
		for(int i = 0; i < coefficients.length - 1; i++) {
			result += coefficients[i] * MathUtils.pow(x, coefficients.length - 1 - i);
		}
		return result;
	}
	
	public Complex function(Complex x) {
		Complex result = new Complex(zero());
		for(int i = 0; i < coefficients.length - 1; i++) {
			result = result.add(new Complex(coefficients[i]).multiply(Complex.pow(x, new Complex(coefficients.length - 1 - i))));
		}
		return result;
	}
	
	public Polynomial derivative() {
		if(coefficients.length == 1) {
			return new Polynomial(0);
		}
		double[] coefficients = new double[this.coefficients.length - 1];
		for(int i = 0; i < coefficients.length; i++) {
			coefficients[i] = (coefficients.length - i) * this.coefficients[i];
		}
		return new Polynomial(coefficients);
	}
	
	@Override
	public Polynomial clone() {
		return new Polynomial(coefficients);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof Polynomial pol) {
			return Arrays.equals(coefficients, pol.coefficients);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(coefficients);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(coefficients);
	}
	
}
