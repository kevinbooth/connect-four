package connectFour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		/*
		 * Used to generate the board UI in the console Can be called after each
		 * successful move
		 */
		System.out.println("\n     =============");
		// Display the number for each column
		System.out.println("     1 2 3 4 5 6 7");
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (j == 0) {
					System.out.print("     ");
				}
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
		System.out.println("     =============\n");
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

	private boolean isBoardFull() {
		/*
		 * Checks to see if the board can hold any more moves If any value in our board
		 * is -1, the board is not full
		 */
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (gameBoard[i][j] == -1) {
					return false;
				}
			}
		}
		// Otherwise the board is full
		return true;
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
		/*
		 * Returns a list of columns that are not full
		 */
		List<Integer> emptyColumns = new ArrayList<Integer>();

		for (int i = 0; i < COLUMNS; i++) {
			if (gameBoard[0][i] == -1) {
				emptyColumns.add(i + 1);
			}
		}

		return emptyColumns;
	}

	public int validateAlmostWinner() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS - 3; j++) {
				Set<Integer> pieceSet = new HashSet<Integer>();
				pieceSet.add(gameBoard[i][j]);
				pieceSet.add(gameBoard[i][j + 1]);
				pieceSet.add(gameBoard[i][j + 2]);
				if (pieceSet.size() == 1) {
					if (!pieceSet.contains(-1) && gameBoard[i][j + 3] == -1 && gameBoard[i - 2][j] != -1) {
						return j + 4; // Base 1 return value
					} else if (!pieceSet.contains(-1) && gameBoard[i - 1][j] == -1 && gameBoard[i - 2][j] != -1) {
						if (j != 0) {
							return j; // Base 1 return value
						}
					}
				}
			}
		}

		for (int j = 0; j < COLUMNS; j++) {
			for (int i = ROWS - 1; i >= 3; i--) {

				Set<Integer> pieceSet = new HashSet<Integer>();
				pieceSet.add(gameBoard[i][j]);
				pieceSet.add(gameBoard[i - 1][j]);
				pieceSet.add(gameBoard[i - 2][j]);
				if (pieceSet.size() == 1) {
					if (!pieceSet.contains(-1) && gameBoard[i - 3][j] == -1) {
						return j + 1;
					}
				}
			}
		}
		return -1;
	}

	public int validateGameBoard() {
		/*
		 * 1.) Check each row for four sequential pieces of the same color 2.) Check
		 * each column for four sequential pieces of the same color 3.) check each
		 * diagonal(with more than four spaces along it) for four sequential pieces of
		 * the same color Return -1 if no current winner Return 0 if the board is full,
		 * indicating a tie Return 1 if player one wins Return 2 if player 2 wins
		 */

		if (isBoardFull()) {
			return 0;
		}
		int checkRows = validateRows();
		int checkColumns = validateColumns();
		int checkDiagonals = validateDiagonals();
		if (checkRows == 1 || checkColumns == 1 || checkDiagonals == 1) {
			return 1;
		} else if (checkRows == 2 || checkColumns == 2 || checkDiagonals == 2) {
			return 2;
		} else {
			return -1;
		}
	}

	private int validateRows() {
		/*
		 * To validate the rows we do the following: 1.) For each row, we select a slice
		 * of 4 columns. 2.) We place each of these column values in a hash set. 3.)
		 * Since hash sets do not allow duplicates, we will easily know if our group of
		 * 4 were the same number(color) 4.) We repeat this process for each group of
		 * four columns in the row, for every row of the board.
		 */
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS - 3; j++) {
				Set<Integer> pieceSet = new HashSet<Integer>();
				pieceSet.add(gameBoard[i][j]);
				pieceSet.add(gameBoard[i][j + 1]);
				pieceSet.add(gameBoard[i][j + 2]);
				pieceSet.add(gameBoard[i][j + 3]);
				if (pieceSet.size() == 1) {

					if (pieceSet.contains(X)) {
						// Player One Wins
						return X;
					} else if (pieceSet.contains(O)) {
						// Player Two Wins
						return O;
					}
				}
			}
		}

		return -1;
	}

	private int validateColumns() {
		/*
		 * To validate the columns, we use a similar hash set validation process to the
		 * row validation. The key difference is, for every column, we select a slice of
		 * 4 rows. each time we grab one of these slices, we check the hash set exactly
		 * the way we did the the row validator
		 */
		for (int j = 0; j < COLUMNS; j++) {
			for (int i = ROWS - 1; i >= 3; i--) {

				Set<Integer> pieceSet = new HashSet<Integer>();
				pieceSet.add(gameBoard[i][j]);
				pieceSet.add(gameBoard[i - 1][j]);
				pieceSet.add(gameBoard[i - 2][j]);
				pieceSet.add(gameBoard[i - 3][j]);
				if (pieceSet.size() == 1) {
					// We have a winner
					if (pieceSet.contains(X)) {
						// Player 1 Wins
						return X;
					} else if (pieceSet.contains(O)) {
						// Player 2 Wins
						return O;
					}
				}
			}
		}
		return -1;
	}

	private int validateDiagonals() {
		// Start by moving across the first row(left to right), and check all diagonals
		// that can fit more than 4 pieces.
		// System.out.println("Now validating diagonals left to right");
		// Validating the diagonals is more involved than the last two validations:

		/*
		 * First, move across the first row, validating all left diagonals (diagonals
		 * which connect the top row to the left most column)
		 */
		// Note that not every diagonal will contain 4 positions, so we can skip such
		// diagonals
		for (int i = 3; i < COLUMNS; i++) {
			int j = 0; // Check each left diagonal in the first row
			int k = i;
			while (k - 3 >= 0 && j + 3 < ROWS) {
				Set<Integer> pieces = new HashSet<>();
				pieces.add(gameBoard[j][k]);
				pieces.add(gameBoard[j + 1][k - 1]);
				pieces.add(gameBoard[j + 2][k - 2]);
				pieces.add(gameBoard[j + 3][k - 3]);
				if (pieces.size() == 1) {
					if (pieces.contains(X)) {
						return X;
					} else if (pieces.contains(O)) {
						return O;
					}
				}
				j++;
				k--;

			}

		}

		/*
		 * Then we move down the right most column and validate each diagonal which
		 * connects this column to the bottom row
		 */
		// Note that our previous top row diagonal validator will have checked the fist
		// column's diagonal already
		for (int i = 1; i < 3; i++) {
			int j = i; // set the row number to change with i
			int k = COLUMNS - 1;// only traverse the last column

			while (j + 3 < ROWS && k - 3 >= 0) {
				Set<Integer> pieces = new HashSet<>();
				pieces.add(gameBoard[j][k]);
				pieces.add(gameBoard[j + 1][k - 1]);
				pieces.add(gameBoard[j + 2][k - 2]);
				pieces.add(gameBoard[j + 3][k - 3]);

				if (pieces.size() == 1) {
					if (pieces.contains(X)) {
						return X;
					} else if (pieces.contains(O)) {
						return O;
					}
				}
				j++;
				k--;
			}
		}

		// System.out.println("Now validating diagonals right to left");

		/*
		 * Now we repeat the above process, but begin by validating each right
		 * diagonal(diagonals which connect the top row to the rightmost column
		 */
		// Note we can again ignore diagonals that are shorter than 4 board positions
		for (int i = COLUMNS - 4; i >= 0; i--) {
			// Moving across the top row from right to left, validate each diagonal
			int j = 0; // Move across the first row
			int k = i;// set the column number to change with i

			while (j + 3 < ROWS && k + 3 < COLUMNS) {
				Set<Integer> pieces = new HashSet<>();
				pieces.add(gameBoard[j][k]);
				pieces.add(gameBoard[j + 1][k + 1]);
				pieces.add(gameBoard[j + 2][k + 2]);
				pieces.add(gameBoard[j + 3][k + 3]);

				if (pieces.size() == 1) {
					if (pieces.contains(X)) {
						return X;
					} else if (pieces.contains(O)) {
						return O;
					}
				}
				j++;
				k++;
			}
		}

		/*
		 * Lastly, move down the leftmost column and check each diagonal which connects
		 * the left most column to the bottom row
		 */
		for (int i = 1; i < 3; i++) {
			// validate each diagonal here
			int j = i;// set the row number to change with i;
			int k = 0;// before entering the while loop, begin at the first column(column 0);
			while (j + 3 < ROWS && k + 3 < COLUMNS) {
				Set<Integer> pieces = new HashSet<>();
				pieces.add(gameBoard[j][k]);
				pieces.add(gameBoard[j + 1][k + 1]);
				pieces.add(gameBoard[j + 2][k + 2]);
				pieces.add(gameBoard[j + 3][k + 3]);

				if (pieces.size() == 1) {
					if (pieces.contains(X)) {
						return X;
					} else if (pieces.contains(O)) {
						return O;
					}
				}
				j++;
				k++;
			}

		}
		return -1;
	}

}
