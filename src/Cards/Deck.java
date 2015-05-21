package Cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Deck {
    private Pack pack = new Pack();
    private DeckElement head;

    public Deck () {
        reset();
    }

    public void reset() {
        ArrayList<Card> cardsList = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cardsList.add(pack.cards[j][i]);
            }
        }
        Random rand = new Random();
        head = null;
        int index;
        for (int i = 52; i > 0; i--) {
            index = Math.abs(rand.nextInt() % i);
            this.add(cardsList.get(index));
            cardsList.remove(index);
        }
    }

    private void add(Card data) {
        DeckElement a = new DeckElement();
        a.data = data;
        if(head == null) {
            head = a;
        }
        else {
            a.next = head;
            head = a;
        }
    }

    public Card retrieve() {
        Card c = head.data;
        head = head.next;
        return c;
    }
}

class DeckElement {
    DeckElement next;
    Card data;

    DeckElement () {
        next = null;
        data = null;
    }

    DeckElement (Card data) {
        next = null;
        this.data = data;
    }
}

class Pack {
    public Card[][] cards = new Card[13][4];

    public Pack () {
        for (byte i = 2; i <= 14; i++) {
            cards[i - 2][0] = new Card(i, (byte) 0);
            cards[i - 2][1] = new Card(i, (byte) 1);
            cards[i - 2][2] = new Card(i, (byte) 2);
            cards[i - 2][3] = new Card(i, (byte) 3);
        }
    }
}
