package org.example;

public class Main  {

    public static void main(String[] args) throws InterruptedException {
        Thread[] clients = new Thread[3];

        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Thread(new Client());
        }

        for (Thread client : clients) {
            client.start();
            Thread.sleep(1000L);
        }

       for (Thread client : clients) {
            client.join();
        }
    }
}
