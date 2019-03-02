package function.matrix;

import java.util.Arrays;

public class Matrix {
	
// ==== SUM SCALAR TO VECTOR ==== //
	/**
	 * <pre>
	 * REQUIRES: double  V[n]
	 *           double  y
	 *           boolean subtract (true -> subtract, false -> add)
	 * MODIFIES: ---
	 * EFFECT:   returns the sum of a vector and a scalar
	 * 
	 * EXAMPLE:  V[n] = |1  2  3|  
	 *           y = 2
	 *           V[n] + y = |3  4  5|
	 *           V[n] - y = |-1 0  1|
	 * </pre>
	 * */
	public double[] sumScalarVector (double[] V, double y, boolean subtract) {
		
		int n = V.length;
		
		double[] C = new double[n];
		for (int i = 0; i < n; i++) {
				C[i] = subtract ? V[i] - y : V[i] + y;
		}
		
		return C;
	}
	

// ==== SUM VECTORS ==== //
	/**
	 * <pre>
	 * REQUIRES: double  A[n]
	 *           double  B[n]
	 *           boolean subtract (true -> subtract, false -> add)
	 * MODIFIES: ---
	 * EFFECT:   returns a new vector that is the sum of two vectors
	 * </pre>
	 * */
	public double[] sumVectors (double[] A, double[] B, boolean subtract) throws IllegalArgumentException {
		
		int n = A.length;
		
		if (n != B.length) {
			throw new IllegalArgumentException("Invalid input params: vectors must have same length");
		}
		
		double[] C = new double[n];
		for (int i = 0; i < n; i++) {
				C[i] = subtract ? A[i] - B[i] : A[i] + B[i];
		}
		
		return C;
	}

	
// ==== SUM MATRICES ==== //
	/**
	 * <pre>
	 * REQUIRES: double  A[m][n]
	 *           double  B[m][n]
	 *           boolean subtract (true -> subtract, false -> add)
	 * MODIFIES: ---
	 * EFFECT:   returns a new matrix that is the sum of two matrices
	 * </pre>
	 * */
	public double[][] sumMartix (double[][] A, double[][] B, boolean subtract) throws IllegalArgumentException {
		
		int m = A.length;
		int n = A[0].length;
		
		if (m != B.length && n != B[0].length) {
			throw new IllegalArgumentException("Invalid input params: matrices must have same dimensions");
		}
		
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = subtract ? A[i][j] - B[i][j] : A[i][j] + B[i][j];
			}
		}
		
		return C;
	}


// ==== SUM VECTOR'S ELEMENTS ==== //
	/**
	 * <pre>
	 * REQUIRES: double  V[n]
	 * MODIFIES: ---
	 * EFFECT:   returns the sum of all items
	 *           sum = V[0] + V[1] + ... + V[n]
	 * </pre>
	 * */
	public double sumVectorEntries(double[] V) {
		double sum = 0;
		for (double d : V) {
			sum += d;
		}
		return sum;
	}

// ==== SCALAR VECTOR MULTIPLICATION ==== //
	/**
	 * <pre>
	 * REQUIRES: double  V[n]
	 *           double  y
	 *           boolean divide (true -> divide, false -> multiply)
	 * MODIFIES: ---
	 * EFFECT:   returns the resulting multiplication / division of a vector with a scalar
	 * </pre>
	 * */
	public double[] multiplyScalarVector(double[] V, double y, boolean divide) {
		int n = V.length;
		double[] A = new double[n];
		y = divide ? (1 / y) : y;
		for (int i = 0; i < n; i++) {
			A[i] = y * V[i];
		}
		return A;
	}

	
// ==== SCALAR MATRIX MULTIPLICATION ==== //
	/**
	 * <pre>
	 * REQUIRES: double  M[m][n]
	 *           double  y
	 *           boolean divide (true -> divide, false -> multiply)
	 * MODIFIES: ---
	 * EFFECT:   returns a new matrix that is the result
	 *           of the multiplication of a matrix with a scalar
	 * </pre>
	 * */
	public double[][] multiplyScalarMatrix(double[][] M, double y, boolean divide) {
		int m = M.length;
		int n = M[0].length;
		double[][] X = new double[m][n];
		y = divide ? (1 / y) : y;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = y * M[i][j];
			}
		}
		return X;
	}
	

