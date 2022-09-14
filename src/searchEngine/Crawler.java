package searchEngine;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import GeneralLibrary.*;

public class Crawler {
	private static int CrawlerIndex = 0;
	private static List<String> ListOfUrlToCrawl;
	private static List<String> ListOfCrawledUrl;
	private static int TotalWords = 0;
	private static Integer MaximumNumberOfUrls;
	//By Calling this method the crawler will start its operation
	public static void ServiceStart(String StartUrl, Integer maximumNumberOfUrls)
	{
		System.out.println("Crawling Started ...");
		MaximumNumberOfUrls = maximumNumberOfUrls;
		IsRunning = true;
		ListOfUrlToCrawl = new ArrayList<String>();
		ListOfCrawledUrl = new ArrayList<String>();
		ListOfUrlToCrawl.add(StartUrl);
		CrawlerIndex = 0;
		GetPageContent(StartUrl);
		System.out.println("Result :");
		System.out.println("Page Crawled : "+PageCounter+" Links Found : "+ListOfUrlToCrawl.size() +" Words Found : "+TotalWords);
		System.out.println();
	}
	private static void GetPageContent(String Url)
	{
		if (LinkIsCrawledBefore(Url))
		{
			CrawlerIndex++;
			if (CrawlerIndex<ListOfUrlToCrawl.size() && PageCounter<MaximumNumberOfUrls)
			{
				String CurrentLink = ListOfUrlToCrawl.get(CrawlerIndex);
				GetPageContent(CurrentLink);
			}	
		}
		else
		{
			String PageContent = "";
			try
			{
				PageContent = new In(Url).readAll();
			}
			catch(Exception e)
			{
				//
			}
			
			if (PageContent!="")
			{
			String TextPageContent = SaveThePageTextContent(PageContent,Url);
			TotalWords += PageAnalyzer.AnalyzeThePage(TextPageContent,Url,PageCounter);
			SearchForLinksInPage(PageContent);
			}
			ListOfCrawledUrl.add(Url);
			//System.out.printf("\r%s","Page Crawled : "+PageCounter+" Links Found : "+ListOfUrlToCrawl.size() +" Words Found : "+TotalWords);
			if (ListOfCrawledUrl.size() == ListOfUrlToCrawl.size())
			{
				//All URLs indexed and There is no more URL
				ServiceStop();
				return;
			}
			CrawlerIndex++;
			if (CrawlerIndex<ListOfUrlToCrawl.size() && PageCounter<MaximumNumberOfUrls)
			{
				String CurrentLink = ListOfUrlToCrawl.get(CrawlerIndex);
				GetPageContent(CurrentLink);
			}	
		}
		
	}
	private static Boolean LinkIsCrawledBefore(String CurrentLink)
	{
		for(String Url : ListOfCrawledUrl)
		{
			if (Url == CurrentLink)
			{
				return true;
			}
		}
		return false;
	}
	private static void SearchForLinksInPage(String PageContent)
	{
		String pattern = "(http://|https://)[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]*";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(PageContent);
	    while (m.find( )) {
	    	String NewLinkFound = m.group(0);
	    	//System.out.println(NewLinkFound);
	    	if (IsValidFile(NewLinkFound))
	    	ListOfUrlToCrawl.add(NewLinkFound);
	    }
	}
	private static boolean IsValidFile(String NewLinkFound) {
		// TODO Auto-generated method stub
		if (!NewLinkFound.contains(".ico") &&
				!NewLinkFound.contains(".css") &&
				!NewLinkFound.contains(".dtd") &&
				!NewLinkFound.contains(".xml") &&
				!NewLinkFound.contains(".js") &&
				!NewLinkFound.contains(".swf") &&
				!NewLinkFound.contains(".pdf") &&
				!NewLinkFound.contains(".jpg") &&
				!NewLinkFound.contains(".png") &&
				!NewLinkFound.contains(".bmp") &&
				!NewLinkFound.contains(".exe") &&
				!NewLinkFound.contains(".zip") &&
				!NewLinkFound.contains(".ttf") &&
				!NewLinkFound.contains(".woff") &&
				!NewLinkFound.contains(".woff2") &&
				!NewLinkFound.contains(".eot") &&
				!NewLinkFound.contains(".svg") && 
				!NewLinkFound.contains("w3.org") && 
				!NewLinkFound.contains("rdfs.org") && 
				!NewLinkFound.contains("xmlns.com") && 
				!NewLinkFound.contains("ogp.me"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	static int PageCounter = 0;
	private static String SaveThePageTextContent(String PageContent,String Url)
	{
		try
		{
			org.jsoup.nodes.Document doc = Jsoup.parse(PageContent);
			//org.jsoup.nodes.Document doc = Jsoup.connect("http://blogs.windsorstar.com/news/woman-to-be-charged-with-child-abandonment-after-infants-found-in-apartment-stairwell").get();
			String text = doc.text();
			PrintWriter out = new PrintWriter(GetPath("/PageContent/"+(++PageCounter)+".txt"));
			System.out.println(PageCounter+" : "+Url);
			out.println(text);
			out.close();	
			return text;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error Parsing : "+Url;
		}
	}
	private static Boolean IsRunning;
	public static void ServiceStop()
	{
		IsRunning = false;
	}
	public static String GetPath(String filename)
	{
		   String envRootDir = System.getProperty("user.dir");
		   return envRootDir+"/bin/SearchEngine/"+filename;	
	}
}
