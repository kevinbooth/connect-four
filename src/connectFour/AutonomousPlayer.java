package connectFour;

import java.util.Random;

public class AutonomousPlayer implements Player {
	private String name = "Rob the Robot";
	private String tileType;
	
	public AutonomousPlayer(String tileType) {
		
	}
	
	public String getName() { return name; }
	
	public String getTileType() { return tileType; }
	
	public int chooseRandomColumn(int columnLimit) {
		Random rand = new Random();
		int column = rand.nextInt(columnLimit) + 1;
		
		return column;
	}
}
