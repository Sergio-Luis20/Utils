package br.sergio.utils.math;

import java.util.Objects;

public final class Complex extends Number implements Cloneable, Comparable<Complex> {
	
	public static final Complex I = new Complex(0, 1);
	
	private final double real;
	private final double imaginary;
	private final double argument;
	private final double modulus;
	
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
		modulus = Math.hypot(real, imaginary);
		argument = isZero() ? Math.PI / 2 : Math.atan2(imaginary, real);
	}
	
	public Complex(double real) {
		this(real, 0);
	}
	
	public Complex(Complex copy) {
		this(copy.real, copy.imaginary);
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
		double denom = c.real * c.real + c.imaginary * c.imaginary;
		if(denom == 0) {
			throw new MathException("denominator = 0");
		}
		double r = (real * c.real + imaginary * c.imaginary) / denom;
		double i = (imaginary * c.real - real * c.imaginary) / denom;
		return new Complex(r, i);
	}

	public static Complex sqrt(Complex c) {
		return pow(c, new Complex(0.5));
	}

	public static Complex cbrt(Complex c) {
		return c.isReal() ? new Complex(Math.cbrt(c.real)) : pow(c, new Complex(1.0 / 3.0));
	}

	public static Complex pow(Complex base, Complex exp) {
		if(base.isZero()) {
			if(exp.isZero()) {
				throw new MathException("0^0");
			} else {
				return new Complex(0);
			}
		}
		if(exp.isZero()) {
			return new Complex(1);
		}
		double theta = exp.real * base.argument + exp.imaginary * Math.log(base.modulus);
		double factor = Math.pow(base.modulus, exp.real) * Math.pow(Math.E, -exp.imaginary * base.argument);
		double real = Math.cos(theta) * factor;
		double imaginary = Math.sin(theta) * factor;
		return new Complex(real, imaginary);
	}

	public static Complex log(Complex base, Complex logarithming) {
		return ln(logarithming).divide(ln(base));
	}

	public static Complex log10(Complex logarithming) {
		return log(new Complex(10), logarithming);
	}

	public static Complex ln(Complex logarithming) {
		return new Complex(Math.log(logarithming.modulus), logarithming.argument);
	}
	
	@Override
	public Complex clone() {
		return new Complex(real, imaginary);
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
		if(isZero()) {
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
			if(imaginary > 0) {
				if(real != 0) {
					if(imaginary == 1) {
						value = realPart + "+i";
					} else {
						value = realPart + "+" + imaginaryPart;
					}
				} else {
					if(imaginary == 1) {
						value = "i";
					} else {
						value = imaginaryPart;
					}
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

	public boolean isZero() {
		return real == 0 && imaginary == 0;
	}
	
	public static Complex valueOf(String value) {
		value = value.replace(" ", "");
		String realRegex = "(([+-]\\d+|\\d+)|([+-]\\d+[.]\\d+|\\d+[.]\\d+))";
		String imaginaryRegex = realRegex + "[i]";
		String complexRegex = realRegex + "([+-]\\d+|[+-]\\d+[.]\\d+)[i]";
		String iRegex = "[+-]?[i]";
		if(value.matches(realRegex)) {
			return new Complex(Double.parseDouble(value));
		} else if(value.matches(imaginaryRegex)) {
			return new Complex(0, Double.parseDouble(value.substring(0, value.length() - 1)));
		} else if(value.matches(complexRegex)) {
			int index = -1;
			if(value.contains("+")) {
				index = value.lastIndexOf('+');
				if(index == 0) {
					index = -1;
				}
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
