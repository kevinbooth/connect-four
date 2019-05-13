package connectFour;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AutonomousPlayer implements Player {
	private String name = "Rob the Robot";
	private int playerNumber;
	private String tileType;
	private int difficulty;

	public AutonomousPlayer(int playerNumber, String tileType, int difficulty) {
		this.playerNumber = playerNumber;
		this.tileType = tileType;
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public String getTileType() {
		return tileType;
	}

	public int chooseRandomColumn(List<Integer> columns, int bestMove) {
		/*
		 * Chooses a random column on the board based on what columns have open spaces
		 */
		Random rand = new Random();
		int odds;

		if (difficulty == 1) {
			odds = rand.nextInt(2);
		} else if (difficulty == 2) {
			odds = rand.nextInt(1);
		} else if (difficulty == 3) {
			odds = 0;
		} else {
			odds = rand.nextInt(2);
		}

		if (bestMove != -1 && odds == 0) {
			return bestMove;
		} else {
			Collections.shuffle(columns);
			return columns.get(0);
		}
	}
}
