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
        table = new Table();
        controller = new Controller(table.players);
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
        controller.resetPlayers(table.players);
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
                Hand h = new Hand(deck.retrieve(), deck.retrieve());
                controller.setHand(table.players[i], h);
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
            //DealFlop();//--------------------tut ydalit
            //controller.setFlop(table.commonCards.flop.c1, table.commonCards.flop.c2, table.commonCards.flop.c3);//------
            //Префлоп
            System.out.println("Preflop started");
            Round1();
            System.out.println("Preflop end");
            DealFlop();
            System.out.println("Postflop started");
            controller.setFlop(table.commonCards.flop.c1, table.commonCards.flop.c2, table.commonCards.flop.c3);
            //Постфлоп
            Round();
            System.out.println("Postflop ended");
            DealTurn();
            System.out.println("Turn started");
            controller.setTurn(table.commonCards.turn);
            //Тёрн
            Round();
            System.out.println("Turn finished");
            DealRiver();
            System.out.println("River started");
            controller.setTurn(table.commonCards.river);
            //Ривер
            Round();
            System.out.println("Kto pobedil sami reshayte");
            //Вскрытие
            //Покидает ли кто-нибудь стол?
            //Садится ли кто-нибудь?
            table.ButtonMove();
        }
    }
    private void Round1() {
        int rememberLastBetPlayerIndex;
        int rememberLastBetPlayerBet;
        Player hodok;
        int gnida = table.button;
        int currentBet = 0;
        gnida = gnidaMove(gnida);
        hodok = table.players[gnida];
        //sb
        System.out.println(hodok.getLogin() + " is Small Blind");
        System.out.println("Bank" + table.bank);
        controller.setBank(table.bank);
        hodok.sendUTF("small blind");
        hodok.takeMoney(table.BB / 2);
        controller.setBankroll(hodok);
        controller.setBet(hodok);
        gnida = gnidaMove(gnida);
        hodok = table.players[gnida];
        //bb
        System.out.println(hodok.getLogin() + " is Big Blind");
        System.out.println("Bank" + table.bank);
        controller.setBank(table.bank);
        hodok.sendUTF("big blind");
        hodok.takeMoney(table.BB);
        controller.setBankroll(hodok);
        controller.setBet(hodok);
        currentBet = table.BB;
        table.bank += currentBet;
        rememberLastBetPlayerBet = currentBet;
        rememberLastBetPlayerIndex = gnida;
        gnida = gnidaMove(gnida);
        do {
            hodok = table.players[gnida];
            //Калян, напиши тут код, который пошлет запрос игроку gnida запрос на ход
            hodok.sendUTF("You turn");
            // ждем ответа от игрока гнида
            int massage;
            massage = hodok.GetInt();
            if (massage == -1) {
                hodok.Fold();
                System.out.println(hodok.getLogin() + " fold");
                controller.fold(hodok);
            } else {
                if (massage == 0) {
                    System.out.println(hodok.getLogin() + " check/call");
                    table.bank += currentBet;
                    hodok.takeMoney(currentBet);
                    controller.setBankroll(hodok);
                    controller.setBet(hodok);
                    System.out.println("Bank" + table.bank);
                    controller.setBank(table.bank);
                } else {
                    System.out.println(hodok.getLogin()+ " rize " + massage + "BB");
                    currentBet = massage;
                    rememberLastBetPlayerIndex = hodok.getPosition();
                    rememberLastBetPlayerBet = currentBet;
                    table.bank += currentBet;
                    hodok.takeMoney(massage);
                    controller.setBankroll(hodok);
                    controller.setBet(hodok);
                    System.out.println("Bank" + table.bank);
                    controller.setBank(table.bank);
                }
            }
            gnida = gnidaMove(gnida);
        } while (gnida == rememberLastBetPlayerIndex && currentBet == rememberLastBetPlayerBet);
    }
    private void Round() {
        int rememberLastBetPlayerIndex;
        int rememberLastBetPlayerBet;
        Player hodok;
        int gnida = table.button;
        int currentBet = 0;
        rememberLastBetPlayerBet = currentBet;
        rememberLastBetPlayerIndex = gnida;
        gnida = gnidaMove(gnida);
        do {
            hodok = table.players[gnida];
            //Калян, напиши тут код, который пошлет запрос игроку gnida запрос на ход
            hodok.sendUTF("You turn");
            // ждем ответа от игрока гнида
            int massage;
            massage = hodok.GetInt();
            if (massage == -1) {
                hodok.Fold();
                System.out.println(hodok.getLogin() + " fold");
                controller.fold(hodok);
            } else {
                if (massage == 0) {
                    System.out.println(hodok.getLogin() + " check/call");
                    table.bank += currentBet;
                    hodok.takeMoney(currentBet);
                    controller.setBankroll(hodok);
                    controller.setBet(hodok);
                    System.out.println("Bank" + table.bank);
                    controller.setBank(table.bank);
                } else {
                    System.out.println(hodok.getLogin()+ " rize " + massage + "BB");
                    currentBet = massage;
                    rememberLastBetPlayerIndex = hodok.getPosition();
                    rememberLastBetPlayerBet = currentBet;
                    table.bank += currentBet;
                    hodok.takeMoney(massage);
                    controller.setBankroll(hodok);
                    controller.setBet(hodok);
                    System.out.println("Bank" + table.bank);
                    controller.setBank(table.bank);
                }
            }
            gnida = gnidaMove(gnida);
        } while (gnida == rememberLastBetPlayerIndex && currentBet == rememberLastBetPlayerBet);
    }

    private int gnidaMove (int g) {
        int gTmp = g;
        do {
            gTmp++;
            gTmp =  gTmp % 6;
        } while (!table.players[gTmp].isInGame);
        return gTmp;
    }
}





