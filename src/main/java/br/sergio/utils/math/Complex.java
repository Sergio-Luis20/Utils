package br.sergio.utils.math;

import java.util.Objects;

public final class Complex extends Number implements Comparable<Complex> {
	
	public static transient final Complex I = new Complex(0, 1);
	
	private final double real;
	private final double imaginary;
	private final double argument;
	private final double modulus;
	
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
		Vector vector = new Vector(real, imaginary);
		modulus = vector.getMagnitude();
		argument = new Vector(1, 0).angle(vector) * (imaginary >= 0 ? 1 : -1);
	}
	
	public Complex(double real) {
		this(real, 0);
	}
	
	public Complex add(Complex c) {
		return new Complex(real + c.real, imaginary + c.imaginary);
	}
	
	public Complex subtract(Complex c) {
		return new Complex(real - c.real, imaginary - c.imaginary);
	}
	
	public Complex multiply(Complex c) {
		double r = real * c.real - imaginary * c.imaginary;
		double i = real * c.imaginary + imaginary * c.real;
		return new Complex(r, i);
	}
	
	public Complex divide(Complex c) {
		double denom = MathUtils.pow(c.real, 2) + MathUtils.pow(c.imaginary, 2);
		if(denom == 0) {
			throw new MathException("O complexo divisor não pode ser 0.");
		}
		double r = (real * c.real + imaginary * c.imaginary) / denom;
		double i = (imaginary * c.real - real * c.imaginary) / denom;
		return new Complex(r, i);
	}
	
	public Complex sqrt() {
		return pow(new Complex(0.5));
	}
	
	public Complex cbrt() {
		return isReal() && real < 0 ? new Complex(MathUtils.cbrt(real)) : pow(new Complex(1.0 / 3.0));
	}
	
	public Complex pow(Complex exp) {
		if(modulus == 0) {
			return new Complex(0);
		}
		double theta = exp.real * argument + exp.imaginary * MathUtils.ln(modulus);
		double factor = MathUtils.pow(modulus, exp.real) * MathUtils.pow(Math.E, -exp.imaginary * argument);
		double real = MathUtils.cos(theta) * factor;
		double imaginary = MathUtils.sin(theta) * factor;
		return new Complex(real, imaginary);
	}
	
	public Complex log(Complex base) {
		Complex a = ln();
		Complex b = base.ln();
		return a.divide(b);
	}
	
	public Complex log10() {
		return log(new Complex(10));
	}
	
	public Complex ln() {
		return new Complex(MathUtils.ln(modulus), argument);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof Complex c) { 
			return real == c.real && imaginary == c.imaginary;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(real, imaginary);
	}
	
	@Override
	public String toString() {
		String value;
		if(real == 0 && imaginary == 0) {
			value = "0";
		} else {
			String realPart, imaginaryPart;
			if(MathUtils.isInteger(real)) {
				realPart = real == 0 ? "" : "" + (long) real;
			} else {
				realPart = real == 0 ? "" : "" + real;
			}
			if(MathUtils.isInteger(imaginary)) {
				imaginaryPart = imaginary == 0 ? "" : (long) imaginary + "i";
			} else {
				imaginaryPart = imaginary == 0 ? "" : imaginary + "i";
			}
			if(real != 0 && imaginary > 0) {
				if(imaginary == 1) {
					value = realPart + "+i";
				} else {
					value = realPart + "+" + imaginaryPart;
				}
			} else if(real == 0 && imaginary > 0) {
				if(imaginary == 1) {
					value = "i";
				} else {
					value = imaginaryPart;
				}
			} else {
				if(imaginary == -1) {
					value = realPart + "-i";
				} else {
					value = realPart + imaginaryPart;
				}
			}
		}
		return value;
	}
	
	@Override
	public int compareTo(Complex c) {
		return (int) (modulus - c.modulus);
	}
	
	public boolean isReal() {
		return imaginary == 0;
	}
	
	public static Complex valueOf(String value) {
		String realRegex = "(([-]\\d+|\\d+)|([-]\\d+[.]\\d+|\\d+[.]\\d+))";
		String imaginaryRegex = realRegex + "[i]";
		String complexRegex = realRegex + "([+-]\\d+|[+-]\\d+[.]\\d+)[i]";
		String iRegex = "([-][i]|[i])";
		if(value.matches(realRegex)) {
			return new Complex(Double.parseDouble(value));
		} else if(value.matches(imaginaryRegex)) {
			return new Complex(0, Double.parseDouble(value.substring(0, value.length() - 1)));
		} else if(value.matches(complexRegex)) {
			int index = -1;
			if(value.contains("+")) {
				index = value.indexOf('+');
			}
			if(index == -1) {
				index = value.lastIndexOf('-');
			}
			return new Complex(Double.parseDouble(value.substring(0, index)), 
					Double.parseDouble(value.substring(index, value.length() - 1)));
		} else if(value.matches(iRegex)) {
			return new Complex(0, value.contains("-") ? -1 : 1);
		} else {
			throw new IllegalArgumentException("invalid value: " + value);
		}
	}
	
	public double getReal() {
		return real;
	}
	
	public double getImaginary() {
		return imaginary;
	}
	
	public double getArgument() {
		return argument;
	}
	
	public double getModulus() {
		return modulus;
	}
	
	public Complex conjugate() {
		return new Complex(real, -imaginary);
	}
	
	@Override
	public int intValue() {
		return (int) real;
	}
	
	@Override
	public long longValue() {
		return (long) real;
	}
	
	@Override
	public float floatValue() {
		return (float) real;
	}
	
	@Override
	public double doubleValue() {
		return real;
	}
	
}
