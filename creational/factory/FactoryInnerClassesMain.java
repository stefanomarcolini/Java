package ***.***.***.factory;

import ***.***.***.factory.FactoryInnerClasses.A;
import ***.***.***.factory.FactoryInnerClasses.B;
import ***.***.***.factory.FactoryInnerClasses.C;

public class FactoryInnerClassesMain {

	public static void main(String[] args) {
		
		A a = (A) FactoryInnerClasses.create("A");
		B b = (B) FactoryInnerClasses.create("B", 10, "Hello World!");
		C c = (C) FactoryInnerClasses.create("C", Math.E, "Hi there!", new int[] {0, 1, 2, 3, 4});
		
		
		/*	OUTPUT:
		 * 
		 * class A created
		 * class B created >> Integer: 10
		 *                    String:  Hello World!
		 * class C created >> Double: 2.718281828459045
		 *                    String: Hi there!
		 *                    int[]:  [0, 1, 2, 3, 4]
		 * */
		
	}

}
