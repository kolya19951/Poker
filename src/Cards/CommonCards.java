package Cards;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class CommonCards {
    Flop flop;
    Card turn;
    Card river;

    CommonCards() {
        flop = null;
        turn = null;
        river = null;
    }

    void SetFlop (Card c1, Card c2, Card c3) {
        flop = new Flop(c1, c2, c3);
    }

    void SetTurn (Card c) {
        turn = c;
    }

    void SetRiver (Card c) {
        river = c;
    }
}

class Flop {
    public Card c1;
    public Card c2;
    public Card c3;

    Flop(Card c1, Card c2, Card c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }
}
