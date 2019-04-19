package connectFour;

import java.util.Scanner;

import game.Board;

public class Game {
	
	public static char[] number = new char[] { '1', '2' };
	public static char[] badge = new char[] { 'X', 'O' };
	private Scanner scanner = new Scanner(System.in);
	private int moves = 7 * 6;
	private int currentPlayer;
	private Board board;
	
	public Game() {
		currentPlayer = 1;
		board = new Board();
		startGame();
	}
	
	private void startGame() {
		
	}
}
