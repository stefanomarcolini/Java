package tdd;

public class Math {

	public static long reminder(long numerator, long denominator) 
		throws ArithmeticException {
		
		if (checkParameters(numerator, denominator)) {
			long n = numerator;
			long d = denominator;
			n = n < 0 ? -n : n;
			d = d < 0 ? -d : d;
			int sign = numerator > 0 ? 1 : -1;
			long q = quotient(n, d);
			long r = (n - (q*d)) * sign;
			return r;
		}
		throw new ArithmeticException();
	}
	
	public static int[] divide(int numerator, int denominator)
		throws ArithmeticException {
		
		int[] qr = new int[2];
		if (checkParameters(numerator, denominator)) {
			int a = numerator;
			int b = denominator;
			if (a == b) {
				qr[0] = 1;
				qr[1] = 0;
			} else if (a < b) {
				qr[1] = a;
			} else {
				while(b <= a) {
					qr[0] += 1;
					b += denominator;
					if (b > a) {
						b -= denominator;
						qr[1] = a - b;
						return qr;
					}
				}
			}
		} else {
			throw new ArithmeticException();
		}
		return qr;
	}
	
	public static int bitwiseMultiply(int a, int b) {
		int r = 0;
		while(b != 0) {
			if ((b&1)!= 0) {
				r += a;
			}
			a<<=1;
			b>>>=1;
		}
		return r;
	}
	
	public static long quotient(long numerator, long denominator) {

		long n = numerator;
		long d = denominator;
		n = n < 0 ? -n : n;
		d = d < 0 ? -d : d;
		long q = 1;
		while (d*q++ < n) {}
		if (d*--q > n) {
			q--;
		}
		return q;
	}
	
	private static byte[] longToBinaryByteArray(long value) {
		byte[] binary = new byte[Long.SIZE];
		long v = value == Long.MIN_VALUE ? Long.MAX_VALUE 
				 : value < 0 ? -value 
				 : value;
		int sign = value < 0 ? 1 : 0;
		int length = binary.length - 1;
		for (int i = length; i >= 0; i--) {
			if (i == length) {
				binary[i] = (byte)sign;
			}
			else if ((long)1<<i <= v) {
				v -= ((long)1<<i);
				binary[i] = 1;
			}
		}
		return binary.clone();
	}
	
	public static long binaryToLong(long value) {
		byte[] binary = longToBinaryByteArray(value);
		long r = 0;
		int length = binary.length - 1;
		int sign = 1;
		boolean min = true;
		for (int i = length; i >= 0; i--) {
			if (i == length) {
				sign = binary[length] == 0 ? 1 : -1;
			} else if (sign > 0) {
				if (i == 0) {
					r += binary[i] == 0 ? 0 : 1;
				} else {
					r += binary[i] == 0 ? 0 : (long)1 << i;
				} 
			} else {
				min = binary[i] == 0 ? false : min;
				if (i == 0) {
					r -= binary[i] == 0 ? 0 : 1;
					r -= min ? 1 : 0;
				} else {
					r -= binary[i] == 0 ? 0 : (long)1 << i;
				} 
			}
		}
		return r;
	}

	private static boolean checkParameters(long numerator, long denominator) {
		return (numerator != 0 && denominator != 0);
	}
	
}
