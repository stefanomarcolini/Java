package ***.***.***.factory;

/**
 * Factory design pattern implementation:
 * the Factory is responsible to choose which object
 * to create given the name of the object
 * */
public class Factory implements IFactory {
	
	public Factory f = null;
	
	@Override
	public IFactory create(String type) throws Exception {
		switch (type.toUpperCase()) {
			case "A":
				f = new A();
				break;
			case "B":
				f = new B();
				break;
			case "C":
				f = new C();
				break;
			default:
				throw new Exception("Unsupported Type");
		}
		return f;
	}
}

interface IFactory {
	public abstract IFactory create(String type) throws Exception;
}

interface IMethod {
	public abstract void iMethod();
}

class A extends Factory implements IMethod {

	@Override
	public void iMethod() {
		System.out.println("Class A - iMethod()");
	}
	
}

class B extends Factory implements IMethod {

	@Override
	public void iMethod() {
		System.out.println("Class B - iMethod()");
	}
	
}

class C extends Factory implements IMethod {

	@Override
	public void iMethod() {
		System.out.println("Class C - iMethod()");
	}
	
}
