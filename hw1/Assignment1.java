
public class Assignment1 {

	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);
		if (x < 1 || y < 1 || z < 1) {
			System.out.println("Invalid input!");
		} else {
			if (x + y < z || x + z < y || y + z < x) {
				System.out.println("The input (" + x + ", " + y + ", " + z + ") does not define a valid triangle!");
			} else {
				int leg1;
				int leg2;
				int m;
				if (x > y) {
					leg1 = y;
					m = x;
				} else {
					leg1 = x;
					m = y;
				}
				if (m > z) {
					leg2 = z;
				} else {
					leg2 = m;
					m = z;
				}
				if (leg1 * leg1 + leg2 * leg2 == m * m) {
					System.out.println("The input (" + x + ", " + y + ", " + z + ") defines a valid right triangle!");
				} else {
					System.out.println("The input (" + x + ", " + y + ", " + z + ") defines a valid triangle!");
				}
			}
		}
	}
}
