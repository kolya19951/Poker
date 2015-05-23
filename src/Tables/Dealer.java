package Tables;

import Cards.Deck;
import Cards.Hand;
import Lan.Server;
import Players.Player;

/**
 * Created by Денис on 16.05.2015.
 */
public class Dealer {

    private Table table;
    private Deck deck;

    public Dealer () {
        table = new Table();
        deck = new Deck();
    }

    public void seatClients () {
        //добавляем нового игрока
        Server server = new Server();
        Player[] players = server.ConnectPlayers();
        table.addPlayers(players);
        for (int i = 0; i < 6; i++) {
            //if (table.players[i] != null) {
            if (table.players[i].getPosition() != -1) {
                table.players[i].send("game start info");
                for (int j = 0; j < 6; j++) {
                    //if (players[j] == null) {
                    if (players[j].getPosition() == -1) {
                        //players[i].send("player");
                        players[i].sendInt(j);
                        players[i].send("free seat");
                    } else {
                        //players[i].send("player");
                        players[i].sendInt(j);
                        players[i].send(table.players[j].getLogin());
                        players[i].initAMoney(1000);
                    }
                }
            }
        }
    }

    public void ResetDeck() {
        deck.reset();
    }
    public void DealFlop () {
        table.DealFlop(deck.retrieve(), deck.retrieve(), deck.retrieve());
    }
    public void DealTurn () {
        table.DealTurn(deck.retrieve());
    }
    public void DealRiver () {
        table.DealRiver(deck.retrieve());
    }
    public void DealHands () {
        for (int i = 0; i < 6; i++) {
            //if (table.players[i] != null) {
            if (table.players[i].getPosition() != -1) {
                table.players[i].send("you hand");
                Hand h = new Hand(deck.retrieve(), deck.retrieve());
                table.players[i].GiveAHand(h);
                table.players[i].isInGame = true;
            }
        }
    }
    public void Play () {
        //добавить игроков
        //----------------
        table.bank = 0;
        while (true) {//розигрыш
            table.bank = 0;
            ResetDeck();
            //блайнды
            DealHands();
            //Префлоп
            System.out.println("Preflop started");
            Round();
            System.out.println("Preflop end");
            DealFlop();
            System.out.println("Postflop started");
            //Постфлоп
            Round();
            System.out.println("Postflop ended");
            DealTurn();
            System.out.println("Turn started");
            //Тёрн
            Round();
            System.out.println("Turn finished");
            DealRiver();
            System.out.println("River started");
            //Ривер
            Round();
            System.out.println("Kto pobedil sami reshayte");
            //Вскрытие
            //Покидает ли кто-нибудь стол?
            //Садится ли кто-нибудь?
            table.ButtonMove();
        }
    }
    private void Round() {
        int gnida = table.button;
        int currentBet = 0;
        int previousBet;
        gnida = gnidaMove(gnida);
        table.players[gnida].send("Small blind");
        previousBet = currentBet;
        currentBet += table.BB/2;
        table.bank += currentBet;
        System.out.println(table.players[gnida].getLogin()+ " is Small Blind");
        System.out.println("Bank" + table.bank);
        gnida = gnidaMove(gnida);
        table.players[gnida].send("Big blind");
        previousBet = currentBet;
        currentBet += table.BB/2;
        table.bank += currentBet;
        System.out.println(table.players[gnida].getLogin()+ " is Big Blind");
        System.out.println("Bank" + table.bank);
        do {
            previousBet = currentBet;
            gnida = gnidaMove(gnida);
            //Калян, напиши тут код, который пошлет запрос игроку gnida запрос на ход
            table.players[gnida].send("You turn");
            // ждем ответа от игрока гнида
            int massage;
            massage = table.players[gnida].GetInt();
            if (massage == -1) {
                table.players[gnida].Fold();
                System.out.println(table.players[gnida].getLogin() + " fold");
            } else {
                if (massage == 0) {
                    System.out.println(table.players[gnida].getLogin() + " check/call");
                } else {
                    System.out.println(table.players[gnida].getLogin()+ " rize " + massage + "BB");
                    previousBet = currentBet;
                    currentBet += massage*table.BB;
                    table.bank += currentBet;
                    System.out.println("Bank" + table.bank);
                }
            }
        } while (!(gnida == table.button && previousBet == currentBet));
    }

    private int gnidaMove (int g) {
        int gTmp = g;
        do {
            gTmp++;
            gTmp =  gTmp % 6;
        } while (table.players[gTmp] == null || !table.players[gTmp].isInGame);
        return gTmp;
    }
}
