
/** 
 * Player.java - a class for representing a blackjack player.
 * @author Rick Seeger
 *
 */

public class Player {

	public static final int INITIAL_CHIPS = 100;
	private String name = "";
	private int chips = INITIAL_CHIPS;
		
	/**
	 * The constructor requires a name.
	 * @param name
	 */
	public Player(String name) {
		if ((name == null) || (name.length() == 0)) { this.name = "Player"; }
		else { this.name = name; }
	}
	
	public String getName() {
		return(name);
	}
	
	public void addChips(int amount) {
		chips += amount;
	}

	public int getChips() {
		return(chips);
	}		
}
