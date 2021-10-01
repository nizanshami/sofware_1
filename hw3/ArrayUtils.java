package il.ac.tau.cs.sw1.hw3;

public class ArrayUtils {

	public static int[][] transposeMatrix(int[][] m) {
		int n = m.length;
		int t = m[0].length;
		int[][] result = new int[t][n];
		for(int i = 0; i < t; i++) {
			for(int j = 0; j < n; j++) {
				result[i][j] = m[j][i];
			}
		}
		return result; 

	}

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		int n = array.length;
		boolean r = direction == 'R';
		if ((direction != 'L' && direction != 'R') || move == 0){
			return array;
			}
		if(move < 0) {
			r = !r;
			move = -move;
		}
		if (move > n) {
			move = move%n;
		}
		if(!r) {
			move = n-move;
		}
		return shift(array, move, r, n); 

	}
	private static int[] shift(int[] array, int move,boolean r, int len) {
		int[] tmp = array.clone();
		for(int i = 0; i < array.length; i++) {
			array[(i+move) % len] = tmp[i] ;
			
		}	
		return array;
	}

	public static int alternateSum(int[] array) {
		int max = 0;
		for(int i = 0;i < array.length;i++) {
			int sum = 0;
			int sign = 1;
			for(int j = i; j < array.length;j++) {
				sum += array[j]*sign;
				if(sum > max) {
					max = sum;
				}
				sign *= -1;
			}
		}	
		return max; 

	}

	public static int findPath(int[][] m, int i, int j, int k) {
		if(i == j && k == 1) {
			return 1;
		}
		int counter = 0;
		int[] queue = new int[m.length];
		int[] queue_sen = {0,0,0};
		for(int item : queue) {
			item = -1;
		}
		enqeue(queue,queue_sen, i);
		while(queue_sen[2] != 0) {
			int v = deqeue(queue, queue_sen);
			if(v == j && counter == k) {
				return 1;
			}
			for(int e = 0;e < m[v].length;e++) {//step
				if(m[v][e] == 1 && e != v) {
					enqeue(queue, queue_sen, e);
				}
			}
			counter++;
			for(int r = 0; r < m.length;r++) {//mark v
				m[r][v] = 0;
			}
		}
		return 0; 

	}
	private static void enqeue(int[] queue, int[] sentinal, int item) {
		queue[sentinal[1]] = item;
		sentinal[0]++;
		sentinal[2]++;
	}
	private static int deqeue(int[] queue,int[] sentinal) {
		int i = queue[sentinal[1]];
		sentinal[1]++;
		sentinal[2]--;
		return i;
	}
}