// ==== VECTORS MULTIPLICATION ==== //
	/**
	 * <pre>
	 * REQUIRES: double  A[n]
	 *           double  B[n]
	 * MODIFIES: ---
	 * EFFECT:   returns the result of the multiplication
	 *           of a vector with a scalar
	 * </pre>
	 * */
	public double multiplyVectors(double[] A, double[] B) throws IllegalArgumentException {
		int n = A.length;
		if (n != B.length) {
			throw new IllegalArgumentException("Invalid input params: vectors must have same length");
		}
		double mult = 0;
		for (int i = 0; i < n; i++) {
			mult += A[i] * B[i];
		}
		return mult;
	}


// ==== VECTOR MATRIX MULTIPLICATION ==== //
	/**
	 * <pre>
	 * IMPORTANT: matrix columns length must be equal to vector length
	 *            in case of division vector must NOT contain 0 (zeros) 
	 *            otherwise Infinity | Nan values may occur
	 * REQUIRES:  double  M[m][n]
	 *            double  V[n]
	 *            boolean divide (true -> divide, false -> multiply)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new vector that is the result of the
	 *            multiplication of a vector with a matrix
	 * </pre>
	 * */
	public double[] multiplyVectorMatrix(double[][] M, double[] V, boolean divide) throws IllegalArgumentException {
		int m = M.length;
		int n = M[0].length;
		if (n != V.length) {
			throw new IllegalArgumentException("Invalid input params: matrix columns length and vector length must match");
		}
		double[] X = new double[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i] += divide ? M[i][j] * (1 / V[j]) : M[i][j] * V[j];
			}
		}
		return X;
	}
	

// ==== MATRICES MULTIPLICATION ==== //
	/**
	 * <pre>
	 * IMPORTANT: M1 -> matrix[m][N], M2 -> matrix[N][o]
	 *            in case of divisions, matrix B must NOT contain 0 (zeros)
	 *            otherwise Infinity | Nan values may occur
	 *            
	 *            es. M1 = |1  2|  M2 = |3  4  5  6  9|
	 *                     |3  4|       |7  8  9  1  3|
	 *                     |6  5|
	 *                 
	 * MATRICES MULTIPLICATION IS <u>NON COMMUTATIVE</u>: (A * B) != (B * A)
	 * MATRICES MULTIPLICATION IS <u>ASSOCIATIVE</u>:     (A * B) * C == A * (B * C)
	 * 
	 * REQUIRES:  double  A[m][N]
	 *            double  B[N][o]
	 *            boolean divide (true -> divide, false -> multiply)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new matrix that is the result of the
	 *            multiplication of two matrices
	 * </pre>
	 * */
	public double[][] multiplyMatrices(double[][] A, double[][] B, boolean divide) throws IllegalArgumentException {
		
		int m = A.length;
		int n = A[0].length;

		int o = B[0].length;
		
		if (n != B.length) {
			throw new IllegalArgumentException("Invalid input params: matrix A columns and matrix B rows must have the same length");
		}
		
		double[][] X = new double[m][o];
		
		for (int i = 0; i < o; i++) {
			for (int j = 0; j < m; j++) {
				X[j][i] = multiplyVectorMatrix(A, exctractVector(B, i), divide)[j];
			}
		}
		
		return X;
	}
	

// ==== EXTRACT VECTOR ==== //
	/**
	 * <pre>
	 * REQUIRES:  double M[m][n]
	 *            double column (from which to extract the vertical vector)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new vector V[m] that contains all
	 *            items selected from the specified column of 
	 *            the given matrix
	 * </pre>
	 * */
	public double[] exctractVector(double[][] M, int column) {
		int m = M.length;
		double[] V = new double[m];
		for (int i = 0; i < m; i++) {
			V[i] = M[i][column];
		}
		return V;
	}
	

// ==== VECTOR TO MATRIX ==== //
	/**
	 * <pre>
	 * REQUIRES:  double V[n]
	 * MODIFIES:  ---
	 * EFFECT:    returns a new matrix M[n][2] such that 
	 *            every M[n][0] == 1 && M[n][1] = V[n]
	 * 
	 * EXAMPLE:   V = [2  3  4] -> M = |1  2|
	 *                                 |1  3|
	 *                                 |1  4|
	 * </pre>
	 * */
	public double[][] vectorToMatrix(double[] V) {
		int n = V.length;
		double[][] M = new double[n][2];
		for (int i = 0;  i < n; i++) {
			for (int j = 0; j < 2; j++) {
				M[i][j] = (j == 0) ? 1 : V[i];
			}
		}
		return M;
	}

	

