package ***.***.***.factory;

public class FactoryMain {

	public static void main(String[] args) {
		
		IFactory i = new Factory();
		
		try {
			
			A a = (A)i.create("a");
			a.iMethod();
			
			B b = (B)i.create("B");
			b.iMethod();
			
			C c = (C)i.create("c");
			c.iMethod();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*	OUTPUT:
		 * 
		 * Class A - iMethod()
		 * Class B - iMethod()
		 * Class C - iMethod()
		 * */

	}

}
