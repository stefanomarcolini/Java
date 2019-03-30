package design.pattern.creational.factory;

import java.util.Arrays;

/**
 * Factory design pattern implementation:
 * the Factory is responsible to choose which object
 * to create given the name of the object and a list
 * of parameters used to call the proper constructor
 * 
 * in this implementation the possible classes that
 * the Factory may create are public inner classes
 * */
public class Factory {

	public static Factory create(String className, Object...params) throws UnsupportedOperationException {
		switch (className) {
			case "A":
				return new A();
			case "B":
				Integer integer = null;
				String string = null;
				for (Object o : params) {
					if (o != null && o instanceof Integer) {
						integer = Integer.valueOf(String.valueOf(o));
					} else if (o != null && o instanceof String) {
						string = String.valueOf(o);
					}
				}
				return new B(integer, string);
			case "C":
				return new C(params);
			default:
				throw new UnsupportedOperationException("No matching classes found!");
		}
	}

	/**
	 * this inner class is one of the classes
	 * that can can be created by the Factory
	 * */
	public static class A extends Factory {
	
		public A() {
			System.out.println("class A created");
		}
		
	}

	/**
	 * this inner class is one of the classes
	 * that can can be created by the Factory
	 * */
	public static class B extends Factory {
	
		Integer integer;
		String string;
		public B(Integer integer, String string) {
			this.integer = integer;
			this.string = string;
			System.out.println("class B created >> Integer: " + (int)integer + 
							   "\n                   String:  " + string);
		}
		
	}

	/**
	 * this inner class is one of the classes
	 * that can can be created by the Factory
	 * */
	public static class C extends Factory {
	
		Double doublE;
		String string;
		int[] array;
		
		public C(Object...params) {
			for (Object o : params) {
				if (o != null && o instanceof Double) {
					doublE = Double.valueOf(String.valueOf(o));
				} else if (o != null && o instanceof String) {
					string = String.valueOf(o);
				} else if (o != null && o instanceof int[]) {
					array = (int[]) o;
				}
			}
			System.out.println("class C created >> Double: " + (double)doublE + 
							   "\n                   String: " + string + 
							   "\n                   int[]:  " + Arrays.toString(array));
		}
		
	}
	
}
