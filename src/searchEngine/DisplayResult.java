package searchEngine;

import java.util.ArrayList;
import java.util.List;

import GeneralLibrary.MergeSort;


public class DisplayResult {
	private static List<String> DataToPrint = new ArrayList<String>();
	public static void ShowResult(String WordToSearch, List<FoundObject> ListOfFoundObject)
	{
		//Result should be should be shown sorted by the word count (Page Rank)
		//Result should be displayed in the following format :
		Print("");
		Print("Searching For \""+WordToSearch+"\". Result:");
		Print("");
		MergeSort.mergeSort(ListOfFoundObject);
		int count = 0;
		
		for(FoundObject fo : ListOfFoundObject)
		{
			if (NotAlreadyListed(fo.PageUrl))
			{
				if (count<20)
				{
					DataToPrint.add(fo.PageUrl);
					count++;
					Print(fo.PageUrl);
					Print("Number of found words: "+fo.WordCount);
					Print("");
					Print("===========================");
					Print("");
				}
				else
				{
					break;
				}
			}
		}
	}
	
	private static Boolean NotAlreadyListed(String fo)
	{
		for(String a :DataToPrint)
		{
			if (a.equals(fo))
			{
				return false;
			}
		}
		return true;
	}
	
	public static void TestDisplayResult()
	{
		List<FoundObject> ListOfFoundObject = new ArrayList<FoundObject>();
		FoundObject fo = new FoundObject();
		fo.Word = "C";
		fo.WordCount = 3;
		ListOfFoundObject.add(fo);
		
		FoundObject fo1 = new FoundObject();
		fo1.Word = "A";
		fo1.WordCount = 1;
		ListOfFoundObject.add(fo1);
		
		FoundObject fo4 = new FoundObject();
		fo4.Word = "D";
		fo4.WordCount = 4;
		ListOfFoundObject.add(fo4);
		
		FoundObject fo5 = new FoundObject();
		fo5.Word = "D";
		fo5.WordCount = 4;
		ListOfFoundObject.add(fo5);
		
		FoundObject fo45 = new FoundObject();
		fo45.Word = "DD";
		fo45.WordCount = 6;
		ListOfFoundObject.add(fo45);
		
		FoundObject fo3 = new FoundObject();
		fo3.Word = "B";
		fo3.WordCount = 2;
		ListOfFoundObject.add(fo3);
		
		MergeSort.mergeSort(ListOfFoundObject);
		int count = 0;
		for(FoundObject fox : ListOfFoundObject)
		{
			if (count<20)
			{
				count++;
				Print(fox.Word);
				Print("Number of found words: "+fox.WordCount);
				Print("");
				Print("===========================");
				Print("");
			}
		}
	}
	
	public static void Print(String str)
	{
		System.out.println(str);
	}
}
