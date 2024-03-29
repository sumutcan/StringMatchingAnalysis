import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;



public abstract class StringMatching {

	private String filePath;
	protected String inputString;
	protected String pattern;
	protected int comparisonCount;
	protected long runtime;

	protected Hashtable<Character, Integer> shiftTable;

	
	public StringMatching(String filePath) throws IOException
	{
		this.filePath = filePath;

		shiftTable = new Hashtable<Character,Integer>();


		comparisonCount = 0;
		runtime = (long)0;
		parseInputFile();
		
	}
	

	public StringMatching(String inputString, String pattern)
	{
		this.inputString = inputString;
		this.pattern = pattern;
		shiftTable = new Hashtable<Character,Integer>();
		comparisonCount = 0;
		runtime = (long)0;
	}
	
	protected void createShiftTable() throws IOException {
		//initialize shifttable with possible chars and pattern length
		shiftTable.put('_', pattern.length());
		for (int i = 65; i<91; i++)
			shiftTable.put((char)i, pattern.length());
		
		//fill shift table with distance to last char of all chars 
		char[] charArray = pattern.toCharArray();
		
		for (int i=0; i<charArray.length-1; i++)
		{
			shiftTable.put(charArray[i], charArray.length-i-1);
		}
	}

	

	
	private void parseInputFile() throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		inputString = in.readLine();
		pattern = in.readLine();
		in.close();
	}

	public abstract int run () throws IOException;

	
	public abstract void outputFile() throws IOException;

}
