import java.io.IOException;


public class BruteForce extends StringMatching {

	
	public BruteForce(String filePath) throws IOException {
		super(filePath);
		
	}

	@Override
	public int run() throws IOException {
		long startTime = System.nanoTime();
		boolean match = false;
		int i;
		for (i=0;i<inputString.length() && match != true;)
		{
			//conterary of horspool, save first char of current state in order to shift 
			int pCharLoc = 0;
			int firstCharLoc = i;
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
					//shift first char by one char after a mismatch
					pCharLoc = 0;
					match = false;
					i=firstCharLoc+1;
					
				}
				
			}while (match && pCharLoc < pattern.length());
			//checking match variable. which condition broke do-while? pattern ran out and all matched or a mismatch occured
			
		}
		
		runtime = System.nanoTime()-startTime;
		
		if (match)
			return i-1;
		
		return -1;

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