// ==== ADD ONES TO MATRIX ==== //
	/**
	 * <pre>
	 * REQUIRES:  double M[m][n]
	 * MODIFIES:  ---
	 * EFFECT:    returns a new matrix M[m][n + 1] 
	 *            such that every M[m][0] == 1
	 * 
	 * EXAMPLE:   M = |2  3  4| -> M1 = |1  2  3  4|
	 *                |5  6  7|         |1  5  6  7|
	 * </pre>
	 * */
	public double[][] addOnesToMatrix(double[][] M) {
		int m = M.length;
		int n = M[0].length;
		double[][] M1 = new double[m][n + 1];
		for (int i = 0;  i < m; i++) {
			for (int j = 0; j < n + 1; j++) {
				M1[i][j] = (j == 0) ? 1 : M[i][j - 1];
			}
		}
		return M1;
	}


// ==== IDENTITY MATRIX ==== //
	/**
	 * <pre>
	 * FOR ANY MATRIX: (X[m][n] * I[n][n]) == (I[m][m] * X[m][n]) == X[m][n]
	 * 
	 * REQUIRES:  integer dimension d
	 * MODIFIES:  ---
	 * EFFECT:    returns the identity matrix[d][d]
	 * 
	 * EXAMPLE: dimensions = 3
	 *
	 *          Identity     |1 0 0|
	 *           Matrix    = |0 1 0|
	 *                       |0 0 1|
	 * </pre>
	 * */
	public double[][] identityMatrix(int dimensions) {
		
		double[][] I = new double[dimensions][dimensions];
		
		for (int i = 0; i < dimensions; i++) {
			I[i][i] = 1;
		}
		
		return I;
		
	}
	


// ==== PSEUDO INVERSE ==== //
	/**
	 * <pre>
	 * INVERSE MATRIX: (A * A<sup>-1</sup>) == (A<sup>-1</sup> * A) == I
	 *                 any square matrix multiplied by its inverse matrix
	 *                 gives the identity matrix (I)
	 *                 
	 *                 the pseudo inverse formula is used when a matrix cannot
	 *                 be inverted, it gives the same results or a very good 
	 *                 approximation of the values of the inverse matrix
	 * 
	 * REQUIRES:  double X[m][n] (not necessary a square matrix)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new the pseudo inverse matrix: 
	 *            first trying the left-inverse formula,
	 *            then, if results are not acceptable
	 *            using the right-inverse formula
	 *            left :  X<sup>+</sup> = (X<sup>T</sup> * X)<sup>-1</sup> * X<sup>T</sup>
	 *            right : X<sup>+</sup> = X<sup>T</sup> * (X * X<sup>T</sup>)<sup>-1</sup>
	 * </pre>
	 * */
	public double[][] pseudoInverse(double[][] X) {

		// left inverse
		double[][] leftInverse = leftInverse(X);
		
		for (double[] d : leftInverse) {
			if (Arrays.stream(d).anyMatch(e -> Double.valueOf(e).equals(Double.NaN))) {
				// right inverse
				return rightInverse(X);
			}
		}
		
		// left inverse
		return leftInverse;
	}
		

// ==== RIGHT INVERSE ==== //
	/**
	 * <pre>
	 * INVERSE MATRIX: (A * A<sup>-1</sup>) == (A<sup>-1</sup> * A) == I
	 *                 any square matrix multiplied by its inverse matrix
	 *                 gives the identity matrix (I)
	 * 
	 * REQUIRES:  double X[m][n] (not necessary a square matrix)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new inverse matrix
	 *            using the right inverse formula
	 *            right : X<sup>+</sup> = X<sup>T</sup> * (X * X<sup>T</sup>)<sup>-1</sup>
	 * </pre>
	 * */
	public double[][] rightInverse(double[][] X) {

		// right inverse
		double[][] rightInverse =
			multiplyMatrices(transpose(X),
				inverse(
					multiplyMatrices(X, transpose(X), false)
				)
			, false);

		return rightInverse;
	}
		

