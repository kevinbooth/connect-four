package connectFour;

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
		
	}
	
	private void createPlayerData() {
		System.out.print("What is your name?: ");
		String name = scanner.nextLine();
		playerOne = new OrganicPlayer(name, "X");
		playerTwo = new AutonomousPlayer("O");
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
	    System.out.println("            CONNECT FOUR");
	    System.out.println("-------------------------------------");
	    System.out.println("Welcome to Connect Four!");
	    System.out.println();
	}
	
	public boolean toggleTurn() {
		changePlayer();
		
		System.out.printf("Select a column number from 1 to 7, %s: ", currentPlayer.getName());
		
		// returns true to keep playing if no one has won yet
		return true;
	}
}
