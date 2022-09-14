package searchEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GeneralLibrary.TST;

public class Searching {
	
	
	public static TST<Integer> bigtree = new TST<Integer>();
	public static HashMap<String, List<FoundObject>> allwords;
	
	public static void ConstructSearchStructures(List<FoundObject> ObjectList)
	{
		if (allwords==null)
			allwords = new HashMap<String, List<FoundObject>>();
		
		for(FoundObject f:ObjectList) {
			//Here we create the big TST including all the words for our word suggestion
			if (!bigtree.contains(f.Word.toLowerCase()))
			{
				bigtree.put(f.Word.toLowerCase(),f.PageID);
			}
			System.out.println("Adding Word To TST : "+f.Word +" : "+f.WordCount);
			String lowercaseword = f.Word.toLowerCase();
			if(allwords.containsKey(lowercaseword)) {
				if(!AlreadyAdded(allwords.get(lowercaseword),f))
					allwords.get(lowercaseword).add(f);
			}
			else {
				List<FoundObject> newlist = new ArrayList<FoundObject>();
				newlist.add(f);
				allwords.put(lowercaseword,newlist);
			}
		}
	}
	
	private static Boolean AlreadyAdded(List<FoundObject> datalist,FoundObject obj)
	{
		for(FoundObject f:datalist)
		{
			if (f.PageUrl == obj.PageUrl)
			{
				return true;
			}
		}
		return false;
	}
	
	public static void DoSearch(String TexttoSearch)
	{
		String wordsearch = TexttoSearch.toLowerCase();
		if(allwords.containsKey(wordsearch)) {
			DisplayResult.ShowResult(TexttoSearch,allwords.get(wordsearch));
		}
		else
		{
			WordSuggestion.ShowSuggestedWord(wordsearch);
		}
	}
	
	private static void Test()
	{
		for(String str : allwords.keySet())
		{
			System.out.println(str);
		}
	}
}
