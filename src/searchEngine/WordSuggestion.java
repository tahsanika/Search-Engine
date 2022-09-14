package searchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSuggestion {
	public static void ShowSuggestedWord(String CurrentWord)
	{
		Iterable<String> GetSuggestedWords = Searching.bigtree.prefixMatch(CurrentWord.toLowerCase());
		List<String> SuggestedWordList = new ArrayList<String>();
		for(String SuggestedWord : GetSuggestedWords)
		{
			SuggestedWordList.add(SuggestedWord);
		}
		if (SuggestedWordList.size() > 0)
		{
			System.out.println("We cannot find your word. Do you mean:");
			int cnt = 1;
			for(String SuggestedWord : SuggestedWordList)
			{
				System.out.println(cnt+" - "+SuggestedWord);
				cnt++;
			}
			System.out.println("Choose a number to change your search or enter for continue.");
			Scanner scan = new Scanner(System.in);
			String s = scan.nextLine();
			if (!s.equals(""))
			{
				String SelectedWord = "";
				try
				{
					if (Integer.parseInt(s) <= SuggestedWordList.size())
					{
						SelectedWord = SuggestedWordList.get(Integer.parseInt(s) - 1);
						Searching.DoSearch(SelectedWord);
					}
					else
					{
						System.out.println("Incorrect Number Entered. Operation is terminated.");
					}
				}
				catch(Exception ex)
				{
					System.out.println("Incorrect Number Entered. Operation is terminated.");
				}
			}
		}
		else
		{
			SpellChecker.CheckForSpelling(CurrentWord);
		}
	}
}
