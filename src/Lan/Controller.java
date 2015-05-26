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
    Controller (Player... players) {
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
        p.sendInt(b);
    }

    public boolean changeFlop() {
        return true;
    }
}
