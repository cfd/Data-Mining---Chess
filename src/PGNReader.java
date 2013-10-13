import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PGNReader {

	// public String line = "";

	// public StringBuilder builder;
	public ArrayList<Game> games;
	public Game game;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PGNReader reader = new PGNReader();
		reader.run();

	}

	public void run() {

		games = new ArrayList<Game>();

		try {

			Scanner scanner = new Scanner(new FileReader("blackmar.pgn"));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (!line.startsWith("[Result") && line.startsWith("[")) {
					// System.out.println("do nothing");
				}
				if (line.startsWith("[Result")) {
					// new Game
					game = new Game();
					//System.out.println("new game!");
					games.add(game);
					String result = "";
					if (line.equals("[Result \"1-0\"]")) {
						result = "white";
					} else if (line.equals("[Result \"0-1\"]")) {
						result = "black";
					} else {
						result = "draw";
					}

					//System.out.println("set result!");
					game.setResult(result);
					// System.out.println(result);

				}

				if (!line.startsWith("[")) {
					game.getBuilder().append(line + " ");

				}
				
				

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		int white = 0;
		int black = 0;
		int draw = 0;
		
		for (Game game : games) {
			commaTime(game);
			game.setOutputLine(game.getResult() + "," + game.getMoves().trim());
			System.out.println(game);
			if (game.getResult().equals("white")){
				++white;
			}
			if (game.getResult().equals("black")){
				++black;
			}
			if (game.getResult().equals("draw")){
				++draw;
			}
			//end
		}
		
		//alpha analysis
		
		System.out.println("white: " + white + "black: " + black + "draw: " + draw);
		
		
	}

	public void commaTime(Game game) {
		//game.builder. = game.builder.toString().trim();
		//System.out.println(game.builder);
		for (int i = 0; i < game.builder.toString().trim().length()-3; ++i) {
			if (game.builder.toString().trim().charAt(i) == ' ') {
				if (game.builder.toString().trim().charAt(i + 2) == '.') {
					game.builder.setCharAt(i+1, ',');

					//game.builder.deleteCharAt(i+1);
				}
				if (game.builder.toString().trim().charAt(i + 3) == '.') {
					game.builder.setCharAt(i+1, ',');
					//game.builder.deleteCharAt(i+1);
				}

			}
		}

		//System.out.println("set moves!");
		game.setMoves(game.builder.toString());
	}
}
