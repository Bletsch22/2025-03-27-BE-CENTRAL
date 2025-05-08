public class Card {
    //BLUEPRINT

    private final int value; //2-14 Value Cards
    private final String suit;// Hearts Diamonds Spade Clubs
    private final String name;

    //Constructor
    public Card(int value, String suit, String name) {
        this.value = value;
        this.suit = suit;
        this.name = name;
    }

    //getters to get the data from Card type

    public int getValue(){
        return value;
    }
    public String getSuit() {
        return suit;
    }
    public String getName(){
        return name;
    }
    //no setters, we don't want to modify the card

    @Override
    public String toString(){
        return name + "of " + suit; // when the card value is over 10, this will update with a face value
    }

    public void describe(){
        if (value < 10) {
            System.out.print(value + " of " + suit);
        } else {
            System.out.print(name + " of " + suit);
        }
    }
}
