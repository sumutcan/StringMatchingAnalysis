import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;




public class BoyerMoore extends StringMatching {


	private Hashtable<Integer, Integer> goodSuffixTable;
	
	
	public BoyerMoore(String filePath) throws IOException {
		super(filePath);
		goodSuffixTable = new Hashtable<Integer,Integer>();
		

	}
		
	@Override

	public int run() throws IOException {
		long startTime = System.nanoTime();
		super.createShiftTable();
		createGoodSuffixTable();
		
		boolean match = false;
		int iCharLoc = pattern.length()-1;
		int pCharLoc = pattern.length()-1;
		
		int lastCharLoc;
		int successfulMatchCount = 0;
		while (iCharLoc < inputString.length() && !match)
		{
	
			lastCharLoc = iCharLoc;
			do
			{
				comparisonCount++;
				if (inputString.charAt(iCharLoc) == pattern.charAt(pCharLoc))
				{
					match = true;
					successfulMatchCount++;
					iCharLoc--;
					pCharLoc--;
				}
				else
				{
					match = false;
					iCharLoc=lastCharLoc+calculateShift(iCharLoc,successfulMatchCount);
					pCharLoc=pattern.length()-1;
					successfulMatchCount = 0;
				}
			}while (match && pCharLoc > -1);
		}
		
		runtime = System.nanoTime()-startTime;
		if (match)
			return iCharLoc + pattern.length();
		
		
		
		return -1;

	}

	private int calculateShift(int iCharLoc, int successfulMatchCount) {
		
		int d1 = Math.max(shiftTable.get(inputString.charAt(iCharLoc))-successfulMatchCount, 1);
		
		if (successfulMatchCount == 0)
			return d1;
		else
			return Math.max(d1, goodSuffixTable.get(successfulMatchCount));
		
		
	}

	private void createGoodSuffixTable() {
		
		for (int i=0;i<pattern.length()-1;i++)
		{
			String suffix = pattern.substring(pattern.length()-1-i);
			String rest = pattern.substring(0,pattern.length()-1-i); 
			
			int d = searchSuffix(suffix, rest);
			
			if (d == -1)
				d=pattern.length();
			
			
			goodSuffixTable.put(suffix.length(), d);
		}
		
		Enumeration<Integer> num = goodSuffixTable.keys();
		while (num.hasMoreElements())
		{
			
			Integer k = num.nextElement();
			if (goodSuffixTable.get(k) == pattern.length())
			{
				int d = searchPrefix(k);
				goodSuffixTable.put(k, d);
			}
				
		}
		
	}

	private int searchPrefix(int k) {
	
		for (int i=k-1; i>0; i--)
		{
			String prefix = pattern.substring(0,i);
			String suffix = pattern.substring(pattern.length()-i);
			
			if (prefix.compareTo(suffix) == 0 && goodSuffixTable.get(suffix.length()) != pattern.length())
				return pattern.length()-i;
		}
		
		return pattern.length();
	}

	private int searchSuffix(String suffix, String rest) {
		//the part left on lefthand side is longer?
		if (rest.length() < suffix.length())
			return -1;
		
		boolean match = false;
		int i=rest.length()-1;
		int j=suffix.length()-1;
		while (j>-1 && i>-1 && !match)
		{
			do
			{
				if (suffix.charAt(j)==rest.charAt(i))
				{
					match = true;
					j--;
					if (j>-1) //to prevent negative index exception
						i--;
				}
				else
				{
					match = false;
					j=suffix.length()-1;
				}
				
				if (j>i)
					return -1;
				
			}while (j>-1 && match);
			//check if the found suffix preceded by same char with the rightmost occurence
			if (i > 0 && match && rest.charAt(rest.length()-1) == rest.charAt(i-1))
			{
				//if it does, continue searching to beginning of string
				match = false;
				j=suffix.length()-1;
			}
			
			if (match)	
				return pattern.length()-1 -( i+suffix.length()-1);
			
			i--;
		}
		
		return -1;
	}

	@Override
	public void outputFile() throws IOException {
		
		File file = new File("/home/umutcan/workspace/AlgoritmaPart2/src/output");
		
		if (!file.exists())
			file.createNewFile();
		
		FileWriter writer = new FileWriter(file,true);
		BufferedWriter bufWriter = new BufferedWriter(writer);
		
		bufWriter.write("Boyer-Moore String Matching\n\n");
		bufWriter.write("Bad Symbol Shift Table\n");
		bufWriter.write(shiftTable.toString() + "\n\n");
		bufWriter.write("Good Suffix Table\n");
		bufWriter.write(goodSuffixTable.toString() + "\n\n");
		bufWriter.write("Number of key comparison: ");
		bufWriter.write(comparisonCount+"\n");
		bufWriter.write("Runtime (nsec): ");
		bufWriter.write(runtime + "\n\n");
		bufWriter.close();
		
//		System.out.println("Boyer-Moore String Matching");
//		
//		System.out.println(goodSuffixTable);
//		
//		System.out.println();
//		
//		System.out.print("Number of key comparison: ");
//		System.out.println(comparisonCount);
//		
//		System.out.print("Runtime (nsec): ");
//		System.out.println(runtime);
		
		
	}

}
