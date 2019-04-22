package connectFour;

public class OrganicPlayer implements Player {
	private String name;
	private int playerNumber;
	private String tileType;
	
	public OrganicPlayer(String name, int playerNumber, String tileType) {
		this.playerNumber = playerNumber;
		this.name = name;
		this.tileType = tileType;
	}
	
	public String getName() { return name; }
	
	public int getPlayerNumber() { return playerNumber; }
	
	public String getTileType() { return tileType; }
}
