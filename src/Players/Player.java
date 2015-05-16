package Players;

import Cards.Hand;

/**
 * Created by ����� on 15.05.2015.
 */
public class Player {
    private Hand hand;
    private int position;
    private int bankroll;

    public Player () {
        hand = null;
        position = -1;
    }

    public Player (int pos) {
        hand = null;
        position = pos;
    }

    public void SetPosition (int pos) {
        position = pos;
    }
    public int GetPosition () {
        return position;
    }

    public void GiveAHand(Hand h) {
        hand = h;
    }

    Hand TakeAHand() {
        Hand tmp = hand;
        hand = null;
        return tmp;
    }

    public void Fold () {
        hand = null;
    }
    public void ShowHand () {
        hand.Show();
    }
}
