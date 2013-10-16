package analysis;

import java.io.IOException;

public class Analysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AlgoApriori apriori = new AlgoApriori();
		try {
			apriori.runAlgorithm(0.5, "numbers.txt", "results.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
