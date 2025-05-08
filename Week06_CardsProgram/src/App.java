public class App {
    public static void main(String[] args) {
        //a) Instantiate a deck and two players and shuffle the deck.
        Deck deck = new Deck();
        deck.shuffle();

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        // b) Deal 26 cards to both players (52 in total)
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                player1.draw(deck);
            } else {
                player2.draw(deck);
            }
        }
        //c) play 26 rounds
        for (int i = 0; i < 26; i++) {

            Card card1 = player1.flip();
            Card card2 = player2.flip();
            System.out.println("     Round " + (i + 1));
            System.out.println("...Drawing Cards...");

            System.out.print(player1.getName() + ": ");
            card1.describe();

            System.out.println();

            System.out.print(player2.getName() + ": ");
            card2.describe();

            System.out.println();
            System.out.println("-----------------------");


            //d compare card values and increment score.
            if (card1.getValue() > card2.getValue()) {
                player1.incrementScore();
                System.out.println("Player wins this round");
                System.out.println("    Current Scores");
                System.out.println(player1.getName() + ": " + player1.getScore());
                System.out.println(player2.getName() +": " + player2.getScore());
                System.out.println("=======================");
            } else if (card2.getValue() > card1.getValue()){
                System.out.println("Player2 wins this round");
                System.out.println("    Current Scores");
                player2.incrementScore();
                System.out.println(player1.getName() + ": " + player1.getScore());
                System.out.println(player2.getName() +": " + player2.getScore());
                System.out.println("=======================");
            } else {
                System.out.println("This round is a draw");
                System.out.println("    Current Scores");
                System.out.println(player2.getName() +": " + player2.getScore());
                System.out.println(player2.getName() +": " + player2.getScore());
                System.out.println("=======================");
            }
        }
        //e) Compare final scores
        System.out.println("     Final Scores");
        System.out.println(player1.getName() +": "+ player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println("=======================");

        //f) Print the final Score
        if (player1.getScore()> player2.getScore()){
            System.out.println(player1.getName() + " wins the game");
        }else if (player2.getScore()> player1.getScore()) {
            System.out.println(player2.getName() + " wins the game");
        }else{
            System.out.println("The game is a draw.");
        }
    }

}