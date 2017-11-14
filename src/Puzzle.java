/**
 * Contains the grid for the puzzle, will solve the puzzle
 * @author Kenny Daily
 *
 */
public class Puzzle {
	private int[][] origPuzzle;
	private int[][] solvedPuzzle;
	
	public Puzzle() {
		setOrigPuzzle(new int[9][9]);
		setSolvedPuzzle(new int[9][9]);
	}
	
	public Puzzle(Puzzle other) {
		setOrigPuzzle(other.getOrigPuzzle());
		setSolvedPuzzle(other.getSolvedPuzzle());
	}

	public int[][] getOrigPuzzle() {
		return origPuzzle;
	}

	public void setOrigPuzzle(int[][] origPuzzle) {
		this.origPuzzle = origPuzzle;
	}

	public int[][] getSolvedPuzzle() {
		return solvedPuzzle;
	}

	public void setSolvedPuzzle(int[][] solvedPuzzle) {
		this.solvedPuzzle = solvedPuzzle;
	}
}
