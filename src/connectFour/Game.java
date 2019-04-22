package connectFour;

import java.util.List;
import java.util.Scanner;

public class Game {
	
	private Scanner scanner = new Scanner(System.in);
	private int moves = 7 * 6;
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	
	public Game() {
		board = new Board();
		startGame();
	}
	
	private void startGame() {
		printHeader();
		createPlayerData();
		board.generateGameBoard();
	}
	
	private void createPlayerData() {
		System.out.print("What is your name?: ");
		String name = scanner.nextLine();
		playerOne = new OrganicPlayer(name, 1, "X");
		playerTwo = new AutonomousPlayer(2, "O");
	}
	
	private void changePlayer() {
		if (currentPlayer == playerOne) {
			currentPlayer = playerTwo;
		} else {
			currentPlayer = playerOne;
		}
	}
	
	public int getMoves() { return moves; }
	
	private void decreaseMoves() { --moves; }
	
	public boolean checkMoves() {
        if (moves == 0) {
           return false;
        }
        return true;
	}
	
	private void printHeader() {
	    System.out.println("      CONNECT FOUR");
	    System.out.println("------------------------");
	    System.out.println("Welcome to Connect Four!");
	    System.out.println();
	}
	
	public boolean toggleTurn() {
		boolean movesLeft;
		changePlayer();
		decreaseMoves();
		
		if (currentPlayer instanceof OrganicPlayer) {
			boolean result = false;
			
			while (!result) {
				System.out.printf("Select a column, %s: ", currentPlayer.getName());
				try {
					int column = Integer.parseInt(scanner.nextLine());
					result = board.makeTurn(currentPlayer, column);
				} catch (NumberFormatException e) {
					System.out.println("Column choice must be a number!");
				}
			}
		} else {
			List<Integer> emptyColumns = board.getEmptyColumns();
			
			int column = ((AutonomousPlayer) currentPlayer).chooseRandomColumn(emptyColumns);
			board.makeTurn(currentPlayer, column);
			board.generateGameBoard();
		}
		
		
		movesLeft = checkMoves();
		if (movesLeft) {
			return true; // still have moves, keep playing
		} else {
			System.out.println("There are no move moves left on the board");
			return false; // no more moves left
		}
	}
}
