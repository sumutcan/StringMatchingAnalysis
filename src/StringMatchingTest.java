import java.io.IOException;


public class StringMatchingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try
		{
			StringMatching[] algorithms = new StringMatching[2];
		
			algorithms[0] = new BruteForce("/home/umutcan/workspace/AlgoritmaPart2/src/input");
			algorithms[1] = new Horspool("/home/umutcan/workspace/AlgoritmaPart2/src/input");
			//algorithms[2] = new BoyerMoore("/home/umutcan/workspace/AlgoritmaPart2/src/input");
			
			for (StringMatching strMatch : algorithms) {
				strMatch.run();
				strMatch.outputFile();
			}
			
			
			
		}catch(IOException ioEx)
		{
			System.err.println(ioEx.getMessage());
		}
	}

}
