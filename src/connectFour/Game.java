package connectFour;

import java.util.Scanner;

public class Game {
	
	private Scanner scanner = new Scanner(System.in);
	private int moves = 7 * 6;
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	
	public Game() {
		board = new Board();
		startGame();
	}
	
	private void startGame() {
		printHeader();
		createPlayerData();
		
	}
	
	private void createPlayerData() {
		System.out.println("What is your name?: ");
		String name = scanner.nextLine();
		playerOne = new OrganicPlayer(name, "X");
		playerTwo = new AutonomousPlayer("O");
	}
	
	public int getMoves() { return moves; }
	
	public void decreaseMoves() { --moves; }
	
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
	    System.out.println("Select a column number from 1 to 7:");
	    System.out.println();
	}
}
