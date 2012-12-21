import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;


public class StringMatching {

	private String filePath;
	private String inputString;
	private String pattern;
	private Hashtable<Character, Integer> shiftTable;
	
	public StringMatching(String filePath) throws IOException
	{
		this.filePath = filePath;
		shiftTable = new Hashtable<Character,Integer>();
		parseInputFile();
		createShiftTable();
	}
	
	private void createShiftTable() throws IOException {
		StringReader reader = new StringReader(pattern);
		
		for (int i = 65; i<91; i++)
			shiftTable.put((char)i, pattern.length());
		
		int location = -1;
		char[] charArray = inputString.toCharArray();
		
	}

	public void bruteForce()
	{
		
	}
	
	public void horpool()
	{
		
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
}
