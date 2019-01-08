package Connect4_GUI;

public class Player implements Comparable <Player>{
	
	private String playerName;
	private String playerSymbol;
	private int numGames;
	private int numWins;
	private int numLosses;
	
	public Player() {
		
	playerName = "Ralph";
	playerSymbol = "*";
	numGames = 0;
	numWins = 0;
	numLosses = 0;
	}
	public Player (String name, String symbol) {
		
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		playerName = name;
		playerSymbol = symbol;
		
	}
	public void addNumWins() {
		numWins++;
		numGames++;
		
	}
	
	public void addNumLosses() {
		numLosses++;
		numGames++;
		
	}
	public void addDraw() {
		numGames++;
		
	}
	public int getNumWins() {
		return numWins;
	}
	public int getNumLosses() {
		return numLosses;
	}
	public int getNumGames() {
		return numGames;
	}
	public String getSymbol() {
		return playerSymbol;
	}
	public String getPlayerName() {
		return playerName;
	}
	
	public boolean equals (Object o) {
		if (o instanceof Player) {
			Player otherPlayer = (Player)o;
			if (this.playerName.equalsIgnoreCase(otherPlayer.playerName)){
				if (this.playerSymbol.equalsIgnoreCase(otherPlayer.playerSymbol)) {
					if(this.numGames== otherPlayer.numGames) {
						if(this.numLosses == otherPlayer.numLosses) {
							if(this.numWins == otherPlayer.numWins) {
								return true;
							}
						}
					}
					
				}
				
			}
		}
		return false;
	}
	
	@Override
	public int compareTo (Player otherP) {
		if (this.numWins> otherP.numWins) {
			return 1;
		}
		else if (this.numWins < otherP.numWins) {
			return -1;
		}
		return 0;
	}
	
}
