package Tables;

import Cards.Deck;
import Cards.Hand;

/**
 * Created by Денис on 16.05.2015.
 */
public class Dealer {

    private Table table;
    private Deck deck;

    protected Dealer () {
        table = new Table();
        deck = new Deck();
    }

    public void ResetDeck() {
        deck.reset();
    }
    public void DealFlop () {
        commonCards.SetFlop(deck.retrieve(), deck.retrieve(), deck.retrieve());
    }
    public void DealTurn () {
        commonCards.SetTurn(deck.retrieve());
    }
    public void DealRiver () {
        commonCards.SetRiver(deck.retrieve());
    }
    public void DealHands () {
        for (int i = 0; i < 9; i++) {
            if (players[i] != null) {
                Hand h = new Hand(deck.retrieve(), deck.retrieve());
                players[i].GiveAHand(h);
            }
        }
    }
    public void Play () {
        //добавить игроков

        //----------------
        while (true) {//розигрыш
            table.ResetDeck();
            //блайнды
            table.DealHands();
            //Префлоп
            BetRound();
            table.DealFlop();
            //Постфлоп
            BetRound();
            table.DealTurn();
            //Тёрн
            BetRound();
            table.DealRiver();
            //Ривер
            BetRound();
            //Вскрытие
            //Покидает ли кто-нибудь стол?
            //Садится ли кто-нибудь?
            table.ButtonMove();
        }
    }
}
