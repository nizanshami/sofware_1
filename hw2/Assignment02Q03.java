package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfEven = 0;
		int n = -1;
		numOfEven = 1;
		String s = "1 1 2 ";
		n = Integer.parseInt(args[0]);
		int prevprev = 1;
		int prev = 1;
		int curr = 2;
		for(int i = 3;i < n;i++) {
			prevprev = prev;
			prev = curr;
			curr = prev + prevprev;
			if(curr % 2 == 0) {numOfEven++;}
			s += Integer.toString(curr) + " ";
		}
		
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		System.out.println(s);
		System.out.println("The number of even numbers is: "+numOfEven);

	}
	

}
