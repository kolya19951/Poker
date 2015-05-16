package Tables;

import Cards.CommonCards;
import Cards.Deck;
import Players.Player;

/**
 * Created by Денис on 15.05.2015.
 */
public class Table {
    private Deck deck;
    private CommonCards commonCards;
    private Player[] players;

    Table() {
        deck = null;
        commonCards = null;
        players = new Player[9];
        for (int i = 0; i < 9; i++) {
            players[i] = null;
        }
    }

    void addPlayer(int pos) {
        if (pos >= 0 && pos < 9 && players[pos] != null) {
            players[pos] = new Player(pos);
        } else {
            System.out.println("Места нет");
        }
    }
}