package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q01 {

	public static void main(String[] args) {
		for(String arg : args) {
			char c = arg.charAt(0);
			if(c % 2 == 0 && c % 3 == 0) {
				System.out.println(c);
			}
		}
	}

}
