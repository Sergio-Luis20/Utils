package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Arrays;

import br.sergio.utils.TriFunction;

public class Matrix implements Serializable, Cloneable {
	
	private final int lines;
	private final int columns;
	
	private final double[][] data;
	
	public Matrix(int lines, int columns) {
		if(lines < 1 || columns < 1) {
			throw new IllegalArgumentException("lines and columns must be at least 1");
		}
		this.lines = lines;
		this.columns = columns;
		
		data = new double[lines][columns];
	}
	
	public Matrix(int order) {
		this(order, order);
	}
	
	public Matrix(double[][] data) {
		if(data == null) {
			throw new NullPointerException("null array");
		}
		if(data.length == 0) {
			throw new IllegalArgumentException("empty lines");
		}
		if(data[0].length == 0) {
			throw new IllegalArgumentException("empty columns");
		}
		int lines = data.length;
		int columns = data[0].length;
		for(double[] datum : data) {
			if (datum.length != columns) {
				throw new MathException("columns must have the same length");
			}
		}
		this.lines = lines;
		this.columns = columns;
		this.data = cloneData(data);
	}
	
	public double[][] getCopyOfData() {
		return cloneData(data);
	}
	
	private double[][] cloneData(double[][] data) {
		double[][] copy = new double[data.length][];
		for(int i = 0; i < data.length; i++) {
			copy[i] = data[i].clone();
		}
		return copy;
	}
	
	@Override
	public Matrix clone() {
		return new Matrix(data);
	}
	
	public int getLineAmount() {
		return lines;
	}
	
	public int getColumnAmount() {
		return columns;
	}
	
	public double[] getLine(int line) {
		if(line < 0 || line >= lines) {
			throw new IndexOutOfBoundsException(line);
		}
		return data[line].clone();
	}

	public Matrix getLineAsMatrix(int line) {
		return lineMatrix(getLine(line));
	}
	
	public double[] getColumn(int column) {
		if(column < 0 || column >= columns) {
			throw new IndexOutOfBoundsException(column);
		}
		double[] columnArray = new double[lines];
		for(int i = 0; i < lines; i++) {
			columnArray[i] = data[i][column];
		}
		return columnArray;
	}
	
	public Matrix getColumnAsMatrix(int column) {
		return columnMatrix(getColumn(column));
	}
	
	public static Matrix lineMatrix(double... array) {
		if(array.length == 0) {
			throw new MathException("minimum line size: 1");
		}
		double[][] data = new double[1][array.length];
		data[0] = array.clone();
		return new Matrix(data);
	}
	
	public static Matrix lineMatrix(int size) {
		if(size <= 0) {
			throw new MathException("minimum line size: 1");
		}
		return lineMatrix(new double[size]);
	}
	
	public static Matrix columnMatrix(double... array) {
		if(array.length == 0) {
			throw new MathException("minimum column size: 1");
		}
		double[][] data = new double[array.length][1];
		for(int i = 0; i < data.length; i++) {
			data[i][0] = array[i];
		}
		return new Matrix(data);
	}
	
	public static Matrix columnMatrix(int size) {
		if(size <= 0) {
			throw new MathException("minimum column size: 1");
		}
		return columnMatrix(new double[size]);
	}
	
	public boolean isLine() {
		return lines == 1;
	}
	
	public boolean isColumn() {
		return columns == 1;
	}
	
