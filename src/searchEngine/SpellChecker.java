package searchEngine;

import java.util.ArrayList;
import java.util.List;

import GeneralLibrary.In;
import GeneralLibrary.SpellCheckerSort;

public class SpellChecker {
	
	private static String[] AllWords;
	public static void InitializeSpellChecker()
	{
		In FileReader =	new In(Crawler.GetPath("WordsDictionary.txt"));
		AllWords = FileReader.readAllLines();
	}
	
	public static void CheckForSpelling(String CurrentWord)
	{
		List<SpecllCheckerModel> WordsToSuggest = new ArrayList<SpecllCheckerModel>();
		for(String str:AllWords)
		{
			
			int EditDistanceValue = editDistance(CurrentWord,str);
			if (EditDistanceValue > 2  || EditDistanceValue > (CurrentWord.length() / 2))
			{
				//it has too much errors and we ignore it
			}
			else
			{
				//System.out.println("Data Found : "+str);
				SpecllCheckerModel mdl = new SpecllCheckerModel();
				mdl.Word = str;
				mdl.EdValue = EditDistanceValue;
				WordsToSuggest.add(mdl);
			}
		}
		System.out.println();
		SpellCheckerSort.mergeSort(WordsToSuggest);
		if (WordsToSuggest.size()>0)
		{
			if (WordsToSuggest.get(0).EdValue == 0)
			{
				//We dont have error mistake
				System.out.println("Your search does not match ant result.");
			}
			else
			{
				System.out.println("Your search does not match any result. Do you mean:");
				for(SpecllCheckerModel scm : WordsToSuggest)
				{
					System.out.println(scm.Word);
				}
				System.out.print("Correct your entry and ");
			}
		}
		else
		{
			System.out.println("Your search does not match ant result.");
		}
	}
	
	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int[][] dp = new int[len1 + 1][len2 + 1];	// Edit distance table

		for (int i = 0; i <= len1; i++) { dp[i][0] = i;	}
		for (int j = 0; j <= len2; j++) { dp[0][j] = j; }
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = Math.min(replace, insert);
					min = Math.min(delete, min);
					dp[i + 1][j + 1] = min;
				}
			}
		}
		return dp[len1][len2];
	}

}
