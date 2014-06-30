
/**
 * A dealer is a special type of player who makes money from 
 * tips and can issue praises or taunts accordingly.
 * @author Rick Seeger
 *
 */

public class Dealer extends Player {
	
	/**
	 * The superclass constructor requires a name argument.
	 */
	public Dealer() {
		super("The dealer");
	}

	private static final String[] praise = { 
		"Nicely done!",
		"Wow. I didn't see that coming.",
		"Good for you.",
		"Impressive. Very impressive.",
		"You're a natural!",
		"Keep that up and you'll retire early.",
		"You clearly know what you're doing.",
		"Well played, sir.",
		"I think you got lucky there.",
		"Are you counting cards?"
		};
	
	private static final String[] taunt = {
		"Don't quit your day job, buddy.",
		"Maybe gambling just isn't your thing.",
		"I almost feel bad taking your money.",
		"Perhaps you should consider lessons.",
		"A fool and his money just parted.",
		"I find your lack of skill disturbing.",
		"You do know the rules, right?",
		"Maybe you should try Solitaire instead."
	};
	
	public String randomPraise() {
		int i = (int) (Math.random() * (double)praise.length);
		return(praise[i]);
	}

	public String randomTaunt() {
		int i = (int) (Math.random() * (double)taunt.length);
		return(taunt[i]);
	}
	
	/**
	 * Adds a tip amount to the dealer's chip count. 
	 * @param 	tip
	 * @return	true if successful, false if tip invalid 
	 */
	public Boolean giveTip(int tip) {
		if (tip <= 0) return(false);
		else {
			addChips(tip);
			return(true);
		}
	}
}
