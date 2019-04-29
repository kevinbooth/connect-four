package connectFour;

import java.util.Collections;
import java.util.List;

public class AutonomousPlayer implements Player {
	private String name = "Rob the Robot";
	private int playerNumber;
	private String tileType;
	
	public AutonomousPlayer(int playerNumber, String tileType) {
		this.playerNumber = playerNumber;
		this.tileType = tileType;
	}
	
	public String getName() { return name; }
	
	public int getPlayerNumber() { return playerNumber; }
	
	public String getTileType() { return tileType; }
	
	public int chooseRandomColumn(List<Integer> columns) {
		/*
		 * Chooses a random column on the board based on
		 * what columns have open spaces
		 */
		Collections.shuffle(columns);
		return columns.get(0);
	}
}
