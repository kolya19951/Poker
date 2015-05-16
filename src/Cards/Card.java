package Cards;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Card {
    private Suit suit;
    private Rank rank;

    public Card (int r, String s) {
        rank = new Rank(r);
        suit = new Suit(s);
    }

    public void GetInfo () {
        int r = GetRank();
        String s = GetSuit();
        System.out.println(r + " " + s);
    }
    private int GetRank() {
        return rank.GetRank();
    }
    private String GetSuit () {
        return suit.GetSuit();
    }

    public void SetRank (int r) {
        rank = new Rank(r);
    }
}

class Suit {
    private byte index;

    Suit (String name) {
        switch (name) {
            case "hearts": {
                index = 0;
                break;
            }
            case "tiles": {
                index = 1;
                break;
            }
            case "clovers": {
                index = 2;
                break;
            }
            case "pikes": {
                index = 3;
                break;
            }
            default:
                index = 4;
                break;
        }
    }

    String GetSuit () {
        switch (index) {
            case 0: {
                return "hearts";
            }
            case 1: {
                return "tiles";
            }
            case 2: {
                return "clovers";
            }
            case 3: {
                return "pikes";
            }
            default:
                return "NAN";
        }
    }
}

class Rank {
    private byte index;

    Rank (int rank) {
        index = (byte) (rank - 2);
    }

    int GetRank () {
        return index + 2;
    }
}