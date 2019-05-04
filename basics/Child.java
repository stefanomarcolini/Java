package ***.***.basics;

/**
 * Child class extending Parent class
 * */
public class Child extends Parent {
	
	public void childMethod() {
		System.out.println("Child class - childMethod()");
	}
	
}

/**
 * Parent class
 * */
class Parent {
	
	public void parentMethod() {
		System.out.println("Parent class - parentMethod()");
	}
	
}

/**
 * Child2 class extending Parent class
 * */
class Child2 extends Parent {
	
	public void child2Method() {
		System.out.println("Child2 class - child2Method()");
	}
	
	/**
	 * overrides Parent class method
	 * */
	@Override
	public void parentMethod() {
		System.out.println("Child2 class - parentMethod()");
	}
}

/**
 * AParent abstract class
 * contains abstract and implemented methods
 * */
abstract class AParent {
	
	public abstract void abstractParentMethod();
	
	public void implementedMethod() {
		System.out.println("AParent abstract class - implementedMethod()");
	}
}

/**
 * Child3 class extending AParent class
 * */
class Child3 extends AParent {

	/**
	 * overrides AParent class method
	 * */
	@Override
	public void abstractParentMethod() {
		System.out.println("Child3 class - abstractParentMethod()");
	}
	
}

/**
 * IMethod interface
 * contains abstract and default methods
 * */
interface IMethods {
	
	public abstract void iMethod();
	
	public default void defaultIMethod() {
		System.out.println("IMethods interface - defaultIMethod()");
	}
}

/**
 * Implementer class implementing IMethods interface method
 * */
class ImplementerClass implements IMethods {

	/**
	 * implements IMethods interface method
	 * */
	@Override
	public void iMethod() {
		System.out.println("Implementer class - iMethod()");
	}
	
}

