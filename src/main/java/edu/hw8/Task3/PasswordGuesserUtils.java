package edu.hw8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PasswordGuesserUtils {
    private static final Logger LOGGER = LogManager.getLogger(PasswordGuesserUtils.class);
    private static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>(1000000);

    private PasswordGuesserUtils() {
    }

    public static int getQueueSize() {
        return QUEUE.size();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    protected static String md5Hasher(String password) {
        try {
            MessageDigest md5Digester = MessageDigest.getInstance("md5");
            md5Digester.update(password.getBytes());
            byte[] digest = md5Digester.digest();
            StringBuilder hashedString = new StringBuilder();
            for (byte b : digest) {
                String hexByte = Integer.toHexString(b & 0xff);
                if (hexByte.length() == 1) {
                    hashedString.append(0);
                }
                hashedString.append(hexByte);
            }
            return hashedString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Алгоритм хэширования не существует, всё сломалось");
            return "";
        }
    }

    public static String nextPassword() {
        return QUEUE.poll();
    }

    public static boolean hasNextPassword() {
        return !QUEUE.isEmpty();
    }

    static void partialPermutationUtil(String[] arr, String[] data, int start, int end, int index, int r) {
        if (index == r) {
            StringBuilder pass = new StringBuilder();
            for (int j = 0; j < r; j++) {
                pass.append(data[j]);
            }
            try {
                QUEUE.put(pass.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        for (int i = start; i <= end; i++) {
            data[index] = arr[i];
            partialPermutationUtil(arr, data, 0, end, index + 1, r);
        }
    }
}
