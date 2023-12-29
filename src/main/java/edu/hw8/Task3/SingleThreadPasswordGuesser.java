package edu.hw8.Task3;

public class SingleThreadPasswordGuesser extends AbstractPasswordGuesser {
    public SingleThreadPasswordGuesser(String filename) {
        super(filename);
    }

    public void findAllPasswords() {
        long start = System.nanoTime();
            for (int i = 2; i < MAX_LEN; i++) {
                PasswordGuesserUtils.partialPermutationUtil(ALPHABET, new String[i], 0, ALPHABET_LENGTH - 1, 0, i);
                while (PasswordGuesserUtils.hasNextPassword()) {
                    String pass = PasswordGuesserUtils.nextPassword();
                    String userName = passwords.get(PasswordGuesserUtils.md5Hasher(pass));
                    if (userName != null) {
                        result.put(userName, pass);
                        if (passwords.size() == result.size()) {
                            break;
                        }
                    }
                }
            }
    }
}
