import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Hashtable;



public abstract class StringMatching {

	private String filePath;
	protected String inputString;
	protected String pattern;
	protected int comparisonCount;
	protected long runtime;

	
	public StringMatching(String filePath) throws IOException
	{
		this.filePath = filePath;
		comparisonCount = 0;
		runtime = (long)0;
		parseInputFile();
		
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
	public abstract void run () throws IOException;
	
	public abstract void outputFile();

}
