package Players;

import Cards.Hand;

import java.io.*;
import java.net.Socket;

/**
 * Created by ƒенис on 15.05.2015.
 */
public class Player {
    public boolean isInGame;
    private InputStream sin;
    private OutputStream sout;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private String login;
    private int count;
    private Hand hand;
    private int position;
    private int bankroll;
    private int bet;

    public Player() {
        isInGame = false;
        hand = null;
        position = -1;
        bankroll = 0;
        bet = 0;
    }

    public String getLogin() {
        return login;
    }
    public int getPosition() {
        return position;
    }

    public void Init() {
        socket = null;
        login = null;
    }

    public void sendUTF(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("send " + s);
    }

    public void sendInt(int x) {
        try {
            out.writeInt(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("send " + x);
    }

    public boolean readBoolean() {
        boolean b = false;
        try {
            b = in.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return b;
    }

    public String Get() {
        String s = "Error";
        try {
            s = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public int GetInt() {
        int data = -2;
        try {
            data = in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setPlayer(Socket st, int c) {
        isInGame = true;
        count = c;
        socket = st;
        try {
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
            login = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        position = count;//здесь можно попросить позицию
        System.out.print(login);
        System.out.println(" - " + count + "Player");
        setBankroll(1000);
    }

    public void setBankroll (int b) {
        bankroll = b;
    }

    public void SetPosition(int pos) {
        position = pos;
    }

    public int GetPosition() {
        return position;
    }

    public int getBet() {
        return bet;
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

    public int getBankroll () {
        return bankroll;
    }

    public void initAMoney(int m) {
        bankroll = m;
        /*try {
            out.writeUTF("you started money");
            out.writeInt(m);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void giveAMoney(int m) {
        bankroll += m;
        try {
            out.writeUTF("you money");
            out.writeInt(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeMoney(int m) {
        bankroll -= m;
        bet += m;
        /*try {
            out.writeUTF("give me a money");
            out.writeInt(m);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    Hand TakeAHand() {
        Hand tmp = hand;
        hand = null;
        return tmp;
    }

    public void Fold() {
        hand = null;
        isInGame = false;
    }
}
