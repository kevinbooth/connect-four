package connectFour;

public class OrganicPlayer implements Player {
	private String name;
	private String tileType;
	
	public OrganicPlayer(String name, String tileType) {
		this.name = name;
		this.tileType = tileType;
	}
	
	public String getName() { return name; }
	
	public String getTileType() { return tileType; }
}
