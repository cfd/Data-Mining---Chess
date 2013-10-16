import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PGNReader {

	// public String line = "";

	// public StringBuilder builder;
	public ArrayList<Game> games;
	public Game game;
	public BufferedWriter output;

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

			Scanner scanner = new Scanner(new FileReader("BDG2.PGN"));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (!line.startsWith("[Result") && line.startsWith("[")) {
					// System.out.println("do nothing");
				}
				if (line.startsWith("[Result")) {
					// new Game
					game = new Game();
					// System.out.println("new game!");
					games.add(game);
					String result = "";
					if (line.equals("[Result \"1-0\"]")) {
						result = "white";
					} else if (line.equals("[Result \"0-1\"]")) {
						result = "black";
					} else {
						result = "draw";
					}

					// System.out.println("set result!");
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

		try {
			output = new BufferedWriter(new FileWriter("chessGames.csv", true));

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Game game : games) {
			// System.out.println(game.builder.toString().trim());
			// remove variations and annotations
			purgeAnnotations(game);

			purgeVariations(game);

			// System.out.println(game.builder.toString().trim());

			// add commas
			commaTime(game);
			game.setOutputLine(game.getResult() + "," + game.getMoves().trim());

			// write file
			System.out.println(game);

			try {
				if (game.getResult().equals("draw")) {
					output.append(game.toString());
					output.newLine();
				}
				// output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (game.getResult().equals("white")) {
				++white;
			}
			if (game.getResult().equals("black")) {
				++black;
			}
			if (game.getResult().equals("draw")) {
				++draw;
			}
			// end
		}
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// alpha analysis

		System.out.println("white: " + white + "black: " + black + "draw: "
				+ draw);

	}

	public void purgeAnnotations(Game game) {
		int start = 0;
		int end = 0;
		boolean clean = false;
		boolean removedOne = false;
		while (!clean) {
			for (int i = 0; i < game.builder.toString().trim().length(); ++i) {
				removedOne = false;

				if (game.builder.toString().trim().charAt(i) == '{') {

					start = i;
					// System.out.println("set start to: " + start);
				}
				if (game.builder.toString().trim().charAt(i) == '}') {
					if (game.builder.toString().trim().charAt(i + 5) == '.') {
						end = i + 8;
					} else {
						end = i + 2;
					}
					// System.out.println("set end to: " + end);
					// System.out.println(start + " " + end);
					game.builder.delete(start, end);
					removedOne = true;
					break;
				}
			}
			if (!removedOne) {
				clean = true;
			}

		}
	}

	public void purgeVariations(Game gqme) {
		int start = 0;
		int end = 0;
		boolean clean = false;
		boolean removedOne = false;
		while (!clean) {
			for (int i = 0; i < game.builder.toString().trim().length(); ++i) {
				removedOne = false;

				if (game.builder.toString().trim().charAt(i) == '(') {

					start = i;
					// System.out.println("set start to: " + start);
				}
				if (game.builder.toString().trim().charAt(i) == ')') {
					end = i + 7;
					// System.out.println("set end to: " + end);
					// System.out.println(start + " " + end);
					game.builder.delete(start, end);
					removedOne = true;
					break;
				}
			}
			if (!removedOne) {
				clean = true;
			}

		}
	}

	public void commaTime(Game game) {
		for (int i = 0; i < game.builder.toString().trim().length() - 3; ++i) {
			if (game.builder.toString().trim().charAt(i) == ' ') {
				if (game.builder.toString().trim().charAt(i + 2) == '.') {
					game.builder.setCharAt(i + 1, ',');
				}
				if (game.builder.toString().trim().charAt(i + 3) == '.') {
					game.builder.setCharAt(i + 1, ',');
				}

			}
		}

		// System.out.println("set moves!");
		game.setMoves(game.builder.toString());
	}

}
