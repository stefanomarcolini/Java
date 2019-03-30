package design.pattern.creational.singleton;

/**
 * Singleton lazy implementation:
 * class lever variable instance 
 * is initialized when getInstance
 * method is called
 * */
public class SingletonLazy {

	private static SingletonLazy instance;
	
	private SingletonLazy() {}
	
	public static SingletonLazy getInstance() {
		return instance = instance == null ? new SingletonLazy() : instance;
	}
	
}
