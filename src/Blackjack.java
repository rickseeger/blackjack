
import java.io.*;

/**
 * Blackjack.java - implements a basic game of Blackjack.
 * @author Rick Seeger
 */

public class Blackjack {
	
	/**
	 * Add an "s" or not based on the number of items
	 * @param n	the number to pluralize
	 * @return 	an empty string or "s" 
	 */
	public static String pluralize(int n) {
		if (n == 1) return("");
		else return("s");
	}
	
	/**
	 * Try to parse a positive integer with exception handling
	 * @param s	string to be parsed
	 * @return 	a positive integer or 0 on failure
	 */
	public static int parseNatural(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return(0);
		}
		return(i);
	}
	

	/**
	 * Read a line from standard input, exit on failure
	 * @param promptText	text displayed before waiting for input
	 * @return				the line read from stdin
	 */
	public static String readLine(String promptText) {
		String line = "";
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print(promptText);
	    try {
	    	line = reader.readLine();
	    }
	    catch (IOException e) {
	    	// fatal error: we can't play if we can't access STDIN!
	    	System.out.println("Unable to read from STDIN. Exiting!");
	    	System.exit(1);
	    }
	    return(line);
	}

	
	public static void main(String[] args) {

		// create player
		String name = "";
		while (name.length() == 0) {
			name = readLine("Please enter your name:");
		}
		Player player = new Player(name);
	    System.out.print("Let's play blackjack, " + player.getName() + "!\n");

		// set up the game
		Dealer dealer = new Dealer();
		Shoe shoe = new Shoe();
		
		// play rounds
		while (player.getChips() > 0) {
			
			// get player's bet
			int betSize = 0;
			Boolean validBet = false;
			while (!validBet) {
				String betInput = readLine("\nYou have " + player.getChips() + " chip" + pluralize(player.getChips()) + ". How many would you like to bet? ");
				betSize = parseNatural(betInput);
				if ((betSize <= 0) || (betSize > player.getChips())) {
					System.out.println("Sorry, " + player.getName() + " that bet is invalid. Please try again.");
				}
				else validBet = true;
			}
			System.out.println("OK, the bet is: " + betSize + "\nDealing cards...\n");
			
			// dealer's hand
			Hand dealerHand = new Hand();
			dealerHand.addCard(shoe.nextCard());
			dealerHand.addCard(shoe.nextCard());
			
			// player's hand
			Hand playerHand = new Hand();
			playerHand.addCard(shoe.nextCard());
			playerHand.addCard(shoe.nextCard());
			playerHand.addBet(player, betSize);
			
			// show dealers cards
			System.out.println("Dealer is showing:");
			dealerHand.displayPartial();

			// player keeps choosing while the hand is active
			while (playerHand.getActive()) {

				System.out.println(player.getName() + "'s hand:");
				playerHand.display();
				String choice = readLine("You can [H]it [S]tand or [Q]uit: ");
				
				// check first letter of input
				char letter = 0;
				if (choice.length() > 0) letter = choice.toLowerCase().charAt(0);
				switch(letter) {
				
					case 'h': // HIT
						Card c = shoe.nextCard();
						playerHand.addCard(c);
						System.out.println("You got the " + c.longName() + "\n");
						if (playerHand.getValue() > 21) {
							playerHand.setActive(false);
							System.out.println("BUSTED!");
						}								
				        break;
				        
					case 's': // STAND
						playerHand.setActive(false);
						System.out.println("STANDING at " + playerHand.getValue());
				        break;
				        				
					case 'q': // QUIT
						System.out.println("Bye! You walked away with " + player.getChips() + " chip" + pluralize(player.getChips()) + ".");
						System.exit(0);
				}
			}
		
		// player busted, show hands
		if (playerHand.getValue() > 21) {
			System.out.println("Your hand:");
			playerHand.display();
			System.out.println("Dealer had:");
			dealerHand.display();	
		}
		
		// dealer must play on
		else {
			System.out.println("Dealers hand:");
			dealerHand.display();
			
			while (dealerHand.getValue() < 17) {
				System.out.println("Dealer hits!");
				dealerHand.addCard(shoe.nextCard());
				dealerHand.display();
			}
			
			if (dealerHand.getValue() > 21) {
				System.out.println("Dealer busted!");
			}
			else if (dealerHand.getValue() == 21) {
				System.out.println("Dealer got 21!");
			}
			else {
				System.out.println("Dealer stands at " + dealerHand.getValue());
			}
		}				
		
		// tally 
		int winnings = 0;
		
		// dealer busted or player won
		if ((dealerHand.getValue() > 21) || 
			((playerHand.getValue() <= 21) && (playerHand.getValue() > dealerHand.getValue()))) {
				System.out.println("You won!");
				winnings = (2 * playerHand.getBet());
				System.out.println(dealer.randomPraise());
		}
		
		// push - player gets his money back
		else if ((dealerHand.getValue() <= 21) && (playerHand.getValue() <= 21) && (playerHand.getValue() == dealerHand.getValue())) {
				System.out.println("This hand was a push.");
				winnings = playerHand.getBet();
			}
		
		// player lost
		else {
			System.out.println("You lost.");
			System.out.println(dealer.randomTaunt());
		}
		player.addChips(winnings);
	} // while player has money
		
	System.out.println("Sorry, " + player.getName() + ", you're out of money. Game over!");
	}
}

				
				
				
				
				
				
				
				
				
				