package Cards;

/**
 * Created by Δενθρ on 15.05.2015.
 */
public class Deck {
    private DeckElement head;

    public Deck () {
        head = null;
    }

    public void add(Card data) {
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
        //head = head.next;
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
