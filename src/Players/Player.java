package Players;

import Cards.Hand;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Player {
    private Hand hand;
    private int position;
    private int bankroll;

    public Player () {
        hand = null;
    }

    public Player (int pos) {
        hand = null;
        position = pos;
    }

    void GiveAHand(Hand h) {
        hand = h;
    }

    Hand TakeAHand() {
        Hand tmp = hand;
        hand = null;
        return tmp;
    }

    void Fold () {
        hand = null;
    }
}
