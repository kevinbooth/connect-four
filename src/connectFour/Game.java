package connectFour;

import java.util.List;
import java.util.Scanner;

public class Game {
	
	private Scanner scanner = new Scanner(System.in);
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	
	public Game() {
		board = new Board();
		startGame();
	}
	
	private void startGame() {
		/*
		 * Used as the method to setup all aspects of the game
		 */
		printHeader();
		createPlayerData();
		board.generateGameBoard();
	}
	
	private void createPlayerData() {
		/*
		 * Asks the player specific information about themselves
		 */
		System.out.print("What is your name?: ");
		String name = scanner.nextLine();
		playerOne = new OrganicPlayer(name, 1, "X");
		playerTwo = new AutonomousPlayer(2, "O");
	}
	
	private void changePlayer() {
		/*
		 * Changes the current player to the next player
		 */
		if (currentPlayer == playerOne) {
			currentPlayer = playerTwo;
		} else {
			currentPlayer = playerOne;
		}
	}
	
	private void printHeader() {
		/*
		 * Prints the header prior to starting the game
		 */
	    System.out.println("      CONNECT FOUR");
	    System.out.println("------------------------");
	    System.out.println("Welcome to Connect Four!");
	    System.out.println();
	}
	
	public boolean toggleTurn() {
		/*
		 * Handles the state of who is playing and is used to handle if anyone 
		 * has won the game
		 */
		changePlayer();
		
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
		
		
		int winStatus = board.validateGameBoard();
		
		if (winStatus == 0) {
			System.out.println("There are no move moves left on the board");
			return false; // no more moves left
		} else if (winStatus == 1) {
			board.generateGameBoard();
			System.out.println("Congrats " + playerOne.getName() + ", you have won the game!");
			return false;
		} else if (winStatus == 2) {
			System.out.println("Maybe next time, " + playerOne.getName() + ". "
			+ playerTwo.getName() + " has won the game!");
			return false;
		}
		
		return true;
	}
}
