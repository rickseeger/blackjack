import java.util.ArrayList;

/**
 * Hand.java - This class represents a players Blackjack hand.
 * @author Rick Seeger
 *
 */
public class Hand {

	private ArrayList<Card> cards;
	private Boolean active = true;
	private int bet = 0;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
	
	/**
	 * Computes the value of a Blackjack hand.
	 */
	public int getValue() {
		int numAces = 0;
		int total = 0;
		
		// sum of card values
		for (Card c : cards) {
			int v = c.getValue();
			if (v == 1) { // ace
				numAces++;
				total += 11;
			}
			else {
				total += v;
			}
		}
		
		// downgrade the aces until we're below 21
		while ((total > 21) && (numAces > 0)) {
			total -= 10;
			numAces--;
		}	
		return(total);
	}
	
	/**
	 * Determine if the hand can be split which requires two cards of the same value.
	 */
	public Boolean isSplittable() {
		// must have two cards of same value
		if ((cards.size() == 2) && (cards.get(0).getValue() == cards.get(1).getValue())) return(true);
		else return(false);
	}
	
	/**
	 * Split the hand and return the new hand created. If the hand
	 * is not splittable, null is returned.
	 * @return A new Hand with a single card split off from the original.
	 */
	Hand split() {
		if (isSplittable()) { 
			Hand newHand = new Hand();
			newHand.addCard(cards.remove(0));
			return(newHand);
		}
		else {
			return(null);
		}		
	}
	
	/**
	 * Print the hand to stdout so it is readable.
	 * @param isDealer	if true then don't show the first card or the total
	 */
	private void displayHand(Boolean isDealer) {
		Boolean isFirstCard = true;
		
		for (Card c : cards) {
			if (isFirstCard && isDealer) {
				System.out.println(" [HIDDEN CARD]");
				isFirstCard = false;
			}
			else {
				System.out.println(" " + c.longName()); 
			}
		}
		if (!isDealer) System.out.println(" Value: " + getValue());
		System.out.println("");
	}
	
	
	public void display() { displayHand(false); }
	public void displayPartial() { displayHand(true); }
	


	/**
	 * Transfer chips from a player to this hand, if valid.
	 * @param p		A player object
	 * @param bet	Number of chips to bet
	 * @return		true on success, false on failure
	 */
	public Boolean addBet(Player p, int bet) {
		int chips = p.getChips();
		if ((bet < 0) || (bet > chips)) return(false);
		this.bet += bet;
		p.addChips(-bet);
		return(true);
	}
	
	public int getBet() {
		return(bet);
	}

	void setActive(Boolean active) {
		this.active = active;
	}
	
	Boolean getActive() {
		return(active);
	}
		
}
	
	
		
			
