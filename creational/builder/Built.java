package ***.***.***.builder;

/**
 * Builder implementation:
 * an inner Builder static class is responsible for
 * creating the Built object calling its private 
 * constructor. Before returning the newly created
 * object with the static build() method, any of the
 * Built instance variable can be set calling the
 * a setter statically from the Builder class.
 * This helps to optionally set any number of attributes
 * the Built object might require.
 * */
public class Built {

	private byte bytE;
	private short shorT;
	private int inT;
	private long lonG;
	private float floaT;
	private double doublE;
	private String strinG;
	
	private Built() {}
	
	//	TODO: getters
	
	@Override
	public String toString() {
		return 	"Byte:  \t" + bytE + "\n" +
			"Short: \t" + shorT + "\n" +
			"Int:   \t" + inT + "\n" +
			"Long:  \t" + lonG + "\n" +
			"Float: \t" + floaT + "\n" +
			"Double:\t" + doublE + "\n" +
			"String:\t" + strinG;
	}
	
	/**
	 * inner Builder class
	 * */
	public final static class Builder {

		private static Builder builder;
		private static Built built = new Built();
		
		public static Built build() {
			return built;
		}
		
		public static Builder setByte(byte bytE) {
			built.bytE = bytE;
			return builder;
		}
		
		public static Builder setShort(short shorT) {
			built.shorT = shorT;
			return builder;
		}
		
		public static Builder setInt(int inT) {
			built.inT = inT;
			return builder;
		}
		
		public static Builder setLong(long lonG) {
			built.lonG = lonG;
			return builder;
		}
		
		public static Builder setFloat(float floaT) {
			built.floaT = floaT;
			return builder;
		}
		
		public static Builder setDouble(double doublE) {
			built.doublE = doublE;
			return builder;
		}
		
		public static Builder setString(String strinG) {
			built.strinG = strinG;
			return builder;
		}
	}
	
}
