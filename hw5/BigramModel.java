package il.ac.tau.cs.sw1.ex5;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	private int[] wordcount;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
		
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		String[] a = new String[MAX_VOCABULARY_SIZE];
		FileReader fr = new FileReader(fileName);
		BufferedReader in = new BufferedReader(fr);
		String curLine = in.readLine();
		int counter = 0;
		while(curLine != null) {
			String[] words = curLine.split("\\s+");
			for(String word : words) {
				word = word.toLowerCase();
				if(checkLegel(word) && !ArrayContains(a, word)) {
					a[counter] = clean(word);
					counter++;
				}
			}
			curLine = in.readLine();
		}
		String[] result = new String[counter];
		for(int i =0;i < result.length;i++) {
			result[i] = a[i];
		}
		in.close();
		return result;
	}
	
	private boolean ArrayContains(String[] a, String word) {
		for (String s : a) {
			if(s == null) {
				continue;
			}
			if(s.equals(word)) {
				return true;
			}
		}
		return false;
	}
	private int wordAt(String[] a, String word) {
		for (int i = 0; i < a.length;i++) {
			if(a[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}



	private String clean(String word) {
		if(IsNumber(word)) {
			return SOME_NUM;
		}
		return word;
	}
	


	private boolean IsNumber(String word) {
		char c;
		for(int i = 0; i < word.length();i++) {
			c = word.charAt(i);
			if(c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}



	private boolean checkLegel(String word) {
		int digits = 0;
		for(int i = 0; i < word.length();i++) {
			char c = word.charAt(i);
			if(c >= 'a' && c <= 'z') {
				return true;
			}
			if(c >= '0' && c <= '9') {
				digits++;
			}
		}
		return digits == word.length();
	}



	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int n = vocabulary.length;
		int[][] countsArray = new int[n][n];
		wordcount = new int[n];
		FileReader fr = new FileReader(fileName);
		BufferedReader in = new BufferedReader(fr);
		String line = in.readLine();
		while( line != null) {
			String[] words = line.split(" ");
			for(int i = 0; i < words.length - 1; i++) {
				String word1 = words[i].toLowerCase();
				String word2 = words[i+1].toLowerCase();
				if(checkLegel(word1)) {
					int index = wordAt(vocabulary, word1);
					if(index != -1) {
						wordcount[index] += 1;
					}
				}
				if(!checkLegel(word1) || !checkLegel(word2)) {
					continue;
				}
				int x = wordAt(vocabulary, word1);
				int y = wordAt(vocabulary, word2);
				if(x != -1 && y != -1) {
					countsArray[x][y]++;
				}
			}
			String s = words[words.length - 1];
			if(wordAt(vocabulary,s) != -1) {
				wordcount[wordAt(vocabulary,s)] += 1;
			}
			line = in.readLine();
		}
		in.close();
		return countsArray;

	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		File fvoc = new File(fileName + VOC_FILE_SUFFIX + ".txt" );
		File fcount = new File(fileName + COUNTS_FILE_SUFFIX + ".txt");
		 if (!fvoc.exists()) {
	            fvoc.createNewFile();
		 }
		 if (!fcount.exists()) {
	            fcount.createNewFile();
		 }
		 FileWriter fwvoc = new FileWriter(fvoc.getAbsoluteFile());
         BufferedWriter bwvoc = new BufferedWriter(fwvoc);
         bwvoc.write(Integer.toString(mVocabulary.length));
         bwvoc.write(" words");
         bwvoc.newLine();
         for(int i = 0; i < mVocabulary.length;i++) {
        	 bwvoc.write(Integer.toString(i) + ",");
             bwvoc.write(mVocabulary[i]);
             bwvoc.newLine();
         }
         bwvoc.close();
         FileWriter fwcount = new FileWriter(fcount.getAbsoluteFile());
         BufferedWriter bwcount = new BufferedWriter(fwcount);
         for(int i = 0; i < mBigramCounts.length;i++) {
        	 for(int j = 0; j < mBigramCounts[i].length; j++) {
        		 if(mBigramCounts[i][j] != 0) {
        			 String s = String.format("%1$d,%2$d:%3$d", i, j, mBigramCounts[i][j]);
        			 bwcount.write(s);
        			 bwcount.newLine();
        		 }
        		 
        	 }
         }
         bwcount.close();
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		File fvoc = new File(fileName + VOC_FILE_SUFFIX);
		File fcount = new File(fileName + COUNTS_FILE_SUFFIX);
		Scanner invoc = new Scanner(fvoc);
		String[] swordNum = invoc.nextLine().split(" ");
		int wordNum = Integer.parseInt(swordNum[0]);
		String[] voc = new String[wordNum];
		while(invoc.hasNextLine()) {
			String[] line = invoc.nextLine().split(",");
			voc[Integer.parseInt(line[0])] = line[1];
		}
		mVocabulary = voc;
		invoc.close();
		Scanner incount = new Scanner(fcount);
		int[][] count = new int[wordNum][wordNum];
		while(incount.hasNext()) {
			String pos = incount.next();
			count[pos.charAt(0)-'0'][pos.charAt(2)-'0'] = pos.charAt(4) - '0';
		}
		mBigramCounts = count;
		incount.close();
	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		return wordAt(this.mVocabulary, word);
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int x = getWordIndex(word1);
		int y = getWordIndex(word2);
		if( x != -1 && y != -1) {
			return this.mBigramCounts[x][y];
		}
		return 0;
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int max = 0;
		int index = getWordIndex(word);
		for(int i : this.mBigramCounts[index]) {
			if(i > max) {
				max = i;
			}
		}
		if(max != 0) {
			for(int j = 0; j < this.mBigramCounts[index].length;j++) {
				if(this.mBigramCounts[index][j] == max) {
					return this.mVocabulary[j];
				}
			}
		}
		return null;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String[] words = sentence.split(" ");
		for(int i = 0; i < words.length - 1;i++) {
			String word1 = words[i];
			String word2 = words[i+1];
			if(getBigramCount(word1, word2) == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		if(isZeroVector(arr1) || isZeroVector(arr2)) {
			return -1;
		}
		double norm1 = norm(arr1);
		double norm2 = norm(arr2);
		double xy = dotProduct(arr1,arr2);
		return xy/(norm1*norm2);
	}

	
	private static double dotProduct(int[] arr1, int[] arr2) {
		int result = 0;
		for(int i = 0; i < arr1.length; i++) {
			result += arr1[i]*arr2[i];
		}
		return result;
	}



	private static double norm(int[] arr1) {
		double result = 0; 
		for(int i : arr1) {
			result += Math.pow(i, 2);
		}
		result = Math.sqrt(result);
		return result;
	}



	private static boolean isZeroVector(int[] arr1) {
		for(int i : arr1) {
			if(i != 0) {
				return false;
			}
		}
		return true;
	}



	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		int x = getWordIndex(word);
		double max[] = new double[2];
		for(int i = 0; i < mVocabulary.length; i++) {
			if(i != x) {
				double d = calcCosineSim(mBigramCounts[x], mBigramCounts[i]);
				if(d > max[0]) {
					max[0] = d;
					max[1] = i;
				}
			}
		}
		int wordIndex = (int)max[1];
		return mVocabulary[wordIndex];
	}

	/*
	 * @pre: word is a String
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the number of word's occurrences in the text.
	 */
	public int getWordCount(String word){ //  Q - 11	
		int i = getWordIndex(word);
		if(i == -1) {
			return 0;
		}
		return this.wordcount[i];
	}
	
}


