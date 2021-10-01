package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {

	public static String findSortedSequence(String str) {
		String[] a = str.split(" ");
		if(a.length == 0 || str == "") {
			return "";
		}
		String seq = "";
		String max = "";
		for(int i = 0; i < a.length;i++) {
			seq = a[i];
			for(int j = i + 1; j < a.length;j++) {
				if(a[j-1].compareTo(a[j]) <= 0) {
					seq += " " + a[j];
				}else {
					break;
				}		
			}
			if(max.split("").length <= seq.split("").length) {
				max = seq;
			}
		}
		return max; 

	}

	public static boolean isAnagram(String a, String b) {
		a = a.toLowerCase();
		b =  b.toLowerCase();
		a = a.replaceAll("\\s+","");
		b = b.replaceAll("\\s+","");
		char[] A = a.toCharArray();
		char[] B = b.toCharArray();
		Arrays.sort(A);
		Arrays.sort(B);
		
		return Arrays.equals(A, B); //Replace this with the correct returned value
	}
	
	public static boolean isEditDistanceOne(String a, String b){
		int len_a = a.length();
		int len_b = b.length();
		if(Math.abs(len_b - len_a) > 1) {
			return false;
		}
		int count = 0;// count edits
		int i = 0;int j = 0;
		while(i < len_a && j < len_b) {
			if(a.charAt(i) != b.charAt(j)) {
				if(count == 1) {
					return false;
				}
				if(len_a > len_b) {
					i++;
				}else if(len_a < len_b) {
					j++;
				}else {
					i++;j++;
				}
				count++;
			}else {
				i++;j++;
			}			
		}
		return true;
	}
}
