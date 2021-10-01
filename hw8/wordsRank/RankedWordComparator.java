package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{
	private rankType rType;
	public RankedWordComparator(rankType cType) {
		this.rType = cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		if(o1.getRankByType(rType) > o2.getRankByType(rType)) {
			return 1;
		}
		if(o1.getRankByType(rType) < o2.getRankByType(rType)) {
			return -1;
		}
		return 0;
	}	
}
