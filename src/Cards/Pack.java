package Cards;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Pack {
    public Card[][] cards = new Card[13][4];

    public Pack () {
        for (int i = 2; i <= 14; i++) {
            cards[i - 2][0] = new Card(i, "hearts");
            cards[i - 2][1] = new Card(i, "tiles");
            cards[i - 2][2] = new Card(i, "clovers");
            cards[i - 2][3] = new Card(i, "pikes");
        }
    }
}
