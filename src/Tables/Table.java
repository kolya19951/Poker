package Tables;

import Cards.CommonCards;
import Cards.Deck;
import Cards.Hand;
import Players.Player;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Table {
    private Deck deck;
    private CommonCards commonCards;
    private Player[] players;
    public int button;
    private Dealer dealer;

    public Table() {
        deck = new Deck();
        commonCards = new CommonCards();
        players = new Player[9];
        for (int i = 0; i < 9; i++) {
            players[i] = null;
        }
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

    public void DealFlop () {
        commonCards.SetFlop(deck.retrieve(), deck.retrieve(), deck.retrieve());
    }
    public void DealTurn () {
        commonCards.SetTurn(deck.retrieve());
    }
    public void DealRiver () {
        commonCards.SetRiver(deck.retrieve());
    }
    public void DealHands () {
        for (int i = 0; i < 9; i++) {
            if (players[i] != null) {
                Hand h = new Hand(deck.retrieve(), deck.retrieve());
                players[i].GiveAHand(h);
            }
        }
    }

    public void Fold (int pos) {
        players[pos].Fold();
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
    public void ResetDeck() {
        deck.reset();
    }
}