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
		String name;
		int difficulty;
		
		while(true) {
			System.out.print("What is your name?: ");
			name = scanner.nextLine();
			
			if(name.isEmpty()) {
				System.out.println("You didn't enter your name!");
			} else {
				break;
			}
		}
		
		while(true) {
			System.out.print("Choose a difficulty from 1 (easiest) to 3 (hardest): ");
			
			try {
				difficulty = Integer.parseInt(scanner.nextLine());
				System.out.println(difficulty);
				
				if (difficulty != 1 && difficulty != 2 && difficulty != 3) {
					System.out.println("Please choose a number with the range!");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please input a number!");
			}
		}
		
		playerOne = new OrganicPlayer(name, 1, "X");
		playerTwo = new AutonomousPlayer(2, "O", difficulty);
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
			int bestMove = board.validateAlmostWinner();
			
			int column = ((AutonomousPlayer) currentPlayer).chooseRandomColumn(emptyColumns, bestMove);
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
