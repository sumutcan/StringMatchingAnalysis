import java.io.IOException;
import java.util.Hashtable;


public class BoyerMoore extends StringMatching {

	private Hashtable<Integer, Integer> goodSuffixTable;
	private int longestSuffix;
	
	
	public BoyerMoore(String filePath) throws IOException {
		super(filePath);
		goodSuffixTable = new Hashtable<Integer,Integer>();
		longestSuffix = 0;
		
	}

	@Override
	public int run() throws IOException {
		super.createShiftTable();
		createGoodSuffixTable();
		
		return -1;

	}

	private void createGoodSuffixTable() {
		
		for (int i=0;i<pattern.length()-1;i++)
		{
			String suffix = pattern.substring(pattern.length()-1-i);
			String rest = pattern.substring(0,pattern.length()-1-i); 
			int d = searchSuffix(suffix, rest);
			
			if (d != -1)
				longestSuffix = suffix.length();
			else
			{
				
				d=pattern.length();
			}
			
			goodSuffixTable.put(suffix.length(), d);
		}
		
	}

	private int searchSuffix(String suffix, String rest) {
		
		if (rest.length() < suffix.length())
			return -1;
		
		boolean match = false;
		int i=rest.length()-1;
		int j=suffix.length()-1;
		while (i>-1 && !match)
		{
			do
			{
				if (suffix.charAt(j)==rest.charAt(i))
				{
					match = true;
					j--;
					if (j>-1)
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
			
			if (i > 1 && match && rest.charAt(rest.length()-1) == rest.charAt(i-1))
				match = false;
			
			if (match)
				return pattern.length()-1 -( i+suffix.length()-1);
			
			i--;
		}
		
		return -1;
	}

	@Override
	public void outputFile() {
		// TODO Auto-generated method stub

	}

}
