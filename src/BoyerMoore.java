import java.io.IOException;
import java.util.Hashtable;


public class BoyerMoore extends StringMatching {

	private Hashtable<Character, Integer> shiftTable;
	
	public BoyerMoore(String filePath) throws IOException {
		super(filePath);
		shiftTable = new Hashtable<Character,Integer>();
		
	}

	@Override
	public void run() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void outputFile() {
		// TODO Auto-generated method stub

	}

}
