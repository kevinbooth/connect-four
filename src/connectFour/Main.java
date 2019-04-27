package connectFour;

public class Main {

	public static void main(String[] args) {
		Game game;
		boolean playing = true;
		game = new Game();
		game.startGame();
		
		while(playing) {
			// start player's turn
			playing = game.toggleTurn();
		}
	}

}
