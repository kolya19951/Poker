package Players;

import Cards.Hand;

import java.io.*;
import java.net.Socket;

/**
 * Created by ƒенис on 15.05.2015.
 */
public class Player {
    private InputStream sin;
    private OutputStream sout;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket player;
    private String login;
    private int count;
    private Hand hand;
    private int position;
    private int bankroll;

    public Player () {
        hand = null;
        position = -1;
    }

    public Player (int pos) {
        hand = null;
        position = pos;
    }

    public void Init () {
        player = null;
        login = null;
    }

    public void Send (String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayer(Socket st, int c) {
        count = c;
        player = st;
        try {
            sin = player.getInputStream();
            sout = player.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
            login = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        position = count;//здесь можно попросить позицию
        System.out.print(login);
        System.out.println(" - " + count + "Player");
    }

    public void SetPosition (int pos) {
        position = pos;
    }
    public int GetPosition () {
        return position;
    }

    public void GiveAHand(Hand h) {
        hand = h;
        //  ал€н, напиши тут код, который пошлет этому игроку его карты
        try {
            out.writeUTF(h.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Hand TakeAHand() {
        Hand tmp = hand;
        hand = null;
        return tmp;
    }

    public void Fold () {
        hand = null;
    }
    public void ShowHand () {
        hand.Show();
    }
}
