package il.ac.tau.cs.sw1.ex8.wordsRank;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	private Map<String, IHistogram<String>> files = new HashMap<>();
	public static final int UNRANKED_CONST = 30;
	
	

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {
		//This code iterates over all the files in the folder. add your code wherever is needed

		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				IHistogram<String> f = new HashMapHistogram<String>();
				try {
					f.addAll(FileUtils.readAllTokens(file));
				} catch (IOException e) {
					continue;
				}
				files.put(file.getName(), f);
			}
		}
	}
	
  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		if(!files.containsKey(filename)) {
			throw new FileIndexException("file not in the folder");
		}
		IHistogram<String> fHist = this.files.get(filename);
		return fHist.getCountForItem(word.toLowerCase()); //replace this with the actual returned value
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		word = word.toLowerCase();
		if(!files.containsKey(filename)) {
			throw new FileIndexException("file not in the folder");
		}
		if(this.files.get(filename).getCountForItem(word) == 0) {
			return this.files.get(filename).getItemsSet().size() + UNRANKED_CONST;
		}
		Iterator<String> ranks = this.files.get(filename).iterator();
		int rank = 0;
		int i = 1;
		while(ranks.hasNext()) {
			String s = ranks.next();
			if(word.equals(s)) {
				rank = i;
			}
			i++;
		}	
		return rank; 
	}
	
	private RankedWord getRankWord(String word) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		for(String filename : this.files.keySet()) {
			try {
				map.put(filename, this.getRankForWordInFile(filename, word.toLowerCase()));
			} catch (FileIndexException e) {
				continue;
			}
		}
		RankedWord rankWord = new RankedWord(word.toLowerCase() , map);
		return rankWord;
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		return this.getRankWord(word.toLowerCase()).getRankByType(rankType.average); //replace this with the actual returned value
	}
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k){
		List<String> words = new ArrayList<String>();
		for(IHistogram<String> hist : this.files.values()) {
			for(String word : hist.getItemsSet()) {
				if(getAverageRankForWord(word) < k) {
					words.add(word);
				}
			}
		}
		
		return words;
	}
	
	public List<String> getWordsWithMinRankSmallerThanK(int k){
		List<String> words = new ArrayList<String>();
		for(IHistogram<String> hist : this.files.values()) {
			for(String word : hist.getItemsSet()) {
				if(getRankWord(word).getRankByType(rankType.min) < k) {
					words.add(word);
				}
			}
		}
		return words;
	}
	
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		List<String> words = new ArrayList<String>();
		for(IHistogram<String> hist : this.files.values()) {
			for(String word : hist.getItemsSet()) {
				if(getRankWord(word).getRankByType(rankType.max) < k) {
					words.add(word);
				}
			}
		}	
		return words;
	}

}
