package analysis;

import java.io.IOException;

public class Analysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AlgoApriori apriori = new AlgoApriori();
		try {
			apriori.runAlgorithm(0.0, "chessGamesWhite.csv", "chessResultsWhite.txt");
			apriori.runAlgorithm(0.0, "chessGamesBlack.csv", "chessResultsBlack.txt");
			apriori.runAlgorithm(0.0, "chessGamesDraw.csv", "chessResultsDraw.txt");
			apriori.runAlgorithm(0.00265, "chessGamesMixed.csv", "chessResultsMixed.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
