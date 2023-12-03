package edu.hw8.Task1;

import org.junit.jupiter.api.Test;

public class MultithreadingDemonstrationTest {
    Client client = new Client("оскорбления") {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @Test
    void test() throws InterruptedException {
        Thread serverThread = new Thread(new Server());
        Thread client1 = new Thread(client);
        Thread client2 = new Thread(client);

        serverThread.start();
        Thread.sleep(100);
        client1.start();
        client2.start();
    }
}
