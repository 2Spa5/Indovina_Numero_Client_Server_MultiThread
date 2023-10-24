package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Random rand = new Random();
            int numero = rand.nextInt(1, 101);
            int numtentativi = 1;
            int numricevuto = 0;
            do {

                numricevuto = Integer.parseInt(in.readLine());
                System.out.println(numricevuto + " " + numero);

                if (numricevuto == numero) {
                    System.out.println(3);
                    out.writeBytes("3\n");
                }

                else if (numricevuto < numero) {
                    System.out.println(1);
                    out.writeBytes("1\n");
                    numtentativi++;
                }

                else if (numricevuto > numero) {
                    System.out.println(2);
                    out.writeBytes("2\n");
                    numtentativi++;
                }

            } while (numricevuto != numero);

            out.writeBytes(numtentativi + "\n");

            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
