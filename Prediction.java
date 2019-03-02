package ml.function.prediction;

import java.util.Arrays;

import function.matrix.Matrix;

public class Prediction {

// ==== FEATURE SCALING ==== //
	/**
	 * <pre>
	 * REQUIRES: double V[n]
	 * MODIFIES: ---
	 * EFFECT:   returns a new scaled vector S[n]
	 *           where 0 <= S[i] <= 1
	 * </pre>
	 * */
	public double[] featureScaling(double[] V) {
		
		int m = V.length;

		double[] scaled = new double[m];
		
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		
		for (int i = 0; i < m; i++) {
			min = Math.min(min, V[i]);
			max = Math.max(max, V[i]);
		}

		double range = max/2 - min/2;	// in case of range > MAX_VALUE
		
		for (int i = 0;  i < m; i++) {
			scaled[i] = range != 0 ? (V[i] / range) / 2 : V[i];
		}
		return scaled;
	}


	/**
	 * <pre>
	 * REQUIRES: double M[m][n]
	 * MODIFIES: ---
	 * EFFECT:   returns a new scaled matrix S[m][n]
	 *           where 0 <= S[i][j] <= 1
	 * </pre>
	 * */
	public double[][] featureScaling(double[][] M) {
		
		int m = M.length;
		int n = M[0].length;

		double[][] scaled = new double[m][n];
		
		double[] max = new double[n];
		double[] min = new double[n];
		Arrays.fill(max, Double.MIN_VALUE);
		Arrays.fill(min, Double.MAX_VALUE);
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				min[j] = Math.min(min[j], M[i][j]);
				max[j] = Math.max(max[j], M[i][j]);
			}
		}

		double[] range = new double[n];
		for (int j = 0; j < n; j++) {
			range[j] = max[j]/2 - min[j]/2;	// in case of range > MAX_VALUE
		}

		for (int j = 0;  j < n; j++) {
			for (int i = 0;  i < m; i++) {
				scaled[i][j] = range[j] != 0 ? (M[i][j] / range[j]) / 2 : M[i][j];
			}
		}
		return scaled;
	}


// ==== MEAN NORMALIZATION ==== //
	/**
	 * <pre>
	 * REQUIRES: double V[n]
	 * MODIFIES: ---
	 * EFFECT:   returns mean normalization vector S[n]
	 *           where -0.5 <= N[i] <= 0.5
	 * </pre>
	 * */
	public double[] meanNormalization(double[] V) {


		int m = V.length;
		double mean = 0;
		double halfRange = 0;
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (double d : V) {
			min = Math.min(min, d);
			max = Math.max(max, d);
			mean += d / m;
		}
		halfRange = (Math.abs(min/2) + Math.abs(max/2));
		double[] N = new double[m];
		for (int i = 0; i < m; i++) {
			N[i] = ((V[i] - mean) / (halfRange)) / 2;
		}
		return N;
	}
	

// ==== MULTIVARIATE LINEAR REGRESSION ==== //
	/**
	 * <pre>
	 * REQUIRES:  double  X[n]  (features) X<sub>n</sub>
	 *            double  T[m]  (parameters) Theta<sub>m</sub>
	 *            for m == n + 1
	 * MODIFIES:  ---
	 * EFFECT:    returns a new multivariate linear regression vector Htx[m] 
	 *            where Htx[m] = { T[0]*1, T[1]*X[0], ..., T[m]*X[n] }
	 *            (or)  Htx = T transpose * X
	 * </pre>
	 * */
	public double[] multivariateLinearRegression(double[] X, double[] T) throws IllegalArgumentException {
		int m = T.length;
		int n = X.length;
		if (m != n + 1) {
			throw new IllegalArgumentException("Invalid input params: theta lenght must be features length + 1");
		}
		double[] Htx = new double[m];
		for (int i = 0; i < m; i++) {
			Htx[i] += (i == 0) ? T[i] : T[i] * X[i - 1];
		}
		return Htx;
	}

	

