package Games;

import Players.Player;
import Tables.Table;

/**
 * Created by Денис on 16.05.2015.
 */
public class Game {
    Table table;

    public Game () {
        table = new Table();
    }

    public void addPlayer (Player p, int position) {
        p.SetPosition(position);
        table.addPlayer(p);
    }
    public void DealHands () {
        table.DealHands();
    }
    public void DealFlop () {
        table.DealFlop();
    }
    public void DealTurn () {
        table.DealTurn();
    }
    public void DealRiver () {
        table.DealRiver();
    }
    public void ShowCards () {
        table.ShowCards();
    }
    public void ResetDeck () {
        table.ResetDeck();
    }
    public void Fold (int pos) {
        table.Fold(pos);
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

    private void BetRound () {
        int gnida = table.button;
        do {
            gnida++;
            gnida = gnida % 9;
            //гнида ходи
        } while (gnida != table.button);
    }
}
