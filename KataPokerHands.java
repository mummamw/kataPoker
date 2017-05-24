import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Input format from http://codingdojo.org/kata/PokerHands/
 * Input File is taken as first argument when running the file.
 * Test file provided is testHands.txt
 */

public class KataPokerHands {

	public static void comparitor(Hand white, Hand black){
		if(white.getHandRank() > black.getHandRank()){
			white.printWinCase();
		} else if(white.getHandRank() < black.getHandRank()){
			black.printWinCase();
		} else { //Tie Breaking
			switch(white.getHandRank()){
				case 0: // High Card
					for(int i = 4; i>0; i--){
						if(white.cards[i].getNumValue()>black.cards[i].getNumValue()){
							System.out.println(white.player + " wins. - with High Card "
							+ white.cards[i].printNumValue());
							break;
						} else if(white.cards[i].getNumValue()<black.cards[i].getNumValue()){
							System.out.println(black.player + " wins. - with High Card " 
							+ black.cards[i].printNumValue());
							break;
						}
					}
					break;
				case 1: // Pair
					int whitePairIndex = white.getPair1Index();
					int blackPairIndex = black.getPair1Index();
					if(white.cards[whitePairIndex].getNumValue() 
							> black.cards[blackPairIndex].getNumValue()) {
							System.out.println(white.player + " wins. - with Pair of: " 
								+ white.cards[whitePairIndex].printNumValue());
							break;
						}
					else if(white.cards[whitePairIndex].getNumValue() 
							< black.cards[blackPairIndex].getNumValue()) {
							System.out.println(black.player + " wins. - with Pair of: " 
								+ black.cards[blackPairIndex].printNumValue());
							break;
						}
					for(int i = 4; i>(-1); i--){
						if(white.cards[i].getNumValue() > black.cards[i].getNumValue()){
							System.out.println(white.player + " wins.- with high Card " 
									+ white.cards[i].printNumValue());
							break;
						} else if(white.cards[i].getNumValue() < black.cards[i].getNumValue()){
							System.out.println(black.player + " wins.- high Card " 
									+ black.cards[i].printNumValue());
							break;
						}
					}
					break;
				case 2: // Two Pair
					int whitePair1Index = white.getPair1Index();
					int whitePair2Index = white.getPair2Index();
					int blackPair1Index = black.getPair1Index();
					int blackPair2Index = black.getPair2Index();
					if(white.cards[whitePair1Index].getNumValue() 
							> black.cards[blackPair1Index].getNumValue()) {
							System.out.println(white.player + " wins. - with two pair of: " 
								+ white.cards[whitePair1Index].printNumValue()
								+ " and " + white.cards[whitePair2Index].printNumValue());
							break;
					} else if(white.cards[whitePair1Index].getNumValue() 
							< black.cards[blackPair1Index].getNumValue()) {
							System.out.println(black.player + " wins. - with two pair of: " 
								+ black.cards[blackPair1Index].printNumValue()
								+ " and " + black.cards[blackPair2Index].printNumValue());
							break;
					} else if(white.cards[whitePair2Index].getNumValue() 
							> black.cards[blackPair2Index].getNumValue()) {
							System.out.println(white.player + " wins. - with two pair of: " 
								+ white.cards[whitePair1Index].printNumValue()
								+ " and " + white.cards[whitePair2Index].printNumValue());
							break;
					} else if(white.cards[whitePair2Index].getNumValue() 
							< black.cards[blackPair2Index].getNumValue()) {
							System.out.println(black.player + " wins. - with two pair of: " 
								+ black.cards[blackPair1Index].printNumValue()
								+ " and " + black.cards[blackPair2Index].printNumValue());
							break;
					}
					break;
				case 3: // Three of a kind
					int whiteThreeOfAKindIndex = white.getThreeOfAKindIndex();
					int blackThreeOfAKindIndex = black.getThreeOfAKindIndex();
					if(white.cards[whiteThreeOfAKindIndex].getNumValue() 
							> black.cards[blackThreeOfAKindIndex].getNumValue()) {
							System.out.println(white.player + " wins. - with Three of a kind of: " 
								+ white.cards[whiteThreeOfAKindIndex].printNumValue());
							break;
						}
					else if(white.cards[whiteThreeOfAKindIndex].getNumValue() 
							< black.cards[blackThreeOfAKindIndex].getNumValue()) {
							System.out.println(black.player + " wins. - with Three of a kind of: " 
								+ black.cards[blackThreeOfAKindIndex].printNumValue());
							break;
						}
					break;
				case 4: // Straight
					if(white.cards[4].getNumValue() > black.cards[4].getNumValue()){
						System.out.println(white.player + " wins. - with Straight: " 
								+ white.cards[4].printNumValue() + " high");
						break;
					} else if(white.cards[4].getNumValue() < black.cards[4].getNumValue()){
						System.out.println(black.player + " wins. - with Straight: " 
								+ black.cards[4].printNumValue() + " high");
						break;
					}
					break;
				case 5: // Flush
					if(white.cards[4].getNumValue() > black.cards[4].getNumValue()){
						System.out.println(white.player + " wins. - with Flush: " + white.cards[4].getSuit());
						break;
					} else if(white.cards[4].getNumValue() < black.cards[4].getNumValue()){
						System.out.println(black.player + " wins. -with Flush: " + black.cards[4].getSuit());
						break;
					}
					break;
				case 6: // Full House
					int whiteFullHouseThreeOfKind = white.getFullHouseThreeOfKind();
					int whiteFullHousePair = white.getfullHousePair();
					int blackFullHouseThreeOfKind = black.getFullHouseThreeOfKind();
					int blackFullHousePair = black.getfullHousePair();
					if(white.cards[whiteFullHouseThreeOfKind].getNumValue() 
							> black.cards[blackFullHouseThreeOfKind].getNumValue()) {
							System.out.println(white.player + " wins. - with Full House " +  
								white.cards[whiteFullHouseThreeOfKind].printNumValue() + " over " +
								white.cards[whiteFullHousePair].printNumValue());
							break;
					} else if(white.cards[whiteFullHouseThreeOfKind].getNumValue() 
							< black.cards[blackFullHouseThreeOfKind].getNumValue()) {
							System.out.println(black.player + " wins. - with Full House " +  
								black.cards[blackFullHouseThreeOfKind].printNumValue() + " over " +
								black.cards[blackFullHousePair].printNumValue());
							break;
					} else if(white.cards[whiteFullHousePair].getNumValue() 
							> black.cards[blackFullHousePair].getNumValue()) {
							System.out.println(white.player + " wins. - with Full House " +  
								white.cards[whiteFullHouseThreeOfKind].printNumValue() + " over " +
								white.cards[whiteFullHousePair].printNumValue());
							break;
					} else if(white.cards[whiteFullHousePair].getNumValue() 
							< black.cards[blackFullHousePair].getNumValue()) {
							System.out.println(black.player + " wins. - with Full House " +  
								black.cards[blackFullHouseThreeOfKind].printNumValue() + " over " +
								black.cards[blackFullHousePair].printNumValue());
							break;
					}
					break;
				case 7: // Four of a kind
					int whiteFourOfAKindIndex = white.getFourOfAKindIndex();
					int blackFourOfAKindIndex = black.getFourOfAKindIndex();
					if(white.cards[whiteFourOfAKindIndex].getNumValue() 
							> black.cards[blackFourOfAKindIndex].getNumValue()) {
							System.out.println(white.player + " wins. - with Four of a kind of: " 
								+ white.cards[whiteFourOfAKindIndex].printNumValue());
							break;
						}
					else if(white.cards[whiteFourOfAKindIndex].getNumValue() 
							< black.cards[blackFourOfAKindIndex].getNumValue()) {
							System.out.println(black.player + " wins. - with Four of a kind of: " 
								+ black.cards[blackFourOfAKindIndex].printNumValue());
							break;
						}
					break;
				case 8: // Straight Flush
					if(white.cards[4].getNumValue() > black.cards[4].getNumValue()){
						System.out.println(white.player + " wins. - with Straight Flush: " 
								+ white.cards[4].printNumValue() + " high");
						break;
					} else if(white.cards[4].getNumValue() < black.cards[4].getNumValue()){
						System.out.println(black.player + " wins. - with Straight Flush: " 
								+ black.cards[4].printNumValue() + " high");
						break;
					}
					break;
			}
		}
	}
			
	public static void main(String[] args){
		String fileName = args[0];
		File file = new File(fileName);
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while((line = br.readLine()) != null){
				Hand playerOne = new Hand(line.substring(0, 21));
				Hand playerTwo = new Hand(line.substring(22));
				System.out.println(line);
				comparitor(playerOne, playerTwo);
			}	
		} catch(FileNotFoundException e){
			System.out.println("The file " + fileName +" was not found.");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
