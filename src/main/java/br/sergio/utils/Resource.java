package br.sergio.utils;

public class Resource<T> {
	
	private volatile T resource;
	
	public Resource() {
	}
	
	public Resource(T resource) {
		this.resource = resource;
	}
	
	public synchronized T get() throws InterruptedException {
		if(resource != null) {
			return resource;
		}
		wait();
		return resource;
	}
	
	public synchronized void set(T resource) {
		this.resource = resource;
		notifyAll();
	}
	
	public boolean exists() {
		return resource != null;
	}
	
}
