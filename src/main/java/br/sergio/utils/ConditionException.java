package br.sergio.utils;

public class ConditionException extends RuntimeException {
	
	private Object value;

    public ConditionException() {
        super();
    }

    public ConditionException(String message) {
        super(message);
    }

    public ConditionException(Throwable cause) {
        super(cause);
    }

    public ConditionException(String message, Throwable cause) {
        super(message, cause);
    }

    public void setReprovedValue(Object value) {
        this.value = value;
    }

    public Object getRreprovedValue() {
        return value;
    }

}
