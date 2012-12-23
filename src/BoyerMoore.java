import java.io.IOException;
import java.util.Hashtable;


public class BoyerMoore extends StringMatching {

	
	public BoyerMoore(String filePath) throws IOException {
		super(filePath);
		
		
	}

	@Override
	public void run() throws IOException {
		super.createShiftTable();

	}

	@Override
	public void outputFile() {
		// TODO Auto-generated method stub

	}

}
