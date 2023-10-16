package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {
    private boolean isFaulty = false;

    @Override
    public Connection getConnection() {
        if (checkIfConnectionIsFaulty()) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }

    protected boolean checkIfConnectionIsFaulty() {
        if (isFaulty) {
            isFaulty = false;
            return true;
        }
        isFaulty = true;
        return false;
    }
}
