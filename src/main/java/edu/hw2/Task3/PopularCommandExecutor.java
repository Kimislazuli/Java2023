package edu.hw2.Task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        Exception exception = null;
        try (Connection connection = manager.getConnection()) {
            for (int i = 0; i < maxAttempts; i++) {
                try {
                    connection.execute(command);
                } catch (ConnectionException e) {
                    exception = e;
                    continue;
                }
                return;
            }
        } catch (Exception e) {
            exception = e;
        }
        throw new ConnectionException("Can't execute within " + Integer.toString(maxAttempts) + " tries.", exception);
    }
}
