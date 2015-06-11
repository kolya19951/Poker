package Tables;

import Cards.CollectionCards;
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
    private Player hodor;
    private int laslBetPlayerIndex;
    private int lastBetPlayerBet;

    public Dealer() {
        table = new Table();
        controller = new Controller(table.players);
        deck = new Deck();
    }

    public void seatClients() {
        //добавляем нового игрока
        Server server = new Server();
        Player[] players = server.ConnectPlayers();
        table.addPlayers(players);
        /*for (int i = 0; i < 6; i++) {
            if (players[i].getPosition() != -1)
                players[i].sendUTF("connect");
        }*/
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
    public void resetDeck() {
        deck.reset();
    }
    public void dealFlop() {
        table.DealFlop(deck.retrieve(), deck.retrieve(), deck.retrieve());
        controller.setFlop(table.commonCards.flop.c1, table.commonCards.flop.c2, table.commonCards.flop.c3);
    }
    public void dealTurn() {
        table.DealTurn(deck.retrieve());
        controller.setTurn(table.commonCards.turn);
    }
    public void dealRiver() {
        table.DealRiver(deck.retrieve());
        controller.setRiver(table.commonCards.river);
    }
    public void dealHands() {
        for (int i = 0; i < 6; i++) {
            //if (table.players[i] != null) {
            if (table.players[i].getPosition() != -1) {
                Hand h = new Hand(deck.retrieve(), deck.retrieve());
                controller.setHand(table.players[i], h);
                table.players[i].GiveAHand(h);
                table.players[i].isInGame = true;
            }
        }
    }
    public void setBank(int b) {
        table.setBank(b);
        controller.setBank(b);
    }
    private boolean rize(Player p, int b) {
        if (b > table.getCurrentBet() && b <= hodor.getBankroll() + hodor.getBet()) {
            System.out.println(hodor.getLogin() + " rize " + b + "BB");
            table.addToBank(b - p.getBet());
            p.takeMoney(b - p.getBet());
            table.setCurrentBet(b);
            controller.setBankroll(p);
            controller.setBet(p);
            controller.setBank(table.getBank());
            lastBetPlayerBet = b;
            laslBetPlayerIndex = p.getPosition();
            return true;
        } else {
            return false;
        }
    }
    private void fold(Player p) {
        hodor.Fold();
        System.out.println(hodor.getLogin() + " fold");
        controller.fold(hodor);
    }
    private boolean check(Player p) {
        if (p.getBankroll() + p.getBet() < table.getCurrentBet()) {
            return false;
        } else {
            System.out.println(hodor.getLogin() + " check/call");
            table.addToBank(table.getCurrentBet() - hodor.getBet());
            hodor.takeMoney(table.getCurrentBet() - hodor.getBet());
            controller.setBankroll(hodor);
            controller.setBet(hodor);
            System.out.println("Bank" + table.getBank());
            controller.setBank(table.getBank());
            return true;
        }
    }
    private void win(Player p) {
        p.giveMoney(table.getBank());
        controller.setBankroll(p);
    }
    private void setBets(){
        for (int i = 0; i < 6; i++) {
            if (table.players[i].getPosition() != -1) {
                table.players[i].setBet(0);
                controller.setBet(table.players[i]);
            }
        }
    }
    private void init() {
        table.ButtonMove();
        setBank(0);
        setBets();
        resetDeck();
        controller.initCards();
    }
    private void setCombinations() {
        for (int i = 0; i < 6; i++) {
            if (table.players[i].isInGame) {
                table.players[i].setCombination(table.players[i].getHand().c1, table.players[i].getHand().c2, table.commonCards.flop.c1, table.commonCards.flop.c2, table.commonCards.flop.c3);
            }
        }
    }
    public void Play() {
        while (true) {
            init();
            System.out.println("Preflop started");
            if (preflop()) {
                win(hodor);
                continue;
            }

            System.out.println("Preflop end");
            dealFlop();
            setCombinations();
            for (int i = 0; i < 6; i++) {
                if (table.players[i].isInGame) {
                    System.out.println(table.players[i].getCombination());
                }
            }
            System.out.println("Postflop started");
            if (round()) {
                win(hodor);
                continue;
            }

            System.out.println("Postflop ended");
            dealTurn();
            System.out.println("Turn started");
            if (round()) {
                win(hodor);
                continue;
            }

            System.out.println("Turn finished");
            dealRiver();
            System.out.println("River started");
            if (round()) {
                win(hodor);
                continue;
            }

            System.out.println("END");
            //кто - то выиграл
            win(hodor);
        }
    }

    private void blinds() {
        setBets();
        setHodorOnButton();
        hodorNext();
        System.out.println(hodor.getLogin() + " is Small Blind");
        rize(hodor, table.BB / 2);
        hodorNext();
        System.out.println(hodor.getLogin() + " is Big Blind");
        rize(hodor, table.BB);
        hodorNext();
    }
    private boolean preflop() {
        blinds();
        dealHands();
        do {
            handleAction();
            hodorNext();
        }
        while (!(hodor.getPosition() == laslBetPlayerIndex && table.getCurrentBet() == lastBetPlayerBet) && playersCount() != 1);
        if (playersCount() == 1) {
            return true;
        } else {
            if (lastBetPlayerBet == 2) {
                laslBetPlayerIndex = hodorNextIndex();
                do {
                    handleAction();
                    hodorNext();
                }
                while (!(hodor.getPosition() == laslBetPlayerIndex && table.getCurrentBet() == lastBetPlayerBet) && playersCount() != 1);
                if (playersCount() == 1) {
                    return true;
                } else {
                    setBets();
                    setHodorOnButton();
                    laslBetPlayerIndex = table.button;
                    lastBetPlayerBet = 0;
                    hodorNext();
                    return false;
                }
            }
            setBets();
            setHodorOnButton();
            laslBetPlayerIndex = table.button;
            lastBetPlayerBet = 0;
            hodorNext();
            return false;
        }
    }
    private boolean round() {
        do {
            handleAction();
            hodorNext();
        }
        while (!(hodor.getPosition() == laslBetPlayerIndex && table.getCurrentBet() == lastBetPlayerBet) && playersCount() != 1);
        if (playersCount() == 1) {
            return true;
        } else {
            setBets();
            setHodorOnButton();
            laslBetPlayerIndex = table.button;
            lastBetPlayerBet = 0;
            hodorNext();
            return false;
        }
    }
    private int playersCount() {
        int c = 0;
        for (int i = 0; i < 6; i++)
            if (table.players[i].isInGame)
                c++;
        return c;
    }
    private void handleAction() {
        int action = hodor.action();
        if (action == -1) {
            fold(hodor);
        } else {
            if (action == 0) {
                if (!check(hodor))
                    handleAction();
            } else {
                if (!rize(hodor, action))
                    handleAction();
                //lastBetPlayerIndex = hodor.getPosition();
                //lastBetPlayerBet = table.getCurrentBet();
            }
        }
    }
    private void setHodorOnButton() {
        hodor = table.players[table.button];
    }
    private void hodorNext() {
        int gTmp = hodor.getPosition();
        do {
            gTmp++;
            gTmp = gTmp % 6;
        } while (!table.players[gTmp].isInGame);
        hodor = table.players[gTmp];
    }
    private int hodorNextIndex() {
        int gTmp = hodor.getPosition();
        do {
            gTmp++;
            gTmp = gTmp % 6;
        } while (!table.players[gTmp].isInGame);
        return gTmp;
    }
}





