package br.sergio.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
        if(array == null) {
            return null;
        }
    	byte[] newArray = new byte[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static short[] toPrimitive(Short[] array) {
        if(array == null) {
            return null;
        }
    	short[] newArray = new short[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static int[] toPrimitive(Integer[] array) {
        if(array == null) {
            return null;
        }
    	int[] newArray = new int[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static long[] toPrimitive(Long[] array) {
        if(array == null) {
            return null;
        }
    	long[] newArray = new long[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static float[] toPrimitive(Float[] array) {
        if(array == null) {
            return null;
        }
    	float[] newArray = new float[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static double[] toPrimitive(Double[] array) {
        if(array == null) {
            return null;
        }
    	double[] newArray = new double[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static boolean[] toPrimitive(Boolean[] array) {
        if(array == null) {
            return null;
        }
    	boolean[] newArray = new boolean[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static char[] toPrimitive(Character[] array) {
        if(array == null) {
            return null;
        }
    	char[] newArray = new char[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static Byte[] toWrapper(byte[] array) {
        if(array == null) {
            return null;
        }
    	Byte[] newArray = new Byte[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static Short[] toWrapper(short[] array) {
        if(array == null) {
            return null;
        }
    	Short[] newArray = new Short[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static Integer[] toWrapper(int[] array) {
        if(array == null) {
            return null;
        }
    	Integer[] newArray = new Integer[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Long[] toWrapper(long[] array) {
        if(array == null) {
            return null;
        }
    	Long[] newArray = new Long[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Float[] toWrapper(float[] array) {
        if(array == null) {
            return null;
        }
    	Float[] newArray = new Float[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Double[] toWrapper(double[] array) {
        if(array == null) {
            return null;
        }
    	Double[] newArray = new Double[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Boolean[] toWrapper(boolean[] array) {
        if(array == null) {
            return null;
        }
    	Boolean[] newArray = new Boolean[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }
    
    public static Character[] toWrapper(char[] array) {
        if(array == null) {
            return null;
        }
    	Character[] newArray = new Character[array.length];
    	for(int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	return newArray;
    }

    public static <T> List<T> toList(T[] array) {
        if(array == null) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(array));
    }

    public static <T> Set<T> toSet(T[] array) {
        if(array == null) {
            return null;
        }
        return new LinkedHashSet<>(Arrays.asList(array));
    }

    public static <T> Queue<T> toQueue(T[] array) {
        if(array == null) {
            return null;
        }
        return new LinkedList<>(Arrays.asList(array));
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Collection<T> collection, Class<T> clazz) {
        if(collection == null) {
            return null;
        }
        if(clazz == null) {
            throw new NullPointerException("clazz = null");
        }
        return collection.toArray((T[]) Array.newInstance(clazz, collection.size()));
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
    
    public static boolean isNumericIntegerPrimitive(Class<?> clazz) {
    	return isNumericPrimitive(clazz) && clazz != float.class && clazz != double.class;
    }
    
    public static boolean isNumericRealPrimitive(Class<?> clazz) {
    	return isNumericPrimitive(clazz) && !isNumericIntegerPrimitive(clazz);
    }

    public static boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive();
    }
    
    public static Class<?> getPrimitiveClass(Class<?> wrapper) {
    	if(wrapper == Byte.class) {
    		return byte.class;
    	} else if(wrapper == Short.class) {
    		return short.class;
    	} else if(wrapper == Integer.class) {
    		return int.class;
    	} else if(wrapper == Long.class) {
    		return long.class;
    	} else if(wrapper == Float.class) {
    		return float.class;
    	} else if(wrapper == Double.class) {
    		return double.class;
    	} else if(wrapper == Boolean.class) {
    		return boolean.class;
    	} else if(wrapper == Character.class) {
    		return char.class;
    	} else {
    		return null;
    	}
    }
    
    public static Class<?> getPrimitiveCheckingClass(Class<?> wrapper) {
    	if(isPrimitive(wrapper)) {
    		return wrapper;
    	}
    	return getPrimitiveClass(wrapper);
    }

    public static boolean isNumericWrapper(Class<?> clazz) {
        return isWrapper(clazz) && clazz != Boolean.class && clazz != Character.class;
    }
    
    public static boolean isNumericIntegerWrapper(Class<?> clazz) {
    	return isNumericWrapper(clazz) && clazz != Float.class && clazz != Double.class;
    }
    
    public static boolean isNumericRealWrapper(Class<?> clazz) {
    	return isNumericWrapper(clazz) && !isNumericIntegerWrapper(clazz);
    }

    public static boolean isWrapper(Class<?> clazz) {
        return getPrimitiveClass(clazz) != null;
    }
    
    public static Class<?> getWrapperClass(Class<?> primitive) {
    	if(primitive == byte.class) {
    		return Byte.class;
    	} else if(primitive == short.class) {
    		return Short.class;
    	} else if(primitive == int.class) {
    		return Integer.class;
    	} else if(primitive == long.class) {
    		return Long.class;
    	} else if(primitive == float.class) {
    		return Float.class;
    	} else if(primitive == double.class) {
    		return Double.class;
    	} else if(primitive == boolean.class) {
    		return Boolean.class;
    	} else if(primitive == char.class) {
    		return Character.class;
    	} else {
    		return null;
    	}
    }
    
    public static Class<?> getWrapperCheckingClass(Class<?> primitive) {
    	if(isWrapper(primitive)) {
    		return primitive;
    	}
    	return getWrapperClass(primitive);
    }
    
    public static Object mapWrapperConverting(Class<?> clazz, String value) {
    	return mapWrapper(getWrapperCheckingClass(clazz), value);
    }
    
    public static Object mapWrapper(Class<?> clazz, String value) {
    	if(!isWrapper(clazz)) {
			throw new IllegalArgumentException("Not a wrapper class: " + clazz.getName());
		}
		if(clazz == Character.class) {
			if(value.length() != 1) {
				throw new IllegalArgumentException("Class: " + clazz.getName() + ", String length: " + value.length() + ", allowed: 1");
			}
			return Character.valueOf(value.toCharArray()[0]);
		} else {
			try {
				return clazz.getMethod("valueOf", String.class).invoke(null, value);
			} catch(Exception e) {					
				// Should not get here since our verifications
				throw new Error(e);
			}
		}
    }

    public static boolean isNumeric(Class<?> clazz) {
        return isNumericPrimitive(clazz) || isNumericWrapper(clazz);
    }

    public static boolean isNumericInteger(Class<?> clazz) {
        return isNumericIntegerPrimitive(clazz) || isNumericIntegerWrapper(clazz);
    }

    public static boolean isNumericReal(Class<?> clazz) {
        return isNumericRealPrimitive(clazz) || isNumericRealWrapper(clazz);
    }
    
    public static List<Field> getNonStaticFields(Class<?> clazz) {
    	return getNonStaticFields(clazz, false);
    }
    
    public static List<Field> getNonStaticFields(Class<?> clazz, boolean accessiblesOnly) {
    	return Stream.of(accessiblesOnly ? clazz.getFields() : clazz.getDeclaredFields())
    			.filter(field -> !Modifier.isStatic(field.getModifiers())).toList();
    }
    
    public static List<Field> getAllFields(Class<?> clazz) {
    	return getAllFields(clazz, false);
    }
    
    public static List<Field> getAllFields(Class<?> clazz, boolean accessiblesOnly) {
    	// Collect fields from subclasses to superclasses
		List<List<Field>> fields = new ArrayList<>();
		while(clazz != Object.class) {
			fields.add(getNonStaticFields(clazz, accessiblesOnly));
			clazz = clazz.getSuperclass();
		}
		/*
		 * Checks for duplicates in superclasses, keeping
		 * fields from subclasses and deleting those from
		 * superclasses 
		 */
		int size = fields.size();
		for(int i = 0; i < size - 1; i++) {
			List<Field> list = fields.get(i);
			for(Field field : list) {
				for(int j = i + 1; j < size; j++) {
					List<Field> superList = fields.get(j);
					for(Field superField : superList) {
						if(equalsField(field, superField)) {
							superList.remove(superField);
						}
					}
				}
			}
		}
		/*
		 * Sort fields from superclasses to subclasses
		 * and return a list consisting of a flatMap of them
		 */
		Collections.reverse(fields);
		return fields.stream().flatMap(List::stream).toList();
	}
    
    private static boolean equalsField(Field a, Field b) {
    	return a.getName().equals(b.getName());
    }
    
    public static List<Method> getNonStaticMethods(Class<?> clazz) {
    	return getNonStaticMethods(clazz, false);
    }
    
    public static List<Method> getNonStaticMethods(Class<?> clazz, boolean accessiblesOnly) {
    	return Stream.of(accessiblesOnly ? clazz.getMethods() : clazz.getDeclaredMethods())
				.filter(method -> !Modifier.isStatic(method.getModifiers())).toList();
    }
    
    public static List<Method> getAllMethods(Class<?> clazz) {
    	return getAllMethods(clazz, false);
    }
    
    public static List<Method> getAllMethods(Class<?> clazz, boolean accessiblesOnly) {
    	// Collect methods from subclasses to superclasses
    	List<List<Method>> methods = new ArrayList<>();
		while(clazz != Object.class) {
			methods.add(Stream.of(accessiblesOnly ? clazz.getMethods() : clazz.getDeclaredMethods())
					.filter(method -> !Modifier.isStatic(method.getModifiers())).toList());
			clazz = clazz.getSuperclass();
		}
		/*
		 * Checks for duplicates in superclasses, keeping
		 * methods from subclasses and deleting those from
		 * superclasses 
		 */
		int size = methods.size();
		for(int i = 0; i < size - 1; i++) {
			List<Method> list = methods.get(i);
			for(Method method : list) {
				for(int j = i + 1; j < size; j++) {
					List<Method> superList = methods.get(j);
					for(Method superMethod : superList) {
						if(equalsMethod(method, superMethod)) {
							superList.remove(superMethod);
						}
					}
				}
			}
		}
		/*
		 * Sort methods from superclasses to subclasses
		 * and return a list consisting of a flatMap of them
		 */
		Collections.reverse(methods);
		return methods.stream().flatMap(List::stream).toList();
    }
    
    private static boolean equalsMethod(Method a, Method b) {
    	return a.getName().equals(b.getName()) && Arrays.equals(a.getParameterTypes(), b.getParameterTypes());
    }
    
    public static Object defaultInitValue(Class<?> clazz) {
    	if(clazz == byte.class || clazz == Byte.class) return (byte) 0;
    	if(clazz == short.class || clazz == Short.class) return (short) 0;
    	if(clazz == int.class || clazz == Integer.class) return 0;
    	if(clazz == long.class || clazz == Long.class) return 0L;
    	if(clazz == float.class || clazz == Float.class) return 0.0f;
    	if(clazz == double.class || clazz == Double.class) return 0.0;
    	if(clazz == boolean.class || clazz == Boolean.class) return false;
    	if(clazz == char.class || clazz == Character.class) return (char) 0;
    	return null;
    }

    public static boolean areEquivalent(Class<?> c1, Class<?> c2) {
        if(c1 == c2) {
            return true;
        }
        if(c1.isPrimitive() && getPrimitiveCheckingClass(c2) == c1) {
            return true;
        }
        if(isWrapper(c1) && getWrapperCheckingClass(c2) == c1) {
            return true;
        }
        return false;
    }

    public static boolean isBasic(Class<?> c) {
        return c.isPrimitive() || isWrapper(c) || c == String.class;
    }

    public static String methodToString(Method method) {
        String name = method.getName();
		Class<?>[] params = method.getParameterTypes();
		if(params.length == 0) {
			return name + "()";
		}
		String paramsString = Arrays.toString(Stream.of(params).map(Class::getName).toArray());
		paramsString = paramsString.substring(1, paramsString.length() - 1);
		return name + "(" + paramsString + ")";
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
