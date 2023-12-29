package edu.hw8.Task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingPasswordGuesser extends AbstractPasswordGuesser {
    private static final int KERNELS = 6;

    public MultithreadingPasswordGuesser(String filename) {
        super(filename);
    }

    @Override
    public void findAllPasswords() {
        ExecutorService executorService = Executors.newFixedThreadPool(KERNELS);
        for (int i = 2; i < MAX_LEN; i++) {
            int passwordLen = i;
            executorService.execute(() -> PasswordGuesserUtils.partialPermutationUtil(
                ALPHABET,
                new String[passwordLen],
                0,
                ALPHABET_LENGTH - 1,
                0,
                passwordLen
            ));
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (PasswordGuesserUtils.hasNextPassword()) {
                if (passwords.size() == result.size()) {
                    break;
                }
                String password = PasswordGuesserUtils.nextPassword();
                if (password != null) {
                    executorService.execute(new PasswordGuesser(password));
                }
            }
        }
    }

    private final class PasswordGuesser implements Runnable {
        private final String password;

        private PasswordGuesser(String password) {
            this.password = password;
        }

        @Override
        public void run() {
            if (result.size() == passwords.size()) {
                return;
            }
            String userName = passwords.get(PasswordGuesserUtils.md5Hasher(password));
            if (userName != null) {
                result.put(userName, password);
            }
        }
    }
}

