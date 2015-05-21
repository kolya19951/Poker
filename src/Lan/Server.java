package Lan;

import Forms.MainForm;
import Players.Player;

import javax.swing.*;
public class Server {
    public Player[] ConnectPlayers()    {
        SocketServer sock = new SocketServer();
        MainForm frame = new MainForm(sock);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            do {
                sock.players[sock.count].setPlayer(sock.ss.accept(), sock.count);
                sock.count++;
            } while (sock.count != 2);
        }catch(Exception x) { x.printStackTrace(); }
        System.out.println("Game start!");
        return sock.players;
    }
}
