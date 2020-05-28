package ***.***.***.singleton;

/**
 * Singleton multi thread implementation:
 * class level variable instance is initialized when
 * getInstance() method is called. Only one thread
 * can create a new instance of the Singleton.
 * 
 * the instance variable is declared volatile in order
 * to manage compiler optimizations in multi threading
 * */
public class SingletonMultiThread {

	private volatile static SingletonMultiThread instance;
	
	private SingletonMultiThread() {}
	
	public static SingletonMultiThread getInstance() {
		if (instance == null) {
			synchronized (SingletonMultiThread.class) {
				if (instance == null) {
					instance = new SingletonMultiThread();
				}
			}
		}
		return instance;
	}
	
}
