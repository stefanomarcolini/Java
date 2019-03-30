package design.pattern.creational.builder;

import design.pattern.creational.builder.Built.Builder;

public class BuilderMain {

	public static void main(String[] args) {

		@SuppressWarnings("static-access")
		Built built = Builder.setInt(2)
							 .setFloat((float) Math.PI)
							 .setByte((byte) 12)
							 .setShort((short) 6500)
							 .setDouble(Math.E)
							 .setLong(987654321098765432L)
							 .setString("Ready to build...")
							 .build();
		
		System.out.println(built.toString());
		
	}

}
