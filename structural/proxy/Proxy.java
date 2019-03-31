package ***.***.structural.proxy;

import java.util.LinkedList;

/**
 * Proxy implementation:
 * the proxy is responsible to manage the business logic
 * used to read, write and delete each Message based on
 * the Person's login privilege.
 * */
public class Proxy {

	private Login login;
	private LinkedList<Message> messages;
	
	public Proxy() {
		messages = new LinkedList<>();
	}

	public void login(Person person) {
		this.login = new Login(person);
		login.login(login.person.name, login.person.privileges);
	}
	
	public void write(String message) {
		Message msg = new Message(message);
		messages.add(msg);
	}

	public void read() {
		if (messages == null || messages.isEmpty()) {
			System.out.println("There are no messages yet!");
		} else {

			for (Message message : messages) {
				System.out.println(String.format("%d >> %s", message.ID, message.message));
			}
		}
	}

	/**
	 * only ADMINs can delete messages from the list
	 * */
	public void delete(Person person, long id) {

		if (messages == null || messages.isEmpty()) {
			System.out.println("There are no messages yet!");
		} else {
			for (Message message : messages) {
				if (Privileges.ADMIN.equals(person.privileges) && message.ID == id) {
					if (messages.remove(message)) {
						System.out.println(String.format("Message %d >> DELETED!", message.ID));
						break;
					} else {
						System.err.println("Error: Unable to Delete message");
						break;
					}
				} else {
					System.out.println(String.format("Sorry, you can't delete message %d as " + 
									 "you are logged in as %s", id, person.privileges));
					break;
				}
			}
		}
	}
	
	/**
	 * inner public Person class
	 * */
	public class Person {
		
		private String name;
		private Privileges privileges;
		
		public Person(String name, Privileges privileges) {
			this.name = name;
			this.privileges = privileges;
		}
		
	}
	
	/**
	 * inner private Login class
	 * */
	private class Login {

		private Person person;
		
		private Login(Person person) {
			this.person = person;
		}

		private void login(String name, Privileges privileges) {
			System.out.println(String.format("Wellcome %s, you have logged in as %s", name, privileges));
		}

	}
	
	/**
	 * inner private Message class
	 * */
	private static class Message {

		private String message;
		private static long number;
		private final long ID;

		private Message(String message) {
			ID = ++number;
			this.message = message;
		}

	}
	
	/**
	 * inner public Privileges enum
	 * */
	public enum Privileges {
		USER, ADMIN;
	}
}
