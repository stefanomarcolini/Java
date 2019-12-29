package ***.***.***.singleton;

/**
 * Singleton inner proxy implementation:
 * in line class lever variable initialization
 * calling the inner proxy responsible to create
 * the Singleton object
 * */
public class SingletonInnerProxy {

	private static final SingletonInnerProxy instance = InnerProxy.singleton;
	
	private SingletonInnerProxy() {}
	
	private static final class InnerProxy {
		private static final SingletonInnerProxy singleton = new SingletonInnerProxy();
	}
	
	public static SingletonInnerProxy getInstance() {
		return instance;
	}
}
