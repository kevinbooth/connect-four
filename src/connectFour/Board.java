package connectFour;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final int[][] gameBoard;
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final int X = 1;
	private static final int O = 2;

	public Board() {
		this.gameBoard = new int[ROWS][COLUMNS];
		// Initialize each position in the game board to empty
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				gameBoard[i][j] = -1;
			}
		}
	}

	public void generateGameBoard() {
		System.out.println("\n=============");
		// Display the number for each column
		System.out.println("1 2 3 4 5 6 7");
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (gameBoard[i][j] == X) {
					System.out.print("X ");
				} else if (gameBoard[i][j] == O) {
					System.out.print("O ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
		System.out.println("=============\n");
	}

	public boolean makeTurn(Player player, int column) {
		/*
		 * Since row position is determined by how many pieces are currently in a given
		 * column, we only need to choose a column position and the row position will be
		 * determined as a result.
		 */

		// Decrement the column value by 1, as our array is zero indexed.
		column--;

		// Check if the column chosen is valid
		if (column < 0 || column >= COLUMNS) {
			System.out.println("Column choice must be between 0 and 7!");
			return false;
		}

		// Check if the column chosen is already full
		if (isColumnFull(column)) {
			System.out.println("That column is already full!");
			return false;
		}
		/*
		 * Otherwise, start from the bottom of the column and change the value in the
		 * first open row to the player's number
		 */
		else {
			for (int i = ROWS - 1; i >= 0; i--) {
				if (gameBoard[i][column] == -1) {
					gameBoard[i][column] = player.getPlayerNumber();
					break;
				}
			}
			return true;
		}

	}

	private boolean isColumnFull(int columnNumber) {
		/*
		 * Based on the way pieces are placed in a game of connect four, if the very
		 * first row of a column has a piece in it, the column must be full.
		 */
		if (gameBoard[0][columnNumber] == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Integer> getEmptyColumns() {
		List<Integer> emptyColumns = new ArrayList<Integer>();
		
		for (int i = 0; i < COLUMNS; i++) {
			if (gameBoard[0][i] == -1) {
				emptyColumns.add(i + 1);
			}
		}
		
		return emptyColumns;
	}
}
