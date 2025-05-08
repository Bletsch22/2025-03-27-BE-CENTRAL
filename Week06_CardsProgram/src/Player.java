import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> hand;
    private int score = 0;
    private final String name;

    //Constructor
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new ArrayList<>();
    }

    public void describe(){ // Prints out player name and score and cards played in round
        System.out.println("Player: " + name + " Score: " + score);
        for (Card card: hand) {
            card.describe();
        }
    }
    public Card flip(){ // responsible for removing and returning the top card from the players hand
        if(!hand.isEmpty()){
            return hand.removeFirst();
        }else {
        return null;
        }
    }
    public void draw(Deck deck) {
        Card drawnCard = deck.draw();
        if (drawnCard != null) {
            hand.add(drawnCard);
        }
    }
    public void incrementScore(){ //Increments the score of each player
        score++;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
}
