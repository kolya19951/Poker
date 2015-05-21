import Lan.Server;
import Tables.Dealer;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        dealer.seatClients();
        dealer.Play();
        //new players
        /*Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        game1.addPlayer(player1, 0);
        game1.addPlayer(player2, 3);
        game1.addPlayer(player3, 2);
        game1.addPlayer(player4, 7);
        game1.ResetDeck();
        game1.DealHands();
        game1.DealFlop();
        game1.DealTurn();
        game1.DealRiver();
        game1.ShowCards();
        game1.Fold(3);
        game1.ShowCards();*/
    }
}
