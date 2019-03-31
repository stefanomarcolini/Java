package design.pattern.creational.prototype;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Prototype implementation:
 * the prototype is responsible to clone itself
 * so that other objects can be created from it
 * with the same properties.
 * 
 * This Prototype uses a private constructor that
 * creates a new object identical to the one that
 * is passed as parameter using java Reflection
 * */
public class Prototype implements Cloneable {

	private int inT;
	private double[] doubleArray;
	private String strinG;
	private LinkedList<?> list;

	public Prototype(int inT, double[] doubleArray, String strinG, LinkedList<?> list) {
		this.inT = inT;
		this.doubleArray = doubleArray;
		this.strinG = strinG;
		this.list = list;
	}

	private Prototype(Prototype prototype) {
		Field[] fields = prototype.getClass().getDeclaredFields();
		Field[] params = this.getClass().getDeclaredFields();

		if (params.length == fields.length) {
			for (int i = 0; i < fields.length; i++) {
				if (params[i].getType().equals(fields[i].getType())) {
					try {
						params[i].set(this, fields[i].get(prototype));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		Prototype copy = new Prototype(this);

		return copy;
	}

	@Override
	public String toString() {
		return "inT:         " + inT + "\n" + "doubleArray: " + Arrays.toString(doubleArray) + "\n" + "strinG:      "
				+ strinG + "\n" + "list:        " + listToString(list);
	}

	private <T> String arrayToString(T[] array) {
		return Arrays.toString(array);
	}

	@SuppressWarnings("unchecked")
	private <T> String listToString(LinkedList<T> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (Object obj : list) {
			if (obj.getClass().isArray()) {
				sb.append(arrayToString((T[]) obj));
			} else {
				sb.append(obj);
			}
			sb.append(", ");
		}
		return sb.toString().substring(0, sb.length() - 2) + "}";
	}

}
