import java.io.IOException;
import java.util.Hashtable;


public class Horspool extends StringMatching {
	
	private Hashtable<Character, Integer> shiftTable;
	
	public Horspool(String filePath) throws IOException {
		super(filePath);
		shiftTable = new Hashtable<Character,Integer>();
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

	@Override
	public void run() throws IOException {
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
				comparisonCount++;
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
		runtime = System.nanoTime()-startTime;
		
	}

	@Override
	public void outputFile() {
		System.out.println("Horspool String Matching");
		
		System.out.println(shiftTable);
		
		System.out.println();
		
		System.out.print("Number of key comparison: ");
		System.out.println(comparisonCount);
		
		System.out.print("Runtime (nsec): ");
		System.out.println(runtime);
		
	}
	
	
}
