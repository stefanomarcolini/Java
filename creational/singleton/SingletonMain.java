package design.pattern.creational.singleton;

public class SingletonMain {

	public static void main(String[] args) {
		
		//	Singleton eager
		System.out.println("--- Singleton eager ---");
		SingletonEager instanceE = SingletonEager.getInstance();
		System.out.println(instanceE);
		
		SingletonEager instanceE1 = SingletonEager.getInstance();
		System.out.println(instanceE1);
		

		//	Singleton lazy
		System.out.println("\n--- Singleton lazy ---");
		SingletonLazy instanceL = SingletonLazy.getInstance();
		System.out.println(instanceL);
		
		SingletonLazy instanceL1 = SingletonLazy.getInstance();
		System.out.println(instanceL1);
		

		//	Singleton inner proxy
		System.out.println("\n--- Singleton inner proxy ---");
		SingletonInnerProxy instanceIP = SingletonInnerProxy.getInstance();
		System.out.println(instanceIP);
		
		SingletonInnerProxy instanceIP1 = SingletonInnerProxy.getInstance();
		System.out.println(instanceIP1);
		

		//	Singleton multi thread
		System.out.println("\n--- Singleton multi thread ---");
		testMultiThread(10);
		
	}
	
	private static void testMultiThread(int n) {

		MyThread[] threads = new MyThread[n];
		for (int i = 0; i < n; i++) {
			threads[i] = new MyThread(i);
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	private static class MyThread extends Thread {
		
		SingletonMultiThread singletonMultiThread;
		private int t = 0; 
		
		public MyThread(int t) {
			this.t = t;
		}
		
		public void run() {
			singletonMultiThread = SingletonMultiThread.getInstance();
			System.out.println(t + ">> " + singletonMultiThread);
		}
		
	}

}
