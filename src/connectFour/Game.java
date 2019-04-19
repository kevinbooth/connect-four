package connectFour;

import java.util.Scanner;

import game.Board;

public class Game {
	
	private Scanner scanner = new Scanner(System.in);
	private int moves = 7 * 6;
	private Board board;
	
	public Game() {
		board = new Board();
		startGame();
	}
	
	private void startGame() {
		printHeader();
	}
	
	private void printHeader() {
	    System.out.println("            CONNECT FOUR");
	    System.out.println("-------------------------------------");
	    System.out.println("Welcome to Connect Four!");
	    System.out.println("Select a column number from 1 to 7:");
	    System.out.println();
	}
}
