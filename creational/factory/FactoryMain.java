package design.pattern.creational.factory;

import design.pattern.creational.factory.Factory.A;
import design.pattern.creational.factory.Factory.B;
import design.pattern.creational.factory.Factory.C;

public class FactoryMain {

	public static void main(String[] args) {
		
		A a = (A) Factory.create("A");
		B b = (B) Factory.create("B", new Integer(10), "Hello World!");
		C c = (C) Factory.create("C", Math.E, "Hi there!", new int[] {0, 1, 2, 3, 4});

	}

}
