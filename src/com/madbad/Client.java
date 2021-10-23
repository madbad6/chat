package com.madbad;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    Socket socket;
    Scanner in;
    PrintStream out;
    ChatClient client;
    String name;


    public Client(Socket socket, ChatClient client){

        this.socket = socket;
        this.client = client;
        // создаем клиента на своей стороне
        new Thread (this).start();
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to Chat! Input your name");
            name = in.nextLine();
            String input = in.nextLine();
            while (!input.equals("bye")) {
                client.messageSend(name +"\n" + input + "\n");
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getMessage(String message){
        out.println(message);
    }
}
