package Lan;

import Forms.MainForm;
import Players.Player;

import javax.swing.*;
public class Server {
    final int PLAYERS_COUNT = 3;
    public Player[] ConnectPlayers()    {
        SocketServer sock = new SocketServer();
        MainForm frame = new MainForm(sock);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        for (int i = 0; i < 6; i++) {
            sock.players[i] = new Player();
        }
        try {
            do {
                sock.players[sock.count].setPlayer(sock.ss.accept(), sock.count);
                sock.count++;
            } while (sock.count != PLAYERS_COUNT);
        }catch(Exception x) { x.printStackTrace(); }
        System.out.println("Game start!");
        return sock.players;
    }
}
