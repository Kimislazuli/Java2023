package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client implements Runnable {
    private static final int PORT = 10000;
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private final String message;

    public Client(String message) {
        this.message = message;
    }

    public Client() {
        LOGGER.info("Напишите, что хотели бы сказать");
        message = SCANNER.nextLine();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", PORT)) {
            LOGGER.info("клиент: Подключение успешно " + System.nanoTime() + " nanoseconds");
            try (OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
                var response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                LOGGER.info("клиент: Получаю ответ " + System.nanoTime() + " nanoseconds");
                LOGGER.info("клиент: ответ сервера: " + response.readLine() + " " + System.nanoTime() + " nanoseconds");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            LOGGER.error("клиент умер" + e.getMessage());
        }
    }
}
