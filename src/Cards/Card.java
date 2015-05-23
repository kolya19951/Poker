package Cards;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Card {
    public byte rank;
    public byte suit;

    public Card() {
        rank = -1;
        suit = -1;
    }
    public Card(byte r, byte s) {
        rank = r;
        suit = s;
    }
    public Card(Card c) {
        rank = c.rank;
        suit = c.suit;
    }

    public String toString() {
        String s = "";
        if (rank >= 11 && rank <= 14) {
            switch (rank) {
                //case 10 :
                //   s = "T";
                //    break;
                case 11 :
                    s = "J";
                    break;
                case 12 :
                    s = "Q";
                    break;
                case 13 :
                    s = "K";
                    break;
                case 14 :
                    s = "A";
                    break;
            }
        } else s = rank <= 10 && rank >= 2 ? Byte.toString(rank) : "N";
        s = suit >= 0 && suit <= 3 ? s + "," + Byte.toString(suit) : s + "," + "N";
        return s;
    }
}