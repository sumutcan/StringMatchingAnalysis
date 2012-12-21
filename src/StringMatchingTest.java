import java.io.IOException;


public class StringMatchingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try
		{
			StringMatching strMatch = new StringMatching("/home/umutcan/workspace/AlgoritmaPart2/src/input");
			strMatch.bruteForce();
			
			
		}catch(IOException ioEx)
		{
			System.err.println(ioEx.getMessage());
		}
	}

}
