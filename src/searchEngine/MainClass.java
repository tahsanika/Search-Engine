package searchEngine;

import java.util.List;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpellChecker.InitializeSpellChecker();
		Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("please input a word or a command: ");
            String s = scan.nextLine();
            if(s.equals("quit")) {
                break;
            } else if(s.equals("help")) {
            	System.out.println("Commands: ");
            	System.out.println("start WebsiteURL MaximumPageToCrawl > Starts crawlling the given url");
            	System.out.println("suggest 'an incomplete word' > Shows all the suggested word according to the given phrase");
            	System.out.println("quit > Exits the application");
            	System.out.println("or you can type a word for searching");
            } else if(s.startsWith("start")) {
            	String[] Arguments = s.split("\\s+");
            	if (Arguments.length==3)
            	{
            		String StartUrl = Arguments[1];
            		Integer MaximumNumberOfUrls = Integer.parseInt(Arguments[2]);
            		Crawler.ServiceStart(StartUrl,MaximumNumberOfUrls);
            	}
            	else
            	{
            		System.out.println("Invalid Argument.");
            	}
                //break;
            } else if(s.startsWith("suggest")) {
            	String[] Arguments = s.split("\\s+");
            	if (Arguments.length==2)
            	{
            		String Word = Arguments[1];
            		WordSuggestion.ShowSuggestedWord(Word);
            	}
            	else
            	{
            		System.out.println("Invalid Argument.");
            	}
                //break;
            } else if (s.equals("test")) {
            	DisplayResult.TestDisplayResult();
            } else {
            	//SpellChecker.CheckForSpelling(s);
               Searching.DoSearch(s);
            }

        }
	}

}
