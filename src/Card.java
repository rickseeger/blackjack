
/**
 * This class represents a card in a deck. 
 * @author Rick Seeger
 *
 */

public class Card {

	private int rank = 0;
	private int suit = 0;
	private int deck = 0;

	private static final String[] shortRankName = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private static final String[] shortSuitName = {"S","H","D","C"};
	private static final String[] longRankName = {"Ace","Two","Three","Four","Five","Six","Seven",
												 "Eight", "Nine","Ten","Jack","Queen","King"};
	private static final String[] longSuitName = {"Spade","Heart","Diamond","Club"};
	
		
	/**
	 * Think of each card as having a unique integer ID 
	 * which determines its rank, suit, and deck number,
	 * all zero-based.
	 */
	public Card(int cardID) {
		rank = cardID % 13;
		suit = (cardID / 13) % 4;
		deck = (cardID / 52);
	}
	
	/**
	 * Get the value of the card. Face cards are worth 10. Aces are special
	 * in Blackjack, but are not handled here and instead return a value of 1.
	 * @return	value of the card
	 */
	public int getValue() {
		int value = rank + 1;              // rank is zero based
		return(value > 10 ? 10 : value);   // face cards are 10
	}
	
	/**
	 * @return the short name for the card e.g. "9D" for a nine of diamonds.
	 */
	public String shortName() {
		// check for array out of bounds error
		String cardName = shortRankName[rank] + shortSuitName[suit];
		return(cardName);
	}

	/**
	 * @return the long name for the card e.g. "Nine of Diamonds"
	 */
	public String longName() {
		String cardName = longRankName[rank] + " of " + longSuitName[suit] + "s";
		return(cardName);
	}

	public int getDeckNumber() {
		return(deck);
	}	
}
