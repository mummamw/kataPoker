
public class Card {
	
	private int numValue;
	private char value;
	private char suit;
	private String valueHolder;
	
	public Card(String Value){
		numValue = setNumValue(Value.charAt(0));
		value = Value.charAt(0);
		suit = Value.charAt(1);
		valueHolder = Value;
	}
	
	public Card(){
		numValue = -1;
		value = 'x';
		suit = 'x';
	}
	
	public char getValue(){
		return valueHolder.charAt(0);
	}
	
	public int getNumValue(){
		return numValue;
	}
	
	private int setNumValue(char Value){
		if(Character.isDigit(Value)){
			return Character.getNumericValue(Value);
		} else if(Value == 'T') {
			return 10;
		} else if(Value == 'J') {
			return 11;
		} else if(Value == 'Q') {
			return 12;
		} else if(Value == 'K') {
			return 13;
		} else if(Value == 'A') {
			return 14;
		}
		return -1;
	}
	
	public char printNumValue(){
		if(numValue<10){
			return Character.forDigit(numValue, 10);
		} else if(numValue == 10) {
			return 'T';
		} else if(numValue == 11) {
			return 'J';
		} else if(numValue == 12) {
			return 'Q';
		} else if(numValue == 13) {
			return 'K';
		} else if(numValue == 14) {
			return 'A';
		}
		return 'X';
	}
	
	public String printCard(){	
		return String.valueOf(value) + String.valueOf(suit);
	}
	
	public char getSuit(){
		return suit;
	}
	
}
