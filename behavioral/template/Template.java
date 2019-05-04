package ***.***.***.template;

public abstract class Template implements ITemplate {

	public void template() {
		read();
		execute();
		write();
	}
	
	public abstract void read();
	public abstract void write();
	public abstract void execute();
	
}

interface ITemplate {
	public abstract void template();
}

class Implement extends Template {

	public void read() {
		System.out.println("Class Implement - read()");
	}

	public void write() {
		System.out.println("Class Implement - write()");
	}

	public void execute() {
		System.out.println("Class Implement - execute()");
	}
	
}