// ==== LEFT INVERSE ==== //
	/**
	 * <pre>
	 * INVERSE MATRIX: (A * A<sup>-1</sup>) == (A<sup>-1</sup> * A) == I
	 *                 any square matrix multiplied by its inverse matrix
	 *                 gives the identity matrix (I)
	 * 
	 * REQUIRES:  double X[m][n] (not necessary a square matrix)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new inverse matrix
	 *            using the left inverse formula
	 *            left :  X<sup>+</sup> = (X<sup>T</sup> * X)<sup>-1</sup> * X<sup>T</sup>
	 * </pre>
	 * */
	public double[][] leftInverse(double[][] X) {

		// left inverse
		double[][] leftInverse =
			multiplyMatrices(
					inverse(
						multiplyMatrices(
								transpose(X)
						, X, false)
					)
				, transpose(X), false);
				
		return leftInverse;
	}
	


// ==== INVERSE ==== //
	/**
	 * <pre>
	 * INVERSE MATRIX: (A * A<sup>-1</sup>) == (A<sup>-1</sup> * A) == I
	 *                 any square matrix multiplied by its inverse matrix
	 *                 gives the identity matrix
	 * 
	 * REQUIRES:  double M[m][m] (must be a square matrix)
	 * MODIFIES:  ---
	 * EFFECT:    returns a new inverse matrix
	 * </pre>
	 * */
	public double[][] inverse(double[][] X) throws IllegalArgumentException {
		
		// minors & cofactors
		int m = X.length;
		int n = X[0].length;
		
		if (m != n) {
			throw new IllegalArgumentException("Invalid input params: must be a square matrix");
		}
		
		double[][] inverse = new double[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0 ; j < n; j++) {
				inverse[i][j] = (i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0) ? -determinant(minor(X, i, j)) : determinant(minor(X, i, j));
			}
		}
		
		// adjugate & determinant
		inverse = multiplyScalarMatrix(transpose(inverse), Math.pow(determinant(X), -1), false);
		
		return inverse;
	}



// ==== MINOR ==== //
	/**
	 * <pre>
	 * REQUIRES:  double M[m][m]  (must be a square matrix)
	 *            int    row      index of the row to exclude
	 *            int    column   index of the column to exclude
	 * MODIFIES:  ---
	 * EFFECT:    returns a new the minor matrix where all the values of
	 *            the original matrix are represented, except for those
	 *            values contained in the selected row and column
	 * </pre>
	 * */
	public double[][] minor(double[][] X, int row, int column) throws IllegalArgumentException {
		int m = X.length;
		int n = X[0].length;
		if (row >= m) {
			throw new IllegalArgumentException("Invalid input params: row must be less than matrix rows length");
		} else if (column >= n) {
			throw new IllegalArgumentException("Invalid input params: colums must be less than matrix columns length");
		}
		double[][] minor = new double[m - 1][n - 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; i != row && j < n; j++) {
				if (j != column) {
					minor[i < row ? i : i - 1][j < column ? j : j - 1] = X[i][j];
				}
			}
		}
		return minor;
	}
	


// ==== DETERMINANT ==== //
	/**
	 * <pre>
	 * REQUIRES:  double M[m][m] (must be a square matrix)
	 * MODIFIES:  ---
	 * EFFECT:    returns the determinant of the matrix
	 * </pre>
	 * */
	public double determinant(double[][] M) throws IllegalArgumentException {
		int m = M.length;
		int n = M[0].length;
		if (m != n) {
			throw new IllegalArgumentException("Invalid input params: must be a square matrix");
		}
		
		if (m == 1) {
			return M[0][0];
		}

		if (m == 2) {
			return (M[0][0] * M[1][1] - M[0][1] * M[1][0]);
		}

		double det = 0;
		for (int i = 0; i < n; i++) {
			det += Math.pow(-1, i) * M[0][i] * determinant(minor(M, 0, i));
		}
		return det;
	}
	


// ==== TRANSPOSE ==== //
	/**
	 * <pre>
	 * REQUIRES:  double M[m][n]
	 * MODIFIES:  ---
	 * EFFECT:    returns a new transposed matrix
	 * </pre>
	 * */
	public double[][] transpose(double[][] M) {
		int m = M.length;
		int n = M[0].length;
		double[][] transpose = new double[n][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				transpose[j][i] = M[i][j];
			}
		}
		return transpose;
	}
	
	
}
