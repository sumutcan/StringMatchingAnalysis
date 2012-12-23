import java.io.IOException;


public class BruteForce extends StringMatching {

	
	public BruteForce(String filePath) throws IOException {
		super(filePath);
		
	}

	@Override
	public void run() throws IOException {
		long startTime = System.nanoTime();
		boolean match = false;
		for (int i=0;i<inputString.length();i++)
		{
			int pCharLoc = 0;
			do
			{
				comparisonCount++;
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
		
		runtime = System.nanoTime()-startTime;

	}

	@Override
	public void outputFile() {
		System.out.println("Brute Force String Matching");
		System.out.print("Number of key comparison: ");
		System.out.println(comparisonCount);
		
		System.out.print("Runtime (nsec): ");
		System.out.println(runtime);
		
		System.out.println();
		
		
	}

}
