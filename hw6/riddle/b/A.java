package il.ac.tau.cs.sw1.riddle.b;

/**
 * Complete the code of A's methods without changing B and C.
 */
public class A {

	private B b;

	public A(B b) {
		this.b = b;
	}

	public static void printA(B b) {
		b.methodB(b);
	}

	public void printA2() {
		 this.b.foo(this.b);
	}

	public static void printA3(A a) {
		a.b.methodB(a.b);
	}
	
}
