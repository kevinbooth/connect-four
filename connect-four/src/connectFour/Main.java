package connectFour;

import java.util.Scanner;


public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	    public static void main(String[] args) {
	      //Create two players and get their names from user input
	        System.out.println("Welcome to Connect Four!");
	        System.out.println("Please enter the name of Player1: ");
	        String player1_name = scanner.nextLine();
	        System.out.println("Please enter the name of Player2: ");
	        String player2_name = scanner.nextLine();
	        Player player_One = new Player(player1_name);
	        Player player_Two = new Player(player2_name);
	        System.out.println(player_One.getName() + " will be X  on the board)");
	        System.out.println(player_Two.getName() + " will be O  on the board)\n");
	        Board B1 = new Board(player_One,player_Two);
	        Board B2 = new Board(player_One,player_Two);
	        B1.generateGameBoard();
	        System.out.println(player_One.getName() + ", Please enter a column to make your move");
            int turn_One =  scanner.nextInt();
            B1.makeTurn(player_One, turn_One);
            B1.generateGameBoard();
            System.out.println(player_Two.getName() + ", Please enter a column to make your move");
            int turn_Two =  scanner.nextInt();
            B2.makeTurn(player_Two, turn_Two);
            B2.generateGameBoard();
            

	}

}