// ==== POLINOMIAL LINEAR REGRESSION ==== //
	/**
	 * <pre>
	 * REQUIRES:  double X[m] (features) X<sub>n</sub>
	 *            double T[n] (parameters) Theta<sub>m</sub>
	 *            double P[m] contains power for each feature in X[m]
	 *            for m == n + 1
	 * MODIFIES:  ---
	 * EFFECT:    returns a new polinomial linear regression vector Htx[m] 
	 *            where Htx[m] = { T[0]*1, T[1]*(X[0])<sup>P[0]</sup>, ..., T[m]*(X[n])<sup>P[n]</sup> }
	 * </pre>
	 * */
	public double[] polinomialLinearRegression(double[] X, double[] T, double[] P) throws IllegalArgumentException {
		int n = T.length;
		int m = X.length;
		if (m != P.length) {
			throw new IllegalArgumentException("Invalid input params: features length must be equal to powers length");
		} else if (n != m + 1) {
			throw new IllegalArgumentException("Invalid input params: theta lenght must be features length + 1");
		}
		double[] Htx = new double[n];
		for (int i = 0; i < n; i++) {
			Htx[i] += (i == 0) ? T[i] : T[i] * Math.pow(X[i - 1], P[i - 1]);
		}
		return Htx;
	}

	

// ==== PREDICTIONS ==== //
	/**
	 * <pre>
	 * REQUIRES:  double X[m][n] (features) X<sup>m</sup><sub>n</sub>
	 *            double T[n]    (parameters) Theta<sub>n</sub>
	 *            for    m == n + 1
	 * MODIFIES:  ---
	 * EFFECT:    returns a new vector hypothesis of X Htx[m]
	 *            where Htx[m] = { T[0]*1, T[1]*X[0], ..., T[m]*X[n] }
	 * </pre>
	 * */
	public double[] predictions(double[][] X, double[] T) throws IllegalArgumentException {
		int m = X.length;
		int n = X[0].length;
		if (n != T.length) {
			throw new IllegalArgumentException("Invalid input params: theta and features row length must be equal");
		}
		double[] predicitions = new double[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				predicitions[i] += T[j] * X[i][j];
			}
		}
		return predicitions;
	}

	

// ==== COST FUNCTION ==== //
	/**
	 * <pre>
	 * REQUIRES:  double X[m][n] training set containing features
	 *            double Y[m]    real values data
	 *            double T[n]    theta initial values
	 * MODIFIES:  ---
	 * EFFECT:    returns the value of the cost function J(T<sub>n</sub>)
	 *            J(T<sub>0</sub>, T<sub>1</sub>, ..., T<sub>n</sub>) 
	 *            J(T<sub>n</sub>) = <sup>1</sup>/<sub>2m</sub> * sum<sup>m</sup><sub>i=1</sub>(h<sub>T</sub>(x<sup>(i)</sup>) - y<sup>(i)</sup>)<sup>2</sup>
	 * </pre>
	 * */
	public double costFunction(double[][] X, double[] Y, double[] T) throws IllegalArgumentException {

		int m = X.length;
		
		Matrix mx = new Matrix();
		double sum = mx.sumVectorEntries(squaredErrors(X, Y, T));
		
		double J = sum/(2.0*m);
		
		return J;
	}



// ==== ERRORS ==== //
	/**
	 * <pre>
	 * REQUIRES: double hTx[n] row of X<sup>(j)</sup>*T<sup>(j)</sup>
	 *                         { theta[0] * (x[0]==1), theta[1] * x[1], ... theta[n] * x[n] }
	 *           double y      real value that corresponds 
	 *                         to the row of X<sup>(j)</sup>*T<sup>(j)</sup>
	 * MODIFIES: ---
	 * EFFECT:   returns the error = hTx - y
	 * </pre>
	 * */
	public double error(double[] hTx, double y) {
		Matrix mx = new Matrix();
		return mx.sumVectorEntries(hTx) - y;
		
	}
	
	

	/**
	 * <pre>
	 * REQUIRES: x -> features     double[m][n] 
	 *           t -> theta        double[n]
	 *           y -> real values  double[m]
	 * MODIFIES: ---
	 * EFFECT:   returns the errors (h<sub>t(x<sup>(i)</sup>)</sub> - y<sup>(i)</sup>)
	 *           (sum(x<sup>i</sup><sub>j</sub>*t<sub>j</sub>) - y<sup>i</sup>)
	 * </pre>
	 * */
	public double[] errors(double[][] x, double[] t, double[] y) {
		int m = x.length;
		int n = t.length;
		double[] error = new double[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				error[i] += t[j] * x[i][j]; // x[0]*t[0] + x[1]*t[1]*+...+x[n]*t[n]
			}
			error[i] -= y[i];				// row sum - y[i]
		}
		return error;
	}
	

