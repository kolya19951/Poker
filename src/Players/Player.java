package Players;

import Cards.Hand;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Player {
    Hand hand;

    Player () {
        hand = null;
    }
    Player (int brll) {
        hand = null;
    }

    void GiveAHand(Hand h) {
        hand = h;
    }

    Hand TakeAHand() {
        Hand tmp = hand;
        hand = null;
        return tmp;
    }
}
