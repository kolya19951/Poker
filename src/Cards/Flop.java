package Cards;

/**
 * Created by Δενθρ on 27.05.2015.
 */
public class Flop {
    public Card c1;
    public Card c2;
    public Card c3;

    Flop () {
        c1 = null;
        c2 = null;
        c3 = null;
    }
    Flop(Card c1, Card c2, Card c3) {
        SetFlop(c1, c2, c3);
    }
    void SetFlop (Card c1, Card c2, Card c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    /*public void show() {
        c1.GetInfo();
        c2.GetInfo();
        c3.GetInfo();
    }*/
    void Destroy () {
        c1 = null;
        c2 = null;
        c3 = null;
    }
}