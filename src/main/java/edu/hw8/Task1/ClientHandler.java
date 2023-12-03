package edu.hw8.Task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientHandler implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);
    private static final int BYTE_BUFFER_CAPACITY = 1024;
    private static final Map<String, String> ANSWERS;

    static {
        ANSWERS = new HashMap<>();
        ANSWERS.put("личности", "Не переходи на личности там, где их нет");
        ANSWERS.put("оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        ANSWERS.put("глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        ANSWERS.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    private static final String DEFAULT_ANSWER = "Я даже не знаю, что на это ответить...";
    private final SocketChannel clientSocket;

    public ClientHandler(SocketChannel clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("сервер: Получаю информацию от клиента " + clientSocket.socket().getPort());
            ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_CAPACITY);
            int indicator = clientSocket.read(byteBuffer);
            if (indicator != -1) {
                byte[] bytes = new byte[indicator];
                byteBuffer.flip();

                for (int i = 0; i < indicator; i++) {
                    bytes[i] = byteBuffer.get();
                }

                String request = new String(bytes);

                String answer = ANSWERS.getOrDefault(request.toLowerCase(), DEFAULT_ANSWER);
                clientSocket.write(ByteBuffer.wrap((answer + '\n').getBytes()));
            } else {
                LOGGER.error("сервер умер");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
