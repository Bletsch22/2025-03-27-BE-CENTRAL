import java.util.*;

public class Deck {
    private final List<Card> cards;

    //Constructor a standard deck of cards (52)
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        Map<Integer,String> faceMap = new HashMap<>();
        faceMap.put(11, "Jack");
        faceMap.put(12, "Queen");
        faceMap.put(13, "King");
        faceMap.put(14, "Ace");

        for (String suit : suits){
            for(int rank : values){
                String displayName = faceMap.getOrDefault(rank, String.valueOf(rank));
                Card card = new Card(rank, suit, displayName);
                cards.add(card);//adding the card to the deck
            }
        }
    }
    public void shuffle(){ // randomizes card
    Collections.shuffle(cards);
    }

    public Card draw() {
    if (!cards.isEmpty()){
        return cards.removeFirst();
    } else {
        return null;
    }
    }
}

