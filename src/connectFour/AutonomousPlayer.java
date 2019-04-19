package connectFour;

public class AutonomousPlayer implements Player {
	private String name = "Rob the Robot";
	private String tileType;
	
	public AutonomousPlayer(String tileType) {
		
	}
	
	public String getName() { return name; }
	
	public String getTileType() { return tileType; }
}
