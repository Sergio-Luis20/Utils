package br.sergio.utils;

public class ArrayFormatException extends RuntimeException {
	
	public ArrayFormatException() {
		super();
	}
	
	public ArrayFormatException(String message) {
		super(message);
	}
	
	public ArrayFormatException(Throwable cause) {
		super(cause);
	}
	
	public ArrayFormatException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
