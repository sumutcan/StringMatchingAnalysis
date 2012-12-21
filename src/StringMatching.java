import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Hashtable;



public class StringMatching {

	private String filePath;
	private String inputString;
	private String pattern;
	private Hashtable<String, Integer> comparisonCounts;
	private Hashtable<String, Long> runtimes;
	private Hashtable<Character, Integer> shiftTable;
	
	public StringMatching(String filePath) throws IOException
	{
		this.filePath = filePath;
		shiftTable = new Hashtable<Character,Integer>();
		
		comparisonCounts = new Hashtable<String,Integer>();
		comparisonCounts.put("bruteForce",0);
		comparisonCounts.put("horspool",0);
		comparisonCounts.put("boyerMoore",0);
		
		runtimes = new Hashtable<String, Long>();
		runtimes.put("bruteForce",(long)0.0);
		runtimes.put("horspool",(long) 0.0);
		runtimes.put("boyerMoore",(long)0.0);
		
		parseInputFile();
		
	}
	
	private void createShiftTable() throws IOException {
		
		shiftTable.put('_', pattern.length());
		for (int i = 65; i<91; i++)
			shiftTable.put((char)i, pattern.length());
		
		
		char[] charArray = pattern.toCharArray();
		
		for (int i=0; i<charArray.length-1; i++)
		{
			shiftTable.put(charArray[i], charArray.length-i-1);
		}
		
	}

	public void bruteForce()
	{
		long startTime = System.nanoTime();
		boolean match = false;
		for (int i=0;i<inputString.length();i++)
		{
			int pCharLoc = 0;
			do
			{
				comparisonCounts.put("bruteForce", comparisonCounts.get("bruteForce")+1);
				if (inputString.charAt(i) == pattern.charAt(pCharLoc))
				{
					pCharLoc++;
					match = true;
					i++;
				}
				else
				{
					pCharLoc = 0;
					match = false;
				}
				
			}while (match && pCharLoc != pattern.length()-1);
			//checking match variable. which condition broke do-while? pattern ran out and all matched or a mismatch occured
//			if (match)
//				System.out.println(i);
		}
		
		runtimes.put("bruteForce", System.nanoTime()-startTime);
	}
	
	public void horspool() throws IOException
	{
		long startTime = System.nanoTime();
		createShiftTable();
		boolean match = false;
		int iCharLoc = pattern.length()-1;
		int pCharLoc = pattern.length()-1;
		char lastChar;
		int lastCharLoc;
		while (iCharLoc < inputString.length() && !match)
		{
			lastChar = inputString.charAt(iCharLoc);
			lastCharLoc = iCharLoc;
			do
			{
				comparisonCounts.put("horspool", comparisonCounts.get("horspool")+1);
				if (inputString.charAt(iCharLoc) == pattern.charAt(pCharLoc))
				{
					match = true;
					iCharLoc--;
					pCharLoc--;
				}
				else
				{
					match = false;
					iCharLoc=lastCharLoc+shiftTable.get(lastChar);
					pCharLoc=pattern.length()-1;
				}
			}while (match && pCharLoc > -1);
		}
//		if (match)
//			System.out.println(iCharLoc + " - " + (iCharLoc + pattern.length()));
		runtimes.put("horspool", System.nanoTime()-startTime);
	}
	public void boyerMoore()
	{
		
	}
	
	private void parseInputFile() throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		inputString = in.readLine();
		pattern = in.readLine();
		in.close();
	}
	public void outputFile()
	{
		System.out.println("Brute Force String Matching");
		System.out.print("Number of key comparison: ");
		System.out.println(comparisonCounts.get("bruteForce"));
		
		System.out.print("Runtime (nsec): ");
		System.out.println(runtimes.get("bruteForce"));
		
		System.out.println();
		
		System.out.println("Horspool String Matching");
		
		System.out.println(shiftTable);
		
		System.out.println();
		
		System.out.print("Number of key comparison: ");
		System.out.println(comparisonCounts.get("horspool"));
		
		System.out.print("Runtime (nsec): ");
		System.out.println(runtimes.get("horspool"));
	}
}
