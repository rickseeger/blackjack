
/**
 * This class represents one or more decks of cards.
 * @author Rick Seeger
 *
 */
public class Shoe {
	
	private static final int DEFAULT_DECKS = 6;
	private static final int MAX_DECKS = 1000;
	private static final double RESHUFFLE_AFTER_PCT_USED = 0.20;
	private Card shoe[];
	private int shoeIndex[];
	private int numDecks = 0;	
	private int numCards = 0;
	private int reshufflePoint = 0;
	private static int cursor = 0;
	
	public Shoe() {
		makeShoe(DEFAULT_DECKS);
	}
	
	public Shoe(int numDecks) {
		makeShoe(numDecks);
	}
	
	private void makeShoe(int decks) {
		// instantiate cards
		if (decks > MAX_DECKS) decks = MAX_DECKS;
		numDecks = decks;
		numCards = (numDecks * 52);
		shoe = new Card[numCards];
		shoeIndex = new int[numCards];
		for(int i=0;i<numCards;i++) {
			shoe[i] = new Card(i);
			shoeIndex[i] = i; 
		}
		shuffle();

		// determine when to re-shuffle next
		reshufflePoint = (int) ((double)numCards * RESHUFFLE_AFTER_PCT_USED);
		if (reshufflePoint >= numCards) reshufflePoint = (numCards -1); 
	}
	
	public int getNumDecks() {
		return(numDecks);
	}
	
	/**
	 * shuffle the entire shoe using the unbiased Fisher–Yates method
	 * [http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle]
	 * Instead moving objects around, just shuffle indices instead
	 */
	public void shuffle() {
		System.out.println("Shuffling " + numDecks + " decks...");
		for(int i=0;i<(numCards-1);i++) {
			// pick random integer in range [i,numCards)					
        	int j = (int) ((Math.random() * (numCards-i)) + i);
        	// swap cards
        	int tmp = shoeIndex[i];
        	shoeIndex[i] = shoeIndex[j];
        	shoeIndex[j] = tmp;
        }
		// start from the top again
		cursor = 0;
	}

	/**
	 * Print the entire contents of the shoe to stdout for debugging.
	 */
	public void print() {
        for(int i=0;i<numCards;i++) {
			Card c = shoe[shoeIndex[i]];
			System.out.println(c.longName());
        }
	}

	
	/**
	 * Return the next card in the shoe, re-shuffling if necessary.
	 * @return Card object
	 */
	public Card nextCard() {
		if (cursor > reshufflePoint) shuffle();
		return(shoe[shoeIndex[cursor++]]);
		}		
}

