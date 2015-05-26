package Tables;

import Cards.Deck;
import Cards.Hand;
import Lan.Controller;
import Lan.Server;
import Players.Player;

/**
 * Created by Денис on 16.05.2015.
 */
public class Dealer {

    private Controller controller;
    private Table table;
    private Deck deck;

    public Dealer () {
        controller = new Controller();
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
                table.players[i].sendUTF("game start info");
                for (int j = 0; j < 6; j++) {
                    //if (players[j] == null) {
                    if (players[j].getPosition() == -1) {
                        players[i].sendInt(j);//позиция
                        players[i].sendUTF("free seat");
                        players[i].sendInt(table.players[j].getBankroll());
                    } else {
                        players[i].sendInt(j);
                        players[i].sendUTF(table.players[j].getLogin());
                        players[i].sendInt(table.players[j].getBankroll());
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
                table.players[i].sendUTF("you hand");
                Hand h = new Hand(deck.retrieve(), deck.retrieve());
                controller.giveAHand(table.players[i], h);
                //table.players[i].GiveAHand(h);
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
        Player hodok;
        int gnida = table.button;
        int currentBet = 0;
        int previousBet;
        gnida = gnidaMove(gnida);
        hodok = table.players[gnida];
        hodok.sendUTF("small blind");
        hodok.takeAMoney(table.BB / 2);
        controller.setBankroll(hodok, hodok.getBankroll());
        previousBet = currentBet;
        currentBet += table.BB/2;
        table.bank += currentBet;
        System.out.println(hodok.getLogin()+ " is Small Blind");
        System.out.println("Bank" + table.bank);
        gnida = gnidaMove(gnida);
        hodok = table.players[gnida];
        hodok.sendUTF("big blind");
        hodok.takeAMoney(table.BB);
        controller.setBankroll(hodok, hodok.getBankroll());
        previousBet = currentBet;
        currentBet += table.BB/2;
        table.bank += currentBet;
        System.out.println(hodok.getLogin()+ " is Big Blind");
        System.out.println("Bank" + table.bank);
        do {
            previousBet = currentBet;
            gnida = gnidaMove(gnida);
            hodok = table.players[gnida];
            //Калян, напиши тут код, который пошлет запрос игроку gnida запрос на ход
            hodok.sendUTF("You turn");
            // ждем ответа от игрока гнида
            int massage;
            massage = hodok.GetInt();
            if (massage == -1) {
                hodok.Fold();
                System.out.println(hodok.getLogin() + " fold");
            } else {
                if (massage == 0) {
                    System.out.println(hodok.getLogin() + " check/call");
                } else {
                    System.out.println(hodok.getLogin()+ " rize " + massage + "BB");
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
