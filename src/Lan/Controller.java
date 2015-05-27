package Lan;

import Cards.*;
import Players.Player;

/**
 * Created by Денис on 22.05.2015.
 */
public class Controller {
    private Player[] players;

    public Controller() {

    }

    public Controller(Player... players) {
        this.players = players;
    }

    public void resetPlayers(Player... players) {
        this.players = players;
    }

    public void reset() {

    }

    /*public boolean giveAHand(Player p, Hand h) {
        p.sendUTF(h.toString());
        //return p.readBoolean();
        return true;
    }*/

    public void setBankroll(Player p) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("change");
                players[i].sendInt(p.GetPosition());
                players[i].sendUTF("bankroll");
                players[i].sendInt(p.getBankroll());
            }
        }
        System.out.println("controller send bankroll");
    }

    public void setBet(Player p) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("change");
                players[i].sendInt(p.GetPosition());//тут могут быть ошибки с позицией
                players[i].sendUTF("bet");
                players[i].sendInt(p.getBet());
            }
        }
        System.out.println("controller send bet");
    }

    public void setBank(int b) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("bank");
                players[i].sendInt(b);
            }
        }
        System.out.println("controller send bank");
    }

    public void fold(Player p) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("change");
                players[i].sendInt(p.GetPosition());//тут могут быть ошибки с позицией
                players[i].sendUTF("fold");
            }
        }
        System.out.println("controller send fold");
    }

    public void setFlop (Card c1, Card c2, Card c3) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("flop");
                players[i].sendUTF(c1.toString());
                players[i].sendUTF(c2.toString());
                players[i].sendUTF(c3.toString());
            }
        }
        System.out.println("controller send flop");
    }

    public void setTurn (Card c) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("turn");
                players[i].sendUTF(c.toString());
            }
        }
        System.out.println("controller send turn");
    }
    public void setRiver (Card c) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("river");
                players[i].sendUTF(c.toString());
            }
        }
        System.out.println("controller send river");
    }

    public void setHand (Player p, Hand h) {
        p.sendUTF("you hand");
        p.sendUTF(h.c1.toString());
        p.sendUTF(h.c2.toString());
        System.out.println("controller send hand" + p.getPosition());
    }

    public boolean changeFlop() {
        return true;
    }
}
