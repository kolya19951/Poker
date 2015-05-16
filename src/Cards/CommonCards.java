package Cards;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class CommonCards {
    public Flop flop;
    public Card turn;
    public Card river;

    public CommonCards() {
        flop = null;
        turn = null;
        river = null;
    }

    public void SetFlop (Card c1, Card c2, Card c3) {
        flop = new Flop(c1, c2, c3);
    }

    public void SetTurn (Card c) {
        turn = c;
    }

    public void SetRiver (Card c) {
        river = c;
    }

    public void ShowFlop () {
        flop.show();
    }
    public void ShowTurn () {
        turn.GetInfo();
    }
    public void ShowRiver () {
        river.GetInfo();
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

    public void show() {
        c1.GetInfo();
        c2.GetInfo();
        c3.GetInfo();
    }
}
