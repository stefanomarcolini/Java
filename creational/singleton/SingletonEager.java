package design.pattern.creational.singleton;

/**
 * Singleton eager implementation:
 * class level variable instance
 * is initialized in line
 * */
public class SingletonEager {

	private static final SingletonEager instance = new SingletonEager();
	
	private SingletonEager() {}
	
	public static SingletonEager getInstance() {
		return instance;
	}
	
}