	public boolean isDiagonal() {
		if(!isSquare()) {
			return false;
		}
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < columns; j++) {
				if(i != j && data[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public double getValue(int line, int column) {
		return data[line][column];
	}
	
	public void setValue(int line, int column, double value) {
		data[line][column] = value;
	}
	
	public boolean isNull() {
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < columns; j++) {
				if(data[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public double determinant() {
		if(!isSquare()) {
			throw new MathException("only square matrices have determinants");
		}
		return switch(order()) {
			case 1 -> data[0][0];
			case 2 -> data[0][0] * data[1][1] - data[0][1] * data[1][0];
			case 3 -> data[2][0] * data[0][1] * data[1][2] 
						+ data[0][0] * data[1][1] * data[2][2] 
						+ data[1][0] * data[2][1] * data[0][2]
						- data[2][2] * data[0][1] * data[1][0]
						- data[0][2] * data[1][1] * data[2][0]
						- data[1][2] * data[2][1] * data[0][0];
			default -> {
				double determinant = 0;
				for(int i = 0; i < columns; i++) {
					double value = data[0][i];
					if(value == 0) {
						continue;
					}
					determinant += value * cofactor(0, i);
				}
				yield determinant;
			}
		};
	}
	
	public double cofactor(int line, int column) {
		return ((line + column) % 2 == 0 ? 1 : -1) * complementaryMinor(line, column);
	}
	
	public double complementaryMinor(int line, int column) {
		if(!isSquare()) {
			throw new MathException("not a square matrix");
		}
		int order = order();
		if(line < 0 || line >= order || column < 0 || column >= order) {
			throw new IndexOutOfBoundsException("position (" + line + ", " + column + ") out of bounds (" + order + ", " + order + ")");
		}
		if(order == 1) {
			throw new MathException("matrix needs to have at least order 2");
		}
		int newOrder = order - 1;
		double[][] complementaryMinorData = new double[newOrder][newOrder];
		for(int i = 0; i < newOrder; i++) {
			for(int j = 0; j < newOrder; j++) {
				int vLine = i >= line ? i + 1 : i;
				int vColumn = j >= column ? j + 1 : j;
				complementaryMinorData[i][j] = data[vLine][vColumn];
			}
		}
		return new Matrix(complementaryMinorData).determinant();
	}
	
	public Matrix add(Matrix m) {
		return sum(m, 1);
	}
	
	public Matrix subtract(Matrix m) {
		return sum(m, -1);
	}
	
	private Matrix sum(Matrix m, int f) {
		if(lines != m.lines || columns != m.columns) {
			throw new MathException("matrices must have the same dimensions");
		}
		Matrix sum = new Matrix(lines, columns);
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < columns; j++) {
				sum.setValue(i, j, data[i][j] + f * m.data[i][j]);
			}
		}
		return sum;
	}
	
	public Matrix multiplyByScalar(double scalar) {
		return clone().map((i, j, v) -> v * scalar);
	}
	
	public Matrix pow(int exponent) {
		if(!isSquare() || exponent < 0) {
			return null;
		}
		Matrix result = identity(order());
		for(int i = 0; i < exponent; i++) {
			result = result.multiply(this);
		}
		return result;
	}
	
	public Matrix multiply(Matrix m) {
		if(columns != m.lines) {
			throw new MathException("cannot multiply matrices such that the number of columns "
					+ "in the first is different from the number of rows in the second");
		}
		Matrix result = new Matrix(lines, m.columns);
		for(int i = 0; i < result.lines; i++) {
			for(int j = 0; j < result.columns; j++) {
				double sum = 0;
				for(int k = 0; k < columns; k++) {
					sum += data[i][k] * m.data[k][j];
				}
				result.data[i][j] = sum;
			}
		}
		return result;
	}
	
	public int order() {
		return isSquare() ? lines : -1;
	}
	
	public boolean isSquare() {
		return lines == columns;
	}
	
	public boolean isSimetric() {
		return equals(transposed());
	}
	
	public boolean isAntiSimetric() {
		return transposed().equals(multiplyByScalar(-1));
	}
	
	public Matrix transposed() {
		Matrix transposed = new Matrix(columns, lines);
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < columns; j++) {
				transposed.data[j][i] = data[i][j];
			}
		}
		return transposed;
	}
	
	public Matrix cofactorMatrix() {
		int order = order();
		if(!isSquare() || order == 1) {
			return null;
		}
		double[][] cofactorMatrixData = new double[order][order];
		for(int i = 0; i < order; i++) {
			for(int j = 0; j < order; j++) {
				cofactorMatrixData[i][j] = cofactor(i, j);
			}
		}
		return new Matrix(cofactorMatrixData);
	}
	
	public Matrix adjugate() {
		return cofactorMatrix().transposed();
	}
	
	public Matrix inverse() {
		if(!isSquare() || isNull()) {
			return null;
		}
		if(order() == 1) {
			return new Matrix(new double[][] {{1 / data[0][0]}});
		}
		double determinant = determinant();
		if(determinant == 0) {
			return null;
		}
		return adjugate().multiplyByScalar(1 / determinant);
	}
	
	public Matrix map(TriFunction<Integer, Integer, Double, Double> function) {
		Matrix mapped = new Matrix(lines, columns);
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < columns; j++) {
				mapped.data[i][j] = function.apply(i, j, data[i][j]);
			}
		}
		return mapped;
	}
	
	public static Matrix identity(int order) {
		Matrix identity = new Matrix(order);
		for(int i = 0; i < order; i++) {
			identity.data[i][i] = 1;
		}
		return identity;
	}
	
	// Utility method for pretty printing in console
	public String toFormattedString() {
		String[][] formattedColumns = new String[columns][lines];
		for(int i = 0; i < columns; i++) {
			String preffix = i == 0 ? "|" : "";
			String suffix = i == columns - 1 ? "|" : "";
			double[] column = new double[lines];
			int maxSize = 0;
			for(int j = 0; j < lines; j++) {
				column[j] = data[j][i];
				int size = String.valueOf(column[j]).length();
				if(size > maxSize) {
					maxSize = size;
				}
			}
			String[] formattedColumn = new String[lines];
			for(int j = 0; j < lines; j++) {
				String columnString = String.valueOf(column[j]);
				StringBuilder element = new StringBuilder();
				for(int k = 0; k < maxSize - columnString.length(); k++) {
					element.append(" ");
				}
				element.append(columnString);
				formattedColumn[j] = preffix + element.toString() + suffix;
			}
			formattedColumns[i] = formattedColumn;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < lines; i++) {
			StringBuilder line = new StringBuilder();
			for(int j = 0; j < columns; j++) {
				line.append(formattedColumns[j][i] + (j == columns - 1 ? "" : " "));
			}
			sb.append(line.toString() + (i == lines - 1 ? "" : "\n"));
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i = 0; i < lines; i++) {
			StringBuilder line = new StringBuilder();
			line.append("{");
			for(int j = 0; j < columns; j++) {
				line.append(data[i][j] + (j == columns - 1 ? "" : ", "));
			}
			line.append("}");
			sb.append(line.toString() + (i == lines - 1 ? "" : ", "));
		}
		sb.append("}");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof Matrix m) {
			return Arrays.deepEquals(data, m.data);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(data);
	}
	
}
