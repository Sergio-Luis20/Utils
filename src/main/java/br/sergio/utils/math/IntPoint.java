package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;

public class IntPoint implements Serializable, Cloneable {
    
    public static final IntPoint ORIGIN = new IntPoint(0, 0, 0);
    protected int x, y, z;

    public IntPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public IntPoint add(IntPoint p) {
		return new IntPoint(x + p.x, y + p.y, z + p.z);
	}
	
	public IntPoint subtract(IntPoint p) {
		return new IntPoint(x - p.x, y - p.y, z - p.z);
	}
	
	public IntPoint multiply(IntPoint p) {
		return new IntPoint(x * p.x, y * p.y, z * p.z);
	}
	
	public IntPoint multiply(int factor) {
		return new IntPoint(x * factor, y * factor, z * factor);
	}
	
	public Vector toVector() {
		return new Vector(x, y, z);
	}

    public IntVector toIntVector() {
        return new IntVector(x, y, z);
    }
	
	public double distance(IntPoint p) {
        int varX = x - p.x;
        int varY = y - p.y;
        int varZ = z - p.z;
		return MathUtils.sqrt(varX * varX + varY * varY + varZ * varZ);
	}

    public static int[] getXArray(IntPoint[] array) {
		int[] x = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			x[i] = array[i].x;
		}
		return x;
	}
	
	public static int[] getYArray(IntPoint[] array) {
		int[] y = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			y[i] = array[i].y;
		}
		return y;
	}
	
	public static int[] getZArray(IntPoint[] array) {
		int[] z = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			z[i] = array[i].z;
		}
		return z;
	}

    @Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

    @Override
	public IntPoint clone() {
		return new IntPoint(x, y, z);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof IntPoint p) {
			return x == p.x && y == p.y && z == p.z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

    public int getX() {
		return x;
	}

    public void setX(int x) {
		if(this == ORIGIN) {
			return;
		}
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		if(this == ORIGIN) {
			return;
		}
		this.y = y;
	}
	
	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		if(this == ORIGIN) {
			return;
		}
		this.z = z;
	}

}
