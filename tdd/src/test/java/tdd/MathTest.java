package tdd;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MathTest {

	@Test(expected = ArithmeticException.class)
	public void testReminderZeroArgument() {
		testReminder(0, -2, FAIL.FAIL.getFail());
		testReminder(-2, 0, FAIL.FAIL.getFail());
		testReminder(-0, -2, FAIL.FAIL.getFail());
		testReminder(-2, -0, FAIL.FAIL.getFail());
		testReminder(-0, 2, FAIL.FAIL.getFail());
		testReminder(2, -0, FAIL.FAIL.getFail());
		testReminder(0, 2, FAIL.FAIL.getFail());
		testReminder(2, 0, FAIL.FAIL.getFail());
		testReminder(0, 0, FAIL.FAIL.getFail());
	}
	
	@Test
	public void testReminder() {
		testReminder(1, 2, 1);
		testReminder(2, 2, 0);
		testReminder(-2, 1, 0);
		testReminder(2, -1, 0);
		testReminder(9, 3, 0);
		testReminder(-9, 3, 0);
		testReminder(-10, 3, -1);
		testReminder(10, -3, 1);
	}

	@Test(expected = ArithmeticException.class)
	public void testDivideZeroArgumet() {
		testDivide(0, 0);
		testDivide(-0, 0);
		testDivide(0, -0);
		testDivide(-0, -0);
		testDivide(0, 1);
		testDivide(-0, 1);
		testDivide(0, -1);
		testDivide(-0, -1);
		testDivide(1, 0);
		testDivide(-1, 0);
		testDivide(1, -0);
		testDivide(-1, -0);

		testDivide(Integer.MAX_VALUE, 0);
		testDivide(-Integer.MAX_VALUE, 0);
		testDivide(Integer.MAX_VALUE, -0);
		testDivide(-Integer.MAX_VALUE, -0);
		
		testDivide(Integer.MIN_VALUE, 0);
		testDivide(-Integer.MIN_VALUE, 0);
		testDivide(Integer.MIN_VALUE, -0);
		testDivide(-Integer.MIN_VALUE, -0);
	}
	
	@Test
	public void testDivide() {
		
		testDivide(Integer.MAX_VALUE, 1);
		testDivide(-Integer.MAX_VALUE, 1);
		testDivide(Integer.MAX_VALUE, -1);
		testDivide(-Integer.MAX_VALUE, -1);
		
		testDivide(1, Integer.MAX_VALUE);
		testDivide(-1, Integer.MAX_VALUE);
		testDivide(1, -Integer.MAX_VALUE);
		testDivide(-1, -Integer.MAX_VALUE);
		
		testDivide(2, Integer.MAX_VALUE);
		testDivide(-2, Integer.MAX_VALUE);
		testDivide(2, -Integer.MAX_VALUE);
		testDivide(-2, -Integer.MAX_VALUE);
		
		testDivide(3, Integer.MAX_VALUE);
		testDivide(-3, Integer.MAX_VALUE);
		testDivide(3, -Integer.MAX_VALUE);
		testDivide(-3, -Integer.MAX_VALUE);
		
		testDivide(Integer.MIN_VALUE, 1);
		testDivide(-Integer.MIN_VALUE, 1);
		testDivide(Integer.MIN_VALUE, -1);
		testDivide(-Integer.MIN_VALUE, -1);
		
		testDivide(1, Integer.MIN_VALUE);
		testDivide(-1, Integer.MIN_VALUE);
		testDivide(1, -Integer.MIN_VALUE);
		testDivide(-1, -Integer.MIN_VALUE);
		
		testDivide(2, Integer.MIN_VALUE);
		testDivide(-2, Integer.MIN_VALUE);
		testDivide(2, -Integer.MIN_VALUE);
		testDivide(-2, -Integer.MIN_VALUE);
		
		testDivide(3, Integer.MIN_VALUE);
		testDivide(-3, Integer.MIN_VALUE);
		testDivide(3, -Integer.MIN_VALUE);
		testDivide(-3, -Integer.MIN_VALUE);
		
		testDivide(Integer.MAX_VALUE, Integer.MAX_VALUE);
		testDivide(-Integer.MAX_VALUE, Integer.MAX_VALUE);
		testDivide(Integer.MAX_VALUE, -Integer.MAX_VALUE);
		testDivide(-Integer.MAX_VALUE, -Integer.MAX_VALUE);
		
		testDivide(Integer.MAX_VALUE, Integer.MIN_VALUE);
		testDivide(-Integer.MAX_VALUE, Integer.MIN_VALUE);
		testDivide(Integer.MAX_VALUE, -Integer.MIN_VALUE);
		testDivide(-Integer.MAX_VALUE, -Integer.MIN_VALUE);
		
		testDivide(Integer.MIN_VALUE, Integer.MAX_VALUE);
		testDivide(-Integer.MIN_VALUE, Integer.MAX_VALUE);
		testDivide(Integer.MIN_VALUE, -Integer.MAX_VALUE);
		testDivide(-Integer.MIN_VALUE, -Integer.MAX_VALUE);
		
		testDivide(3, 2);
		testDivide(5, 2);
		testDivide(10, 2);
		testDivide(10, 3);
		testDivide(111, 2);
	}

	@Test
	public void testBinaryToLong() {

		testBinaryToLong(10, 0b1010);
		testBinaryToLong(-10, -0b1010);

		testBinaryToLong(0, -0b0);
		testBinaryToLong(-1, -0b1);
		testBinaryToLong(Long.MAX_VALUE, ((long) 1 << 63) - 1);
		testBinaryToLong(Long.MIN_VALUE, ((long) 1 << 63));
	}

	@Test
	public void testBitwiseMultiply() {
		testBitwiseMultiply(0, 0);
		testBitwiseMultiply(-0, -0);
		testBitwiseMultiply(-0, 0);
		testBitwiseMultiply(0, -0);
		testBitwiseMultiply(1, 0);
		testBitwiseMultiply(0, 1);
		testBitwiseMultiply(-1, 0);
		testBitwiseMultiply(0, -1);
		testBitwiseMultiply(1, -0);
		testBitwiseMultiply(-0, 1);
		testBitwiseMultiply(Integer.MAX_VALUE, 0);
		testBitwiseMultiply(Integer.MAX_VALUE, -0);
		testBitwiseMultiply(Integer.MAX_VALUE, 1);
		testBitwiseMultiply(Integer.MAX_VALUE, -1);
		testBitwiseMultiply(Integer.MAX_VALUE, 2);
		testBitwiseMultiply(Integer.MIN_VALUE, 0);
		testBitwiseMultiply(Integer.MIN_VALUE, 0);
		testBitwiseMultiply(Integer.MIN_VALUE, 1);
		testBitwiseMultiply(Integer.MIN_VALUE, -1);
		testBitwiseMultiply(Integer.MIN_VALUE, 2);
		testBitwiseMultiply(3, 2);
		testBitwiseMultiply(-3, 2);
		testBitwiseMultiply(3, -2);
		testBitwiseMultiply(-3, -2);
	}

	private void testReminder(long numerator, long denominator, long reminder) {
		assertTrue(Math.reminder(numerator, denominator) == reminder);
	}

	private void testDivide(int numerator, int denominator) {
		int[] qr = Math.divide(numerator, denominator);
		assertTrue(qr[0] == numerator / denominator && qr[1] == numerator % denominator);
	}

	private void testBitwiseMultiply(int a, int b) {
		int m = Math.bitwiseMultiply(a, b);
		assertTrue(m == a * b);
	}

	private void testBinaryToLong(long value, long binary) {
		assertTrue(Math.binaryToLong(value) == binary);
	}

	enum FAIL {
		FAIL(-1);
		private int fail;

		private FAIL(int fail) {
			this.fail = fail;
		}

		public int getFail() {
			return fail;
		}
	}
}
