package il.ac.tau.cs.sw1.hw6;

public class Polynomial {
	private double[] coefficients;
	private int degree;
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		this.coefficients = new double[1];
		this.degree = 0;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.coefficients = coefficients;
		this.degree = coefficients.length - 1;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int newDegree = Math.max(this.getDegree(), polynomial.getDegree());
		double[] newCoefficients = new double[newDegree + 1];
		for(int i = 0; i < newCoefficients.length; i++) {
			newCoefficients[i] = this.getCoefficient(i) + polynomial.getCoefficient(i);
		}
		Polynomial p = new Polynomial(newCoefficients); 
		return p;
		
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] newCoefficients = new double[this.getDegree() + 1];
		for(int i = 0; i < newCoefficients.length; i++) {
			newCoefficients[i] = a*this.getCoefficient(i);
		}
		Polynomial p = new Polynomial(newCoefficients);
		return p;
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		return this.degree;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if(this.degree < n) {
			return 0;
		}
		return this.coefficients[n];
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if(this.degree < n) {
			double[] newCoefficients = new double[n+1];
			for(int i = 0; i <= getDegree(); i++) {
				newCoefficients[i] = this.coefficients[i];
			}
			this.coefficients = newCoefficients;
			this.degree = n;
		}
		this.coefficients[n] = c;
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		double[] newCoefficients =new double[this.getDegree()];
		for(int i = 0; i < newCoefficients.length; i++) {
			newCoefficients[i] = (i+1)*this.getCoefficient(i+1);
		}
		Polynomial deriv = new Polynomial(newCoefficients);
		return deriv;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		double result = 0;
		for(int i = 0; i <= this.getDegree();i++) {
			result += this.getCoefficient(i)*Math.pow(x, i);
		}
		return result;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial firstDeriv = this.getFirstDerivation();
		if(firstDeriv.computePolynomial(x) != 0) {
			return false;
		}
		Polynomial secondDeriv = firstDeriv.getFirstDerivation();
		if(secondDeriv.computePolynomial(x) != 0 ) {
			return true;
		}
		return false;
	}
	
	
	
	

    
    

}
