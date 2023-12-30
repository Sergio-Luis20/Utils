package br.sergio.utils;

public final class ArrayConverter {
    
    private ArrayConverter() {}

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

}
