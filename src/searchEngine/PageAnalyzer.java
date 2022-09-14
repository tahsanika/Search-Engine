package searchEngine;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import GeneralLibrary.Queue;
import GeneralLibrary.TST;


public class PageAnalyzer {
	
	public static int AnalyzeThePage(String PageContent,String Url,int PageCounter)
	{
		TST<Integer> tst = new TST<Integer>();

		
		//Extract All The Tokens
		StringTokenizer StringParser = new StringTokenizer(PageContent);
	    String[] AllTokens = new String[StringParser.countTokens()];
	    int TokenIndex = 0;
	    do
	    {
	    	AllTokens[TokenIndex++] = StringParser.nextToken();
	    }
	    while(StringParser.hasMoreTokens());
	    //System.out.println(TokenIndex);
    
	    //Count for all tokens Occurences
        for (int i = 0; i < AllTokens.length; i++) {
            //String key = In.readString();
        	AllTokens[i] = AllTokens[i].replaceAll("[\"|'|,|.|;|]", "");
        	if (AllTokens[i].length()>2 && AllTokens[i].length() < 20)
        	{
        		Integer Number = tst.get(AllTokens[i]);
        	//try
        	//{
        		if (Number == null)
        		{
        			tst.put(AllTokens[i], 1);
        		}
        		else
        		{
        			tst.put(AllTokens[i], (Number + 1));
        		}
        	}
        	//}
        	//catch(StackOverflowError e)
        	//{
        		
        	//}
        }
        
        //Construct the structured array and send to Search Analyzer
        List<FoundObject> ObjectList = new ArrayList<FoundObject>();
        //for (int i = 0; i < AllTokens.length; i++) {
        for (String Key:tst.keys())
        {	
        	if (Key.length()>2 && Key.length() < 20)
        	{
        			FoundObject NewObjectFound = new FoundObject();
        			NewObjectFound.PageID = PageCounter;
        			NewObjectFound.PageUrl = Url;
        			NewObjectFound.Word = Key;
        			NewObjectFound.WordCount = tst.get(Key);
        			ObjectList.add(NewObjectFound);
        	}
        	
        	/*if (AllTokens[i].length()>2 && AllTokens[i].length() < 20)
        	{
        		
        		if (tst.contains(AllTokens[i]))
        		{
        			FoundObject NewObjectFound = new FoundObject();
        			NewObjectFound.PageID = PageCounter;
        			NewObjectFound.PageUrl = Url;
        			NewObjectFound.Word = AllTokens[i];
        			NewObjectFound.WordCount = tst.get(AllTokens[i]);
        			ObjectList.add(NewObjectFound);
        		}
        	}*/
        }
        Searching.ConstructSearchStructures(ObjectList);
        return TokenIndex;
	}
}
