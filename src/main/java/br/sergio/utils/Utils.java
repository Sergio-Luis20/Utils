package br.sergio.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Utils {
	
	public static final String NUMBER_ARRAY_REGEX = "^\\s*[\\[\\{\\(]\\s*(((-|\\+)?\\s*\\d+(\\.\\d+)?\\s*,\\s*)*(-|\\+)?\\s*\\d+(\\.\\d+)?)?\\s*[\\]\\}\\)]\\s*$";
	public static final String CLEAN_ARRAY_REGEX = "[\\(\\)\\[\\]\\{\\}\\+\\s]";
    
    private Utils() {}

    public static byte parseByte(String str) {
        return parseByte(str, null);
    }

    public static byte parseByte(String str, Predicate<Byte> predicate) {
        return parseByte(str, predicate, null);
    }

    public static byte parseByte(String str, Predicate<Byte> predicate, String message) {
    	return parse(Byte.parseByte(str), predicate, message);
    }

    public static short parseShort(String str) {
        return parseShort(str, null);
    }

    public static short parseShort(String str, Predicate<Short> predicate) {
        return parseShort(str, predicate, null);
    }

    public static short parseShort(String str, Predicate<Short> predicate, String message) {
    	return parse(Short.parseShort(str), predicate, message);
    }

    public static int parseInt(String str) {
        return parseShort(str, null);
    }

    public static int parseInt(String str, Predicate<Integer> predicate) {
        return parseInt(str, predicate, null);
    }

    public static int parseInt(String str, Predicate<Integer> predicate, String message) {
    	return parse(Integer.parseInt(str), predicate, message);
    }

    public static long parseLong(String str) {
        return parseLong(str, null);
    }

    public static long parseLong(String str, Predicate<Long> predicate) {
        return parseLong(str, predicate, null);
    }

    public static long parseLong(String str, Predicate<Long> predicate, String message) {
    	return parse(Long.parseLong(str), predicate, message);
    }

    public static float parseFloat(String str) {
        return parseFloat(str, null);
    }

    public static float parseFloat(String str, Predicate<Float> predicate) {
        return parseFloat(str, predicate, null);
    }

    public static float parseFloat(String str, Predicate<Float> predicate, String message) {
    	return parse(Float.parseFloat(str), predicate, message);
    }

    public static double parseDouble(String str) {
        return parseDouble(str, null);
    }

    public static double parseDouble(String str, Predicate<Double> predicate) {
        return parseDouble(str, predicate, null);
    }

    public static double parseDouble(String str, Predicate<Double> predicate, String message) {
    	return parse(Double.parseDouble(str), predicate, message);
    }
    
    private static <T> T parse(T value, Predicate<T> predicate, String message) {
    	if(predicate != null && !predicate.test(value)) {
            ConditionException exception = new ConditionException(message);
            exception.setReprovedValue(value);
            throw exception;
        }
    	return value;
    }
    
    /*
     * Removed implementation using reflection to do these
     * convertion array methods because of performance, although
     * they got more verbose
     */

    public static byte[] toPrimitive(Byte[] array) {
    	byte[] newArray = new byte[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static short[] toPrimitive(Short[] array) {
    	short[] newArray = new short[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static int[] toPrimitive(Integer[] array) {
    	int[] newArray = new int[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static long[] toPrimitive(Long[] array) {
    	long[] newArray = new long[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static float[] toPrimitive(Float[] array) {
    	float[] newArray = new float[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static double[] toPrimitive(Double[] array) {
    	double[] newArray = new double[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static boolean[] toPrimitive(Boolean[] array) {
    	boolean[] newArray = new boolean[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static char[] toPrimitive(Character[] array) {
    	char[] newArray = new char[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static Byte[] toWrapper(byte[] array) {
    	Byte[] newArray = new Byte[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static Short[] toWrapper(short[] array) {
    	Short[] newArray = new Short[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static Integer[] toWrapper(int[] array) {
    	Integer[] newArray = new Integer[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Long[] toWrapper(long[] array) {
    	Long[] newArray = new Long[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Float[] toWrapper(float[] array) {
    	Float[] newArray = new Float[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Double[] toWrapper(double[] array) {
    	Double[] newArray = new Double[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Boolean[] toWrapper(boolean[] array) {
    	Boolean[] newArray = new Boolean[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Character[] toWrapper(char[] array) {
    	Character[] newArray = new Character[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static <T> List<T> toList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static <T> Set<T> toSet(T[] array) {
        return new LinkedHashSet<>(Arrays.asList(array));
    }

    public static <T> Queue<T> toQueue(T[] array) {
        return new LinkedList<>(Arrays.asList(array));
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Collection<T> collection) {
        return (T[]) collection.toArray();
    }
    
    public static Object parseNumberArray(String input, Class<?> componentType) {
        if(!Number.class.isAssignableFrom(componentType) && !isNumericPrimitive(componentType)) {
            throw new IllegalArgumentException("component type must be a primitive or wrapper number");
        }
        if(!input.matches(NUMBER_ARRAY_REGEX)) {
        	throw new ArrayFormatException("\"" + input + "\" does not match pattern Utils.NUMBER_ARRAY_REGEX: " + NUMBER_ARRAY_REGEX);
        }
        Method parse = getParseMethod(componentType);
        String[] stringArray = input.replaceAll(CLEAN_ARRAY_REGEX, "").split(",");
        if(stringArray.length == 1 && stringArray[0].isEmpty()) {
            return Array.newInstance(componentType, 0);
        }
        Object primitiveArray = Array.newInstance(componentType, stringArray.length);
        try {
            for(int i = 0; i < stringArray.length; i++) {
                Array.set(primitiveArray, i, parse.invoke(null, stringArray[i]));
            }
        } catch(NumberFormatException e) {
            throw e;
        } catch(Exception e) {
            /*
             * If the code gets here, it's user's fault
             * given our checks and the fact that the other
             * exceptions are only thrown if there are class
             * loader issues
             */
            throw new RuntimeException(e);
        }
        return primitiveArray;
    }
    
    public static <T> T parseEnum(Class<T> enumClass, String value) {
        if(!enumClass.isEnum()) {
            throw new IllegalArgumentException("class must be an enum");
        }
        try {
            Method valueOf = enumClass.getMethod("valueOf", String.class);
            return enumClass.cast(valueOf.invoke(null, value));
        } catch(IllegalArgumentException e) {
            throw e;
        } catch(Exception e) {
            // Should not get here since our verifications
            throw new Error(e);
        }
    }

    public static boolean isNumericPrimitive(Class<?> clazz) {
        return isPrimitive(clazz) && clazz != boolean.class && clazz != char.class;
    }

    public static boolean isPrimitive(Class<?> clazz) {
        return clazz == byte.class ||
               clazz == short.class ||
               clazz == int.class ||
               clazz == long.class ||
               clazz == float.class ||
               clazz == double.class ||
               clazz == boolean.class ||
               clazz == char.class;
    }

    public static boolean isNumericWrapper(Class<?> clazz) {
        return isWrapper(clazz) && clazz != Boolean.class && clazz != Character.class;
    }

    public static boolean isWrapper(Class<?> clazz) {
        return clazz == Byte.class ||
               clazz == Short.class ||
               clazz == Integer.class ||
               clazz == Long.class ||
               clazz == Float.class ||
               clazz == Double.class ||
               clazz == Boolean.class ||
               clazz == Character.class;
    }
    
    public static String toTextLines(List<String> lines) {
		StringBuilder sb = new StringBuilder();
		int size = lines.size();
		for(int i = 0; i < size; i++) {
			sb.append(lines.get(i) + (i == size - 1 ? "" : "\n"));
		}
		return sb.toString();
	}

    public static String capitalize(String input) {
        return transformString(input, String::toUpperCase);
    }

    public static String decapitalize(String input) {
        return transformString(input, String::toLowerCase);
    }

    private static String transformString(String input, Function<String, String> function) {
        if(input == null || input.isEmpty()) {
            return input;
        } else if(input.length() == 1) {
            return function.apply(input);
        } else {
            return function.apply(input.substring(0, 1)) + input.substring(1);
        }
    }

    private static Method getParseMethod(Class<?> clazz) {
        Method parse;
        try {
            String name = capitalize(clazz.getSimpleName());
            parse = Class.forName("java.lang." + (name.equals("Int") ? "Integer" : name))
                .getMethod("parse" + name, String.class);
        } catch(NoSuchMethodException e) {
            throw new IllegalArgumentException("number class must have a parse method");
        } catch(Throwable t) {
            throw new Error(t);
        }
        if(!Modifier.isStatic(parse.getModifiers())) {
            throw new IllegalArgumentException("the parse method must be static");
        }
        return parse;
    }

}
