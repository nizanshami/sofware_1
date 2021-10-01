package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	public static String[] scanVocabulary(Scanner scanner) { // Q - 1
		String[] words = new String[3000];
		int i = 0;
		while(scanner.hasNext()) {
			String s = scanner.next().toLowerCase();
			if(s.length() > 1 && onlyLetter(s) && !contains(words, s)) {
				words[i] = s;
				i++;
			}
			
		}
		int txtLen = i;
		i = 0;
		String[] result = new String[txtLen];
		for(int j = 0; j < txtLen; j++) {
			result[j] = words[j];
		}
		Arrays.sort(result);
		return result;
	}

	private static boolean onlyLetter(String s) {
		for(int i = 0;i < s.length();i++) {
			char c = s.charAt(i);
			if(!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}

	public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
		int count = 0;
		for(char c : puzzle) {
			if(c == '_') {
				count++;
			}
		}
		return count;
	}

	public static String getRandomWord(String[] vocabulary, Random generator) { // Q - 3
		int i = generator.nextInt(vocabulary.length);
		return vocabulary[i];
	}

	public static boolean checkLegal(String word, char[] puzzle) { // Q - 4
		int hiddinLetters = countHiddenInPuzzle(puzzle);
		if(hiddinLetters < 1 || hiddinLetters >= word.length()) {//at list one letter must appear/be hidden
			return false;
		}
		for(int i = 0;i < word.length();i++) {
			if(word.charAt(i) != puzzle[i]) {//check if all appearances of hidden letter are hidden 
				if(contains(puzzle, word.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean contains(char[] puzzle, char letter) {
		for(char c : puzzle) {
			if(c == letter) {
				return true;
			}
		}
		return false;
	}
	private static boolean contains(String[] array, String word) {
		for(String s : array) {
			if(s == null) {
				break;
			}
			if(s.equals(word)) {
				return true;
			}
		}
		return false;
	}


	public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
	    char[] puzzleCandidate = new char[word.length()];
	    for(int i = 0;i < word.length();i++) {
	    	if(generator.nextFloat() <= prob) {
	    		puzzleCandidate[i] = '_'; 
	    	}else {
	    		puzzleCandidate[i] = word.charAt(i);
	    	}
	    }
		return puzzleCandidate;
	}
	
	public static char[] getRandomPuzzle(String word, double prob, Random generator) { // Q - 6
		for(int i = 0; i < 1000;i++){
			char[] puzzle = getRandomPuzzleCandidate(word, prob, generator);
			if(checkLegal(word, puzzle)) {
				return puzzle;
			}
		} 
		throwPuzzleGenerationException();
		
		return null;//never gets here
	}

	public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
		int count = 0;
		for(int i = 0;i < solution.length();i++) {
			if(solution.charAt(i) == guess && puzzle[i] == '_') {
				puzzle[i] = guess;
				count++;
			}
		}
		return count;
	}

	public static char[] getHelp(String solution, char[] puzzle) { // Q - 8
		for(int i = 0; i < puzzle.length;i++) {
			if(puzzle[i] == '_') {
				applyGuess(solution.charAt(i), solution, puzzle);
				break;
			}
		}
		return puzzle;
	}

	public static void main(String[] args) throws Exception { // Q - 9
		if(args.length == 0) {
			System.out.println("file puth missing");
		}else {
			// Uncomment only one of the generators:
			Random generator = new MyRandom(new int[]{0,1,2,3,4,5},new float[]{0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1.0f});
		    //Random generator = new MyRandom(getRrandomIntArr(vocabularySize), getRandomFloatArr());
		    //Random generator = new Random ();
			File f = new File(args[0]);
			Scanner fs = new Scanner(f);
			String[] vocabulary = scanVocabulary(fs);
			int vocabularySize = vocabulary.length; // Replace -1 with size of vocabulary.
			printReadVocabulary(args[0], vocabularySize);
			printGameStageMessage();
			Scanner in = new Scanner(System.in);
			printEnterHidingProbability();
			float prob = in.nextFloat();
			boolean isChosen = false;
			char[] puzzle =  {};
			String word = getRandomWord(vocabulary, generator);
			while(!isChosen) {
				puzzle = getRandomPuzzle(word, prob, generator);
				printPuzzle(puzzle);
				boolean isLegalImput = false;
				while(!isLegalImput) {
					printReplacePuzzleMessage();
					String ans = in.next();
					if(ans.equals("yes")) {
						isLegalImput = true;
						word = getRandomWord(vocabulary, generator);
					}
					if(ans.equals("no")) {
						isChosen = true;
						isLegalImput = true;
					}
				}
			}
			printGameStageMessage();
			int guessNum = countHiddenInPuzzle(puzzle) + 3;
			while(guessNum > 0 && countHiddenInPuzzle(puzzle) > 0) {
				printEnterYourGuessMessage();
				char guess = in.next().charAt(0);
				if(guess != 'H') {
					int match = applyGuess(guess, word, puzzle);
					if(match > 0) {
						if(countHiddenInPuzzle(puzzle) == 0) {
							break;
						}
						printCorrectGuess(guessNum);
						printPuzzle(puzzle);
					}else {
						printCorrectGuess(guessNum);
						printPuzzle(puzzle);
					}
				}else {
					getHelp(word, puzzle);
					printPuzzle(puzzle);
				}
				guessNum--;
			}
			if(countHiddenInPuzzle(puzzle) == 0) {
				printWinMessage();
			}else {
				printGameOver();
			}
			in.close();
			fs.close();
		}
	}

	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	private static float[] getRandomFloatArr() {
		Double[] doubleArr = new Double[] { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 };
		List<Double> doubleList = Arrays.asList(doubleArr);
		Collections.shuffle(doubleList);
		double[] unboxed = doubleList.stream().mapToDouble(Double::doubleValue).toArray();

		// cast double array to float array
		float[] floatArr = new float[unboxed.length];
		for (int i = 0; i < unboxed.length; i++) {
			floatArr[i] = (float) unboxed[i];
		}
		return floatArr;
	}

	private static int[] getRrandomIntArr(int vocabularySize) {
		
		if(vocabularySize<0) {
			throw new RuntimeException("Wrong use of getRandomIntArr(int vocabularySize)");
		}
		
		int i = 0;
		Integer[] intArr = new Integer[vocabularySize];
		while (i < vocabularySize) {
			intArr[i] = i;
			i++;
		}
		List<Integer> doubleList = Arrays.asList(intArr);
		Collections.shuffle(doubleList);
		int[] unboxed = doubleList.stream().mapToInt(Integer::intValue).toArray();
		return unboxed;
	}

	public static void throwPuzzleGenerationException() {
		throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
	}

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterHidingProbability() {
		System.out.println("Enter your hiding probability:");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printReplacePuzzleMessage() {
		System.out.println("Replace puzzle?");
	}

	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

}