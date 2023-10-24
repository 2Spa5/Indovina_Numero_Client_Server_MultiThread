package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Server Avviato");
            ServerSocket server = new ServerSocket(3000);
            MioThread t = null;

            while(true){
                Socket s = server.accept();
                t = new MioThread(s);
                t.start();
                System.out.println("\nNuovo Utente");
            }

            // server.close();

        } catch (Exception e) {
            System.out.println("Qualcosa è andato storto");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}