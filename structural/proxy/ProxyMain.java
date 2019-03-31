package ***.***.structural.proxy;

import ***.***.structural.proxy.Proxy.Person;
import ***.***.structural.proxy.Proxy.Privileges;

public class ProxyMain {

	public static void main(String[] args) {
		
		Proxy proxy = new Proxy();
		
		Person[] persons = new Person[2];
		persons[0] = proxy.new Person("Alice", Privileges.ADMIN);
		persons[1] = proxy.new Person("Bob", Privileges.USER);
		
		
		proxy.login(persons[0]);
		
		proxy.read();
		proxy.write("Hello World!");
		proxy.read();
		proxy.delete(persons[0], 1L);
		
		proxy.login(persons[1]);
		
		proxy.read();
		proxy.write("Hi there");
		proxy.read();
		proxy.delete(persons[1], 1L);
		
		proxy.delete(persons[0], 2L);
		proxy.read();
		
		/*	OUTPUT:
		 * 
		 * 	Wellcome Alice, you have logged in as ADMIN
		 * 	There are no messages yet!
		 * 	1 >> Hello World!
		 * 	Message 1 >> DELETED!
		 * 	Wellcome Bob, you have logged in as USER
		 * 	There are no messages yet!
		 * 	2 >> Hi there
		 * 	Sorry, you can't delete message 1 as you are logged in as USER
		 * 	Message 2 >> DELETED!
		 * 	There are no messages yet!
		 * */
		
	}

}
