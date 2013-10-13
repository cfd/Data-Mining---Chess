
public class Game {

	public String result;
	public String moves;
	public StringBuilder builder;
	public String outputLine;
	
	
	public Game(){
		builder = new StringBuilder();
	}
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMoves() {
		return moves;
	}
	public void setMoves(String moves) {
		this.moves = moves;
	}

	public StringBuilder getBuilder() {
		return builder;
	}
	
	public void setOutputLine(String outputLine) {
		this.outputLine = outputLine;
	}

	@Override
	public String toString(){
		return outputLine;
	}
	
	
	
	
}