// ==== SQUARED ERRORS ==== //
	/**
	 * <pre>
	 * REQUIRES: double X[m][n] features
	 *           double Y[m]    real data values
	 *           double T[n]    theta
	 * MODIFIES: ---
	 * EFFECT:   returns a new vector containing squared errors
	 * </pre>
	 * */
	public double[] squaredErrors(double[][] X, double[] Y, double[] T) throws IllegalArgumentException {

		int m = X.length;
		int n = X[0].length;
		
		if (m != Y.length) {
			throw new IllegalArgumentException("Invalid input params: features and real data rows must match");
		} else if (n != T.length) {
			throw new IllegalArgumentException("Invalid input params: features columns and theta length must match");
		}
		double[] hx = new double[m];
		double[] sqrErr = new double[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				hx[i] += T[j] * X[i][j];
			}
			sqrErr[i] = Math.pow((hx[i] - Y[i]), 2);
		}
		
		return sqrErr;
	}
		


// ==== PARTIAL DERIVATIVE ==== //
	/**
	 * <pre>
	 * REQUIRES: double  X[n]  features row
	 *           double  T[n]  theta
	 *           double  Y[m]  real data
	 *           where   T = (parameters) Theta<sub>n</sub>
	 *                   X = (features) X<sub>j</sub>
	 * MODIFIES: ---
	 * EFFECT:   returns the partial derivative in d(theta)
	 *           <sup>1</sup>/<sub>m</sub> * sum<sup>m</sup><sub>i=1</sub>((h<sub>T</sub>(x<sup>i</sup> ) - y<sup>(i)</sup>) * x<sub>j</sub><sup>(i)</sup>)
	 * </pre>
	 * */
	public double partialDerivative(double[] X, double[] T, double[] Y) {

		int m = Y.length;
		int n = X.length;
		
		double sum = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sum += error(multivariateLinearRegression(T, X), Y[i]) * X[j];
			}
		}
		
		return sum / m;
	}
	
	

	/**
	 * <pre>
	 * REQUIRES: x -> features     double[m][n] 
	 *           t -> theta        double[n]
	 *           y -> real values  double[m]
	 * MODIFIES: ---
	 * EFFECT:   returns the partial derivative
	 *           <sup>1</sup>/<sub>m</sub> * sum(h<sub>t(x<sup>(i)</sup>)</sub> - y<sup>(i)</sup>)*x<sub>j</sub><sup>(i)</sup>
	 *    where: 0 <= i <= m
	 *           0 <= j <= n
	 * </pre>
	 * */
	public double[] partialDerivative(double[][] x, double[] t, double[] y) {
		int m = x.length;
		int n = t.length;
		double[] derivative = new double[n];
		double[] errors = errors(x, t, y);
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				derivative[j] += errors[i] * x[i][j];
			}
			derivative[j] /= m;
		}
		return derivative;
	}



