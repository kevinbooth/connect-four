package connectFour;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean rematch = true;
		
		while(rematch) {
			Game game;
			boolean playing = true;
			game = new Game();
			game.startGame();
			
			while(playing) {
				playing = game.toggleTurn();
			}
			
			while(true) {
				System.out.print("Would you like a rematch? [y/N]: ");
				String input = scanner.nextLine();
				if (input.toLowerCase().equals("y")) {
					System.out.println();
					break;
				} else if (input.toLowerCase().equals("n")) {
					System.out.println("\nThanks for playing!");
					scanner.close();
					rematch = false;
					break;
				} else {
					System.out.println("Your input was wrong.");
				}
			}
		}
	}

}
