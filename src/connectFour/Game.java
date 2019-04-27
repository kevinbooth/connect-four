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
	}
	
	public void startGame() {
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
		
		// check board capacity and for winners
		int status = board.validateGameBoard();
		
		if (status == 0) {
			System.out.println("There are no more moves \nleft on the board");
			return false; // no more moves left
		} else if (status == 1) {
			board.generateGameBoard();
			System.out.println("Congrats " + playerOne.getName() + ", you have won the game!");
			return false;
		} else if (status == 2) {
			System.out.println("Maybe next time, " + playerOne.getName() + ". "
			+ playerTwo.getName() + " has won the game!");
			return false;
		}
		
		return true;
	}
}