// ==== GRADIENT DESCENT ==== //
	/**
	 * <pre>
	 * REQUIRES: double X[j]   features (j = n - 1)
	 *           double Y[m]   real data
	 *           double T[n]   parameters
	 *           double alpha  learning rate
	 *           int feedback  print every ...
	 * MODIFIES: ---
	 * EFFECT:   returns the parameters array (Theta) T[n] 
	 *           of the minimized cost function min(J(T<sub>n</sub>))
	 *           Repeat {
	 *             T<sub>j</sub> :=  T<sub>j</sub> - alpha * <sup>d</sup>/<sub>dT<sub>j</sub></sub> * J(T<sub>0</sub>, T<sub>1</sub>, ..., T<sub>n</sub>)
	 *             (simultaneously update for every j = 0, ..., n)
	 *           }
	 * </pre>
	 * */
	public double[] gradientDescent(double[] X, double[] T, double[] Y, double alpha, int feedback) {

		int n = T.length;
		double min = Double.MAX_VALUE;
		double actual = 0;
		int bestIteration = 0;
		double previous = Double.MAX_VALUE;
		Matrix mx = new Matrix();
		int i = 0;
		while (true) {
			
			for (int j = 0; j < n; j++) {
				T[j] = T[j] - alpha * partialDerivative(T, X, Y);
			}
			
			actual = mx.sumVectorEntries(multivariateLinearRegression(T, X));
			min = Math.min(Math.abs(min), Math.abs(actual));
			
			bestIteration++;
			
			if (i % feedback == 0) {
				System.out.println(String.format(i + ")\tmin cost: %.19f | actual: %.19f | T[0]: %.10f | T[1]: %.10f", 
													min,
													actual, 
													T[0], 
													T[1]
												)
								  );
			}
			
			if (min == 0) {
				System.out.println("PERFECT!");
				break;
			} else if (Math.abs(actual) > Math.abs(min)) {
				System.out.println("MINIMUM FOUND!");
				break;
			} else if (Math.abs(actual) == Math.abs(min)) {
				if (previous == min) {
					System.out.println("QUASI MINIMUM FOUND!");
					break;
				}
				previous = min;
			}
			++i;
		}

		
		System.out.println("\n===================="
				+ "\tBEST RESULTS:\t"
				+ "====================");
		System.out.println(String.format(bestIteration + ")\tmin cost: %.19f | actual: %.19f | T[0]: %.10f | T[1]: %.10f", 
											min, 
											actual,
											T[0], 
											T[1]
										)
							);
		
		return T;
	}
	

	
	/**
	 * <pre>
	 * REQUIRES: double x[m][n]  features
	 *           double y[m]     real values
	 *           double t[n]     theta
	 *           double alpha    learning rate (es. 0.003)
	 *           int feedback    print every ...
	 * MODIFIES: ---
	 * EFFECT:   returns a new double[n] theta vector 
	 *           for the minimized cost function min(J<sub>(t<sub>0</sub>,.,t<sub>n</sub>)</sub>)
	 *           using the batched gradient descent
	 * </pre>
	 * */
	public double[] gradientDescent(double[][] X, double[] Y, double[] T, double alpha, int feedback) {
		
		int m = X.length;
		int n = X[0].length;
		int c = 0;
		
		double[] t = Arrays.copyOf(T, n);
		
		double J = 0;
		double Jm = 0;
		
		double[] hx = new double[m];
		double[] derivative = new double[n];
		
		while (true) {
			
			J = costFunction(X, Y, t);
			Arrays.fill(hx, 0);
			Arrays.fill(derivative, 0);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					hx[i] += X[i][j] * t[j];
					derivative[j] += (hx[i] - Y[i]) * X[i][j];
				}
			}
			
			for (int j = 0; j < n; j++) {
				t[j] -= (alpha * (derivative[j] / m));
			}
			
			Jm = costFunction(X, Y, t);
			
			if (c % feedback == 0) {
				System.out.println(String.format(">> %d)\ncost: %.10f\nmin:  %.10f\nt0:   %.10f\tt1: %.10f\tt2: %.10f\n", c, J, Jm, t[0], t[1], t[2]));
			}

			
			if (Jm >= J) {
				System.out.println(String.format(">> %d)\tQUASI MINIMUM FOUND!", c));
				System.out.println(String.format("cost: %.10f\nmin:  %.10f\nt0:   %.10f\tt1: %.10f\tt2: %.10f", Jm, J, t[0], t[1], t[2]));
				return t;
			}else if (Jm == 0) {
				System.out.println(String.format(">> %d)\tQUASI MINIMUM FOUND!", c));
				System.out.println(String.format("cost: %.10f\nmin:  %.10f\nt0:   %.10f\tt1: %.10f\tt2: %.10f", Jm, J, t[0], t[1], t[2]));
				return t;
			}
			++c;
		}
	
	}
	


// ==== NORMAL EQUATION ==== //
	/**
	 * <pre>
	 * IMPOTRANT: better if m > n ie. rows > columns
	 *            if (X<sup>T</sup> * X) is NON invertible
	 *            the normal equation cannot be calculated
	 * 
	 * REQUIRES: double X[m][n]   features
	 *           double Y[m]      real data (results)
	 * MODIFIES: ---
	 * EFFECT:   returns the vector T[n] (theta)
	 *           T[n] = (X<sup>T</sup> * X)<sup>-1</sup> * (X<sup>T</sup> * Y)
	 * </pre>
	 * */
	public double[] normalEquation(double[][] X, double[] Y) {
		
		Matrix mx = new Matrix();
		double[][] XT = mx.transpose(X);
		return mx.multiplyVectorMatrix(
				mx.multiplyMatrices(
						mx.pseudoInverse(
								mx.multiplyMatrices(XT, X, true)
								)
						, XT, false)
				, Y, false);
		
	}
		
}
