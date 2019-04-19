package connectFour;

public class Main {

	public static void main(String[] args) {
		Game game;
		boolean playing = true;
		
		while(playing) {
			game = new Game();
			
			game.toggleTurn();
			
			// just to get out of the loop for now while we develop
			playing = false;
		}

	}

}
