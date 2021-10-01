package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		
		int n = Integer.parseInt(args[0]);
		for(double i = 0;i < n;i++) {
            double sign = Math.pow((-1.0), i);
			piEstimation += sign * (1/(2*i + 1));
		}
		piEstimation *= 4;
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
