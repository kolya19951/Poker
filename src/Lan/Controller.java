package Lan;

import Cards.*;
import Players.Player;

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

    public void setBankroll(Player p) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("change");
                players[i].sendInt(p.GetPosition());
                players[i].sendUTF("bankroll");
                players[i].sendInt(p.getBankroll());
            }
        }
        //System.out.println("controller send bankroll");
    }

    public void setBet(Player p) {
        for (int i = 0; i < 6; i++) {
            if (players[i].getPosition() != -1) {
                players[i].sendUTF("change");
                players[i].sendInt(p.GetPosition());
                players[i].sendUTF("bet");
                players[i].sendInt(p.getBet());
            }
        }
        //System.out.println("controller send bet");
    }

    public void setBank(int b) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("bank");
                players[i].sendInt(b);
            }
        }
        //System.out.println("controller send bank");
    }

    public void fold(Player p) {
        nullHand(p);
        //System.out.println("controller send fold");
    }

    public void nullHand(Player p) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("change");
                players[i].sendInt(p.GetPosition());//��� ����� ���� ������ � ��������
                players[i].sendUTF("hand");
                players[i].sendUTF("null");//trans!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                players[i].sendUTF("null");
            }
        }
    }

    public void initCards() {
        for (int i = 0; i < 6; i++) {
            if (players[i].getPosition() != -1) {
                nullHand(players[i]);
                players[i].sendUTF("flop");
                players[i].sendUTF("null");
                players[i].sendUTF("null");
                players[i].sendUTF("null");
                players[i].sendUTF("turn");
                players[i].sendUTF("null");
                players[i].sendUTF("river");
                players[i].sendUTF("null");
            }
        }
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
        //System.out.println("controller send flop");
    }
    public void setTurn (Card c) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("turn");
                players[i].sendUTF(c.toString());
            }
        }
        //System.out.println("controller send turn");
    }
    public void setRiver (Card c) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                players[i].sendUTF("river");
                players[i].sendUTF(c.toString());
            }
        }
        //System.out.println("controller send river");
    }

    public void setHand (Player p, Hand h) {
        for (int i = 0; i < 6; i++) {
            if (players[i].isInGame) {
                if (i == p.getPosition()) {
                    p.sendUTF("change");
                    p.sendInt(i);
                    p.sendUTF("hand");
                    p.sendUTF(h.c1.toString());
                    p.sendUTF(h.c2.toString());
                } else {
                    p.sendUTF("change");
                    p.sendInt(i);
                    p.sendUTF("hand");
                    p.sendUTF("back");
                    p.sendUTF("back");
                }
            }
        }
        //System.out.println("controller send turn");
    }

    public boolean changeFlop() {
        return true;
    }
}
