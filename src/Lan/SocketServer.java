package Lan;

import Players.Player;

import java.net.ServerSocket;

public class SocketServer {
    final static int NUMBER_OF_PLAYERS = 6;
    ServerSocket ss;
    int port = 1488;
    Player[] players = new Player[NUMBER_OF_PLAYERS];
    int count = 0;
    SocketServer(){
        try {
            ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a clients...");
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                players[i] = new Player();
                players[i].Init();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}
