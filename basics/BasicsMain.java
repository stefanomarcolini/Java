package ***.***.basics;

public class BasicsMain {

	public static void main(String[] args) {
		
		System.out.println("//Child object:");
		Child c = new Child();
		c.childMethod();
		
		System.out.println("\n//Parent object:");
		Parent p = new Parent();
		p.parentMethod();
		c.parentMethod();

		System.out.println("\n//Child2 object:");
		Child2 c2 = new Child2();
		c2.child2Method();
		c2.parentMethod();

		System.out.println("\n//Child3 object:");
		Child3 c3 = new Child3();
		c3.abstractParentMethod();
		
		System.out.println("\n//AParent reference:");
		AParent ap = new Child3();
		ap.abstractParentMethod();
		ap.implementedMethod();

		System.out.println("\n//ImplementerClass object:");
		ImplementerClass ic = new ImplementerClass();
		ic.iMethod();

		System.out.println("\n//IMethods reference:");
		IMethods im = new ImplementerClass();
		im.iMethod();
		im.defaultIMethod();
		
		/*	OUTPUT:
		 
		   	//Child object:
			Child class - childMethod()
			
			//Parent object:
			Parent class - parentMethod()
			Parent class - parentMethod()
			
			//Child2 object:
			Child2 class - child2Method()
			Child2 class - parentMethod()
			
			//Child3 object:
			Child3 class - abstractParentMethod()
			
			//AParent reference:
			Child3 class - abstractParentMethod()
			AParent abstract class - implementedMethod()
			
			//ImplementerClass object:
			Implementer class - iMethod()
			
			//IMethods reference:
			Implementer class - iMethod()
			IMethods interface - defaultIMethod()
		*/
	}

}

