package ml.function.classification;

import java.util.Arrays;

import ml.function.prediction.Prediction;

public class Classification {
	

// ==== SIGMOID ==== //
	/**
	 * <pre>
	 * REQUIRES: double x[m][n]  features
	 *           double t[n]     theta 
	 * MODIFIES: ---
	 * EFFECT:   returns a new sigmoid function vector: 0 < g<sub>(z)</sub> < 1
	 *           g<sub>(z)</sub> = <sup>1</sup>/<sub>(1 + e<sup>-z</sup>)</sub>  where: z = hypothesis of x
	 *           
	 * USE:      used for classification problems
	 *           with logistic regression
	 *           if g<sub>(z)</sub> > 0.5 prediction = 1
	 *           if g<sub>(z)</sub> < 0.5 prediction = 0
	 * </pre>
	 * */
	public double[] sigmoid(double[][] x, double[] t) {
		
		int m = x.length;
		
		Prediction p = new Prediction();
		double[] prediction = new double[m];
		prediction = p.predictions(x, t);
		
		double[] sigmoid = new double[m];
		for (int i = 0; i < m; i++) {
			sigmoid[i] = 1.0 / (1.0 + Math.pow(Math.E, -prediction[i]));
		}
		return sigmoid;
	}
	

// ==== LOGISTIC COST ==== //
	/**
	 * <pre>
	 * REQUIRES: double x[m][n]  features 
	 *           double y[m]     real values
	 *           double t[n]     theta
	 * MODIFIES: ---
	 * EFFECT:   returns the logistic cost function J(t)
	 *            J = -(1/m)*sum(y'*log(H)+(1-y)'*log(1-H))
	 *     where: H = sigmoid(x,t)
	 * </pre>
	 * */
	public double logisticCostFunction(double[][] x, double[] y, double[] t) {
		int m = x.length;
		double[] prediction = new double[m];
		prediction = sigmoid(x, t);
		
		double sum = 0;
		for (int i = 0; i < m; i++) {
			sum += (y[i]*Math.log(prediction[i])) + ((1-y[i])*Math.log(1-prediction[i]));
		}

		double J = -sum/m;
		return J;
	}
	

// ==== LOGISTIC REGRESSION ==== //
	/**
	 * <pre>
	 * REQUIRES: double x[m][n]  features
 	 *           double y[n]     data set real values (1 0r 2)
	 *           double t[n]     theta
	 *           double alpha    learning step
	 *           int    feedback iterations before 
	 *                           printing out feedback
	 * MODIFIES: t
	 * EFFECT:   returns the minimized cost function J(t)
	 *           using the logistic cost and sigmoid function
	 *           
	 * USE:      used for classification problems
	 * </pre>
	 * */
	public double[] logisticRegression(double[][] x, double[] y, double[] t, double alpha, int feedback) {
		
		int m = x.length;
		int n = t.length;
		int c = 0;
		double[] tmp = new double[n];
		double J = 0;
		double Jm = 0;
		double[] hx = new double[m];
		double[] derivative = new double[n];
		
		while (true) {
			
			J = logisticCostFunction(x, y, t);
			
			Arrays.fill(hx, 0);
			Arrays.fill(derivative, 0);
			hx = sigmoid(x, t);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					derivative[j] += (hx[i]-y[i])*x[i][j];
				}
			}
			
			for (int j = 0; j < n; j++) {
				t[j] -= alpha * (derivative[j] / m);
			}
			
			Jm = logisticCostFunction(x, y, t);
			
			if (Jm < J) {
				tmp = Arrays.copyOf(t, n);
			}
			
			if (c % feedback == 0) {
				System.out.println(c);
				System.out.println(String.format("t0  : %.20f\tt1  : %.20f\tt2  : %.20f\t", t[0], t[1], t[2]));
				System.out.println(String.format("cost: %.20f\t", J));
				System.out.println(String.format("min : %.20f\t", Jm));
			}
			
			if (Jm >= J) {
				System.out.println("\n" + c);
				t = Arrays.copyOf(tmp, n);
				System.out.println("QUASI MINIMUM FOUND!");
				System.out.println(String.format("t0  : %.20f\tt1  : %.20f\tt2  : %.20f\t", t[0], t[1], t[2]));
				System.out.println(String.format("cost: %.20f\t", Jm));
				System.out.println(String.format("min : %.20f\t", J));
				if (Jm > J) {
					System.out.println("\nREASON: DIVERGING -> min != 0 && min > cost");
				} else {
					System.out.println("\nREASON: STUCK -> min != 0 && min == cost");
				}
				return t;
			} else if (Jm == 0) {
				System.out.println("\n" + c);
				t = Arrays.copyOf(tmp, n);
				System.out.println("MINIMUM FOUND!");
				System.out.println(String.format("t0  : %.20f\tt1  : %.20f\tt2  : %.20f\t", t[0], t[1], t[2]));
				System.out.println(String.format("cost: %.20f\t", J));
				System.out.println(String.format("min : %.20f\t", Jm));
				return t;
			}
			++c;
		}
	}

}
