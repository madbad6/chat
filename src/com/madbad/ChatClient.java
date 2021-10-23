package com.madbad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
    List <Client> listClient = new ArrayList<>();
    ServerSocket server;

    public ChatClient() throws IOException {
        server = new ServerSocket(1234);
    }

    public void run(){
        while (true) {
            System.out.println("Waiting...");
            // ждем клиента из сети
            Socket socket;
            try {
                socket = server.accept();
                listClient.add(new Client(socket, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client connected!");
        }
    }


    public void messageSend(String message){
        for (Client client: listClient){
            client.getMessage(message);
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatClient().run();
    }
}
