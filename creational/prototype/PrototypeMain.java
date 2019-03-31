package design.pattern.creational.prototype;

import java.util.LinkedList;

public class PrototypeMain {

	public static void main(String[] args) {
		
		LinkedList<Object> list = new LinkedList<>();
		list.add(new Long[] {9L, 158L});
		list.add(123);
		list.add("Hi there");
		list.add(new Double[] {Math.E, Math.PI});
		
		Prototype prototype = new Prototype(10, new double[] {Math.E,  Math.PI}, "Hello World!",list);
		
		Prototype copy = null;
		
		try {
			copy = (Prototype) prototype.clone();
			System.out.println(copy.toString());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		/*	OUTPUT:
		 * 
		 *	inT:         10
		 *	doubleArray: [2.718281828459045, 3.141592653589793]
		 *	strinG:      Hello World!
		 *	list:        {[9, 158], 123, Hi there, [2.718281828459045, 3.141592653589793]}
		 **/
	}

}
