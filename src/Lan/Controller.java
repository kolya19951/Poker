package Lan;

import Cards.*;
import Players.Player;

/**
 * Created by Δενθρ on 22.05.2015.
 */
public class Controller {
    private Player[] players;

    public Controller() {

    }

    Controller(Player... players) {
        this.players = players;
    }

    public void reset() {

    }

    public boolean giveAHand(Player p, Hand h) {
        p.sendUTF(h.toString());
        //return p.readBoolean();
        return true;
    }

    public void setBankroll(Player p, int b) {
        for (int i = 0; i < 6; i++) {
            if (players[i] == p) {
                p.sendUTF("set bankroll");
                p.sendInt(b);
            } else {
                p.sendUTF("change player");
                p.sendInt(i);
                p.sendUTF("bankroll");
                p.sendInt(players[i].getBankroll());
            }
        }
    }

    public void setBet(Player p, int b) {
        for (int i = 0; i < 6; i++) {
            if (players[i].getPosition() != -1)
            players[i].sendUTF("change player");
            p.sendInt(p.GetPosition());
            p.sendUTF("bet");
            p.sendInt(players[i].getBet());
        }
    }

    public boolean changeFlop() {
        return true;
    }
}
