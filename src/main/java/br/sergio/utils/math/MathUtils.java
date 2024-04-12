package br.sergio.utils.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class MathUtils {
	
	public static final double PI = Math.PI;
	public static final double E = Math.E;

	// inverse square root interations
	private static final int INV_SQRT_IT = 5;

	private MathUtils() {}
	
	public static double sqrt(double x) {
		if(x < 0) {
			throw new MathException("x < 0");
		}
		return Math.sqrt(x);
	}

	public static double inverseSqrt(double x) {
		if(x <= 0) {
			throw new MathException("x <= 0");
		}
		double xhalf = 0.5 * x;
		long i = Double.doubleToLongBits(x);
		i = 0x5fe6ec85e7de30daL - (i >> 1);
		x = Double.longBitsToDouble(i);
		for(int it = 0; it < INV_SQRT_IT; it++) {
			x *= (1.5 - xhalf * x * x);
		}
		return x;
	}
	
	public static double cbrt(double real) {
		return Math.cbrt(real);
	}
	
	public static double root(double rooting, double index) {
		if(Double.isNaN(rooting) || Double.isNaN(index)) {
			return Double.NaN;
		}
		MathException.equalsToZero(index, "index");
		if(rooting == 0 && index < 0) {
			throw new MathException("rooting = 0 and index < 0");
		}
		if(rooting < 0 && (!isInteger(index) || even((long) index))) {
			throw new MathException("rooting < 0 and non integer index or even index, root could be unsafe, use Complex class for that case");
		}
		return (rooting < 0 ? -1 : 1) * pow(abs(rooting), 1 / index);
	}
	
	public static double pow(double base, double exponent) {
		if(base == 0 && exponent <= 0) {
			throw new MathException("base = 0 and exponent <= 0");
		}
		if(base < 0 && !isInteger(exponent)) {
			throw new MathException("base < 0 and non integer exponent");
		}
		return Math.pow(base, exponent);
	}
	
	public static double pow(double base, int exponent) {
		if(base == 1) {
			return 1;
		}
		if(base == 0) {
			if(exponent <= 0) {
				throw new IllegalArgumentException("for base 0, exponent must be greater than 0");
			}
			return 0;
		}
		int exp = abs(exponent);
		double result = 1;
		for(int i = 0; i < exp; i++) {
			result *= base;
		}
		// Check for when 0 < base < 1 and absolute exponent is too big
		if(result == 0) {
			if(base < 0) {
				return even(exponent) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
			} else {
				return Double.POSITIVE_INFINITY;
			}
		}
		return exponent < 0 ? 1 / result : result;
	}
	
	public static double pow(double base, Rational exponent) {
		if(base == 0 && exponent.getNum() <= 0) {
			throw new MathException("base = 0 and exponent <= 0");
		}
		double n = pow(base, exponent.getNum());
		int denom = exponent.getDenom();
		if(n < 0) {
			if(odd(denom)) {
				return -Math.pow(-n, 1.0 / denom);
			} else {
				throw new MathException("cannot get square root of negative number, use Complex class for that purpose");
			}
		} else {
			return Math.pow(n, 1.0 / denom);
		}
	}
	
	public static double log(double base, double logarithming) {
		if(base <= 0 || base == 1 || logarithming <= 0) {
			throw new MathException("base <= 0, base = 1 or logarithming <= 0");
		}
		return ln(logarithming) / ln(base);
	}
	
	public static double log10(double logarithming) {
		MathException.lessThanOrEqualsToZero(logarithming, "logarithming");
		return Math.log10(logarithming);
	}
	
	public static double ln(double logarithming) {
		MathException.lessThanOrEqualsToZero(logarithming, "logarithming");
		return Math.log(logarithming);
	}
	
	public static long comb(int n, int k) {
		return factorial(n).divide((factorial(k).multiply(factorial(n - k)))).longValue();
	}
	
	public static BigInteger factorial(int natural) {
		MathException.lessThanZero(natural, "number");
		BigInteger result = BigInteger.ONE;
		for(int i = 0; i < natural; i++) {
			result = result.multiply(new BigInteger(String.valueOf(i + 1)));
		}
		return result;
	}
	
	public static long[] pascalTriangle(int line) {
		MathException.lessThanZero(line, "line");
		long[] coef = new long[line + 1];
		for(int i = 0; i < coef.length; i++) {
			coef[i] = comb(line, i);
		}
		return coef;
	}
	
	public static double abs(double value) {
		return value >= 0 ? value : -value;
	}
	
	public static int abs(int value) {
		return value >= 0 ? value : -value;
	}
	
	public static long abs(long value) {
		return value >= 0 ? value : -value;
	}
	
	public static double toRadians(double x) {
		return Math.toRadians(x);
	}
	
	public static double toDegrees(double x) {
		return Math.toDegrees(x);
	}
	
	// Trigonometric functions
	
	public static double sin(double x) {
		return Math.sin(x);
	}
	
	public static double cos(double x) {
		return Math.cos(x);
	}
	
	public static double tan(double x) {
		return Math.tan(x);
	}
	
	public static double cot(double x) {
		return 1 / tan(x);
	}
	
	public static double sec(double x) {
		return 1 / cos(x);
	}
	
	public static double csc(double x) {
		return 1 / sin(x);
	}
	
	// Inverse trigonometric functions
	
	public static double asin(double x) {
		return Math.asin(x);
	}
	
	public static double acos(double x) {
		return Math.acos(x);
	}
	
	public static double atan(double x) {
		return Math.atan(x);
	}
	
	public static double acot(double x) {
		return PI / 2 - atan(x);
	}
	
	public static double asec(double x) {
		return acos(1 / x);
	}
	
	public static double acsc(double x) {
		return asin(1 / x);
	}
	
	// Hiperbolic functions
	
	public static double sinh(double x) {
		return Math.sinh(x);
	}
	
	public static double cosh(double x) {
		return Math.cosh(x);
	}
	
	public static double tanh(double x) {
		return Math.tanh(x);
	}
	
	public static double coth(double x) {
		return 1 / tanh(x);
	}
	
	public static double sech(double x) {
		return 1 / cosh(x);
	}
	
	public static double csch(double x) {
		return 1 / sinh(x);
	}
	
	// Inverse hiperbolic functions
	
	public static double asinh(double x) {
		return ln(x + sqrt(pow(x, 2) + 1));
	}
	
	public static double acosh(double x) {
		return ln(x + sqrt(pow(x, 2) - 1));
	}
	
	public static double atanh(double x) {
		return ln((1 + x) / (1 - x)) / 2;
	}
	
	public static double acoth(double x) {
		return ln((x + 1) / (x - 1)) / 2;
	}
	
	public static double asech(double x) {
		return ln((1 + sqrt(1 - pow(x, 2))) / x);
	}
	
	public static double acsch(double x) {
		return ln((1 + sqrt(pow(x, 2) + 1)) / x);
	}
	
	public static double floor(double value) {
		return Math.floor(value);
	}
	
	public static double ceil(double value) {
		return Math.ceil(value);
	}
	
	public static long round(double value) {
		return Math.round(value);
	}
	
	public static double round(double x, int precision) {
	    return BigDecimal.valueOf(x).setScale(precision, RoundingMode.HALF_UP).doubleValue();
	}

	public static String formatInteger(double value) {
		if(isInteger(value)) {
			String plainString = BigDecimal.valueOf(value).toPlainString();
			int index = plainString.lastIndexOf('.');
			if(index > 0) {
				return plainString.substring(0, index);
			} else {
				return plainString;
			}
		} else {
			return String.valueOf(value);
		}
	}
	
	public static long decimalPart(double value) {
		String str = Double.toString(value);
		int expIndex = str.length();
		if(str.contains("E")) {
			expIndex = str.indexOf("E");
			if(Integer.parseInt(str.substring(expIndex) + 1) >= 0) {
				return 0;
			}
		}
		return Long.valueOf(str.substring(str.indexOf(".") + 1, expIndex));
	}
	
	public static double hypotenuse(double catheter1, double catheter2) {
		return sqrt(pow(catheter1, 2) + pow(catheter2, 2));
	}
	
	public static double catheter(double hypotenuse, double catheter) {
		if(catheter > hypotenuse) {
			throw new MathException("catheter > hypotenuse");
		}
		return sqrt(pow(hypotenuse, 2) - pow(catheter, 2));
	}
	
	public static double arithmeticAverage(double... values) {
		double x = 0;
		for(double value : values) {
			x += value;
		}
		return x / values.length;
	}
	
	public static double weightedAverage(double[] weights, double[] values) {
		double denom = 0;
		for(int i = 0; i < weights.length; i++) {
			if(weights[i] <= 0 || values[i] < 0) {
				throw new MathException("none of the weights or values can be less or equals to 0");
			}
			denom += weights[i];
		}
		double num = 0;
		for(int i = 0; i < weights.length; i++) {
			num += weights[i] * values[i];
		}
		return num / denom;
	}
	
	public static double geometricAverage(double... values) {
		double x = 1;
		for(double value : values) {
			x *= value;
			if(x < 0) {
				throw new MathException("values of geometric average cannot be less than 0");
			}
		}
		return root(x, values.length);
	}
	
	public static double harmonicAverage(double... values) {
		double num = values.length;
		double denom = 0;
		for (double doubleValue : values) {
			if (doubleValue <= 0) {
				throw new MathException("none of thee values can be 0");
			}
			denom += 1 / doubleValue;
		}
		return num / denom;
	}

	public static double variance(double... values) {
		double average = arithmeticAverage(values);
		int len = values.length;
		double sum = 0;
		for(double val : values) {
			sum += pow(val - average, 2);
		}
		return sum / len;
	}

	public static double standardDeviation(double... values) {
		return sqrt(variance(values));
	}
	
	public static boolean even(long value) {
		return value % 2 == 0;
	}
	
	public static boolean odd(long value) {
		return value % 2 != 0;
	}
	
	public static boolean multiple(double a, double b) {
		return a % b == 0;
	}
	
	public static boolean divider(double a, double b) {
		return b % a == 0;
	}
	
	public static int lcm(int... values) {
        if(values.length == 0) {
            return 1;
        }
        Map<Integer, Integer> primeFactors = new HashMap<>();
        for(int number : values) {
            Map<Integer, Integer> factors = primeFactors(number);
            for(Entry<Integer, Integer> entry : factors.entrySet()) {
                int prime = entry.getKey();
                int count = entry.getValue();
                if(!primeFactors.containsKey(prime) || primeFactors.get(prime) < count) {
                    primeFactors.put(prime, count);
                }
            }
        }
        int lcm = 1;
        for(Entry<Integer, Integer> entry : primeFactors.entrySet()) {
            int factor = entry.getKey();
            int count = entry.getValue();
            lcm *= pow(factor, count);
        }
        return lcm;
    }
	
	public static int gcd(int... values) {
    	List<Integer> list = new ArrayList<>();
    	for(int i : values) {
    		if(i != 0) {
    			list.add(abs(i));
    		}
    	}
    	int size = list.size();
    	if(size == 0) {
    		return 1;
    	}
    	int result = list.get(0);
        for(int i = 1; i < size; i++) {
            int a = result;
            int b = list.get(i);
            while(b != 0) {
            	result = b;
            	b = a % b;
            	a = result;
            }
        }
        return result;
    }
	
	public static int[] dividers(int n) {
		if(n == 0) {
			throw new MathException("any number other than 0 can divide 0.");
		}
		n = abs(n);
		int k = n / 2;
		List<Integer> dividers = new ArrayList<>();
		for(int i = 1; i <= k; i++) {
			if(n % i == 0) {
				dividers.add(i);
			}
			if(i * i == n) {
				dividers.add(n);
				break;
			}
		}
		int[] array = new int[dividers.size()];
		for(int i = 0; i < dividers.size(); i++) {
			array[i] = dividers.get(i);
		}
		return array;
	}
	
	public static boolean prime(int value) {
		if(value == 0 || value == 1) {
			return false;
		}
		int[] array = primes(value = abs(value));
		return array[array.length - 1] == value; 
	}
	
	public static int[] primes(int n) {
		if(n <= 1) {
			return new int[0];
		}
	    boolean[] primes = new boolean[n + 1];
	    Arrays.fill(primes, true);
	    primes[0] = false;
	    primes[1] = false;
	    for(int i = 2; i*i <= n; i++) {
	        if(primes[i]) {
	            for(int j = i*i; j <= n; j += i) {
	                primes[j] = false;
	            }
	        }
	    }
	    int count = 0;
	    for(boolean prime : primes) {
	        if(prime) {
	            count++;
	        }
	    }
	    int[] result = new int[count];
	    int index = 0;
	    for(int i = 0; i < primes.length; i++) {
	        if(primes[i]) {
	            result[index++] = i;
	        }
	    }
	    return result;
	}
	
	public static Map<Integer, Integer> primeFactors(int n) {
		n = abs(n);
		// Key: prime; value: number of occurrences
		Map<Integer, Integer> factors = new HashMap<>();
	    int d = 2;
	    while(n > 1) {
	        int count = 0;
	        while(n % d == 0) {
	            count++;
	            n /= d;
	        }
	        if(count > 0) {
	            factors.put(d, count);
	        }
	        d++;
	        if(d * d > n) {
	            if(n > 1) {
	                factors.put(n, 1);
	            }
	            break;
	        }
	    }
	    return factors;
	}
	
	public static boolean isNatural(double number) {
		return isInteger(number) && number >= 0;
	}
	
	public static boolean isInteger(double number) {
		return BigDecimal.valueOf(number).stripTrailingZeros().scale() <= 0;
	}
	
	public static double largest(double... values) {
		if(values.length == 0) {
			return 0;
		}
		double biggest = values[0];
		for(int i = 0; i < values.length - 1; i++) {
			double value = values[i + 1];
			if(value > biggest) {
				biggest = value;
			}
		}
		return biggest;
	}
	
	public static int largest(int... values) {
		if(values.length == 0) {
			return 0;
		}
		int biggest = values[0];
		for(int i = 0; i < values.length - 1; i++) {
			int value = values[i + 1];
			if(value > biggest) {
				biggest = value;
			}
		}
		return biggest;
	}
	
	public static double smallest(double... values) {
		if(values.length == 0) {
			return 0;
		}
		double smallest = values[0];
		for(int i = 0; i < values.length - 1; i++) {
			double value = values[i + 1];
			if(value < smallest) {
				smallest = value;
			}
		}
		return smallest;
	}
	
	public static int smallest(int... values) {
		if(values.length == 0) {
			return 0;
		}
		int smallest = values[0];
		for(int i = 0; i < values.length - 1; i++) {
			int value = values[i + 1];
			if(value < smallest) {
				smallest = value;
			}
		}
		return smallest;
	}
	
}
