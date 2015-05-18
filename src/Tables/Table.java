package Tables;

import Cards.CommonCards;
import Cards.Deck;
import Cards.Hand;
import Players.Player;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Table {
    private CommonCards commonCards;
    private Player[] players;
    private int button;

    public Table() {
        commonCards = new CommonCards();
        players = new Player[9];
        for (int i = 0; i < 9; i++) players[i] = null;
    }

    public void addPlayer(Player p) {
            players[p.GetPosition()] = p;
    }
    public void ButtonMove () {
        do {
            button++;
            button =  button % 9;
        } while (players[button] == null);
    }
    public void ShowCards () {
        System.out.println("flop : ");
        commonCards.ShowFlop();
        System.out.println();
        System.out.println("turn : ");
        commonCards.ShowTurn();
        System.out.println();
        System.out.println("river : ");
        commonCards.ShowRiver();
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (players[i] != null) {
                System.out.println("Player" + i + " hand: ");
                players[i].ShowHand();
                System.out.println();
            }
        }
    }
}