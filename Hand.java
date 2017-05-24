public class Hand {

	public String player;
	Card[] cards = new Card[5];
	private int handRank;
    private int pair1Index;
    private int pair2Index;
    private int threeOfAKindIndex;
    private int fullHouseThreeOfKindIndex;
    private int fullHousePairIndex;
    private int fourOfAKindIndex;
	
	public Hand(){
	}
	
	public Hand(String line){
		String[] split = line.split(" ");
		setPlayer(split[0]);
		for(int i = 0; i<5; i++){
			cards[i] = new Card(split[i+1]);
		}
		organizeCards();
		evaluateHand();
	}
	
	public void setPlayer(String value){
		player = value.substring(0,(value.length()-1));
	}
	
	public String getPlayer(){
		return player;
	}
	
	public int getHandRank(){
		return handRank;
	}

	// InsertionSort ranking cards in ascending order
	private void organizeCards(){
		Card temp;
		for(int i=1; i<cards.length; i++) {
			for(int j = i; j > 0; j--){
				if(cards[j].getNumValue() < cards[j-1].getNumValue()){
					temp = cards[j];
					cards[j] = cards[j-1];
					cards[j-1] = temp;
				}
			}
		}
	}

	private void evaluateHand(){
		// High Card
		handRank = 0;
		// First pair evaluation 
		for(int i=0; i<4; i++){
			if(cards[i].getNumValue() == cards[i+1].getNumValue()){
				pair1Index = i;
				handRank = 1;
				i = 4; 
			}
		}
		// Second pair evaluation
		if(handRank == 1) {
			for(int i=pair1Index+1; i<3; i++){
				if(cards[i].getNumValue() == cards[i+1].getNumValue()){
					pair2Index = i;
					handRank = 2;
				}
			}
			if(cards[pair1Index].getNumValue() < cards[pair2Index].getNumValue()){
				int temp = pair1Index;
				pair1Index = pair2Index;
				pair2Index = temp;
			}
		}
		//Three of a kind evaluation
		for(int i=0; i<3; i++){
			if(cards[i].getNumValue() == cards[i+1].getNumValue() 
				&& cards[i].getNumValue() == cards[i+2].getNumValue()){
					threeOfAKindIndex = i;
					handRank = 3;
			}
		}
		// Straight Checker
		if(cards[1].getNumValue() ==  cards[0].getNumValue()+1
				&& cards[2].getNumValue() ==  cards[0].getNumValue()+2
				&& cards[3].getNumValue() ==  cards[0].getNumValue()+3
				&& cards[4].getNumValue() ==  cards[0].getNumValue()+4)
			{
			handRank = 4;
		}
		// Flush Check
		if(cards[0].getSuit() ==  cards[1].getSuit()
				&& cards[2].getSuit() == cards[3].getSuit()
				&& cards[0].getSuit() == cards[3].getSuit()
				&& cards[0].getSuit() == cards[4].getSuit())
			{
			handRank = 5;
		}
		// Full house checker
		if(cards[0].getNumValue() == cards[1].getNumValue() 
				&& cards[0].getNumValue() == cards[2].getNumValue()
				&& cards[3].getNumValue() == cards[4].getNumValue()){
			handRank = 6;
			fullHouseThreeOfKindIndex = 0;
			fullHousePairIndex = 3;
		}
		if(handRank != 6 && 
				cards[0].getNumValue() == cards[1].getNumValue() 
				&& cards[2].getNumValue() == cards[3].getNumValue()
				&& cards[3].getNumValue() == cards[4].getNumValue()){
			handRank = 6;
			fullHouseThreeOfKindIndex = 3;
			fullHousePairIndex = 0;
		}	
		// Four of a kind
		for(int i = 0; i<2; i++){
			if(cards[i].getNumValue() == cards[i+1].getNumValue()
				&& cards[i].getNumValue() == cards[i+2].getNumValue()
				&& cards[i].getNumValue() == cards[i+3].getNumValue()){
				handRank = 7;
				fourOfAKindIndex = i;
			}
		}
		//straight flush
		if(handRank == 4){ // Use knowledge of it being a straight  
			if(cards[0].getSuit() ==  cards[1].getSuit()
					&& cards[2].getSuit() == cards[3].getSuit()
					&& cards[0].getSuit() == cards[3].getSuit()
					&& cards[0].getSuit() == cards[4].getSuit()
					){
				handRank = 8;
			}
		}

	}
	
	public void printHand(){
		for(int i = 0; i<5; i++){
			System.out.print(cards[i].printCard() + " ");
		}
		System.out.println();
	}
	
	public int getPair1Index(){
		return pair1Index;
	}
	
	public int getPair2Index(){
		return pair2Index;
	}
	
	public int getThreeOfAKindIndex(){
		return threeOfAKindIndex;
	}
	
	public int getFourOfAKindIndex(){
		return fourOfAKindIndex;
	}
	
	public int getFullHouseThreeOfKind(){
		return fullHouseThreeOfKindIndex;
	}
	
	public int getfullHousePair(){
		return fullHousePairIndex;
	}

	public void printWinCase(){
		System.out.print(player + " wins. - with ");
		switch(handRank){
			case 0:
				System.out.println("High Card " + cards[4].printNumValue());
				break;
			case 1:
				System.out.println("Pair of: " + cards[pair1Index].printNumValue());
				break;
			case 2:
				System.out.println("Two pair with: " + cards[pair1Index].printNumValue() 
							+ " and " + cards[pair2Index].printNumValue());
				break;
			case 3:
				System.out.println("Three of a kind: " + cards[threeOfAKindIndex].printNumValue());
				break;
			case 4:
				System.out.println("Straight: " + cards[4].printNumValue() + " high");
				break;
			case 5:
				System.out.println("Flush: " + cards[0].getSuit());
				break;
			case 6:
				System.out.println("Full House: " + cards[fullHouseThreeOfKindIndex].printNumValue() 
							+ " over " + cards[fullHousePairIndex].printNumValue());
				break;
			case 7:
				System.out.println("Four of a kind: " + cards[fourOfAKindIndex].printNumValue());
				break;
			case 8:
				System.out.println("Straight Flush: " + cards[0].printNumValue() + " high of "
									+ cards[4].getSuit() +"'s");
				break;
		}
	}
	
}
