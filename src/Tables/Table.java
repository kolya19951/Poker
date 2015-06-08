package Tables;

import Cards.*;
import Players.Player;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Table {
    public int BB;
    public CommonCards commonCards;
    public Player[] players;
    public int button;
    private int bank;
    private int currentBet;

    public Table() {
        BB = 2;
        button = 0;
        bank = 0;
        commonCards = new CommonCards();
        players = new Player[9];
        for (int i = 0; i < 9; i++) players[i] = null;
    }

    public void addPlayer(Player p) {
        //if (p != null) {
        //if (p.getPosition() != -1) {
        //    players[p.GetPosition()] = p;
        //}
    }
    public void addToBank (int b) {
        bank += b;
    }
    public int getCurrentBet() {
        return currentBet;
    }
    public void setCurrentBet(int b) {
        currentBet = b;
    }
    public int getBank () {
        return bank;
    }
    public void addPlayers (Player... players) {
        //for (int i = 0; i < players.length; i++) {
            //players[i] = new Player();
            //players[i].Init();
            //addPlayer(players[i]);
        //}
        this.players = players;
    }
    public void setBank (int b) {
        bank = b;
    }
    public void DealFlop (Card c1, Card c2, Card c3) {
        commonCards.SetFlop(c1, c2, c3);
    }
    public void DealTurn (Card c) {
        commonCards.SetTurn(c);
    }
    public void DealRiver (Card c) {
        commonCards.SetRiver(c);
    }
    public void ButtonMove () {
        do {
            button++;
            button =  button % 6;
        } while (players[button].getPosition() == -1);
    }
    /*public void ShowCards () {
        System.out.println("flop : ");
        //commonCards.ShowFlop();
        System.out.println();
        System.out.println("turn : ");
        //commonCards.ShowTurn();
        System.out.println();
        System.out.println("river : ");
        //commonCards.ShowRiver();
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (players[i] != null) {
                System.out.println("Player" + i + " hand: ");
                players[i].ShowHand();
                System.out.println();
            }
        }
    }*/
}