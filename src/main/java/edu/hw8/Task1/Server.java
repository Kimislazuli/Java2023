package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server implements Runnable {
    private static final int PORT = 10000;
    private static final int EXECUTORS_AMOUNT = 5;
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private final ExecutorService executorService;

    public Server() {
        executorService = Executors.newFixedThreadPool(EXECUTORS_AMOUNT);
    }

    @Override
    public void run() {
        LOGGER.info("сервер: начал работу");
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress("localhost", PORT));
            while (true) {
                LOGGER.info("сервер: жду подключения");
                SocketChannel clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    LOGGER.error("сервер: не удается установить соединение.");
                }
                if (clientSocket == null) {
                    LOGGER.error("сервер: клиент не обнаружен.");
                } else {
                    LOGGER.info("сервер: Начинаю обработку клиента " + clientSocket.socket().getPort());
                    this.executorService.execute(new ClientHandler(clientSocket));
                }
            }
        } catch (IOException e) {
            LOGGER.error("Сервер упал: " + e.getMessage());
        }
    }
}
