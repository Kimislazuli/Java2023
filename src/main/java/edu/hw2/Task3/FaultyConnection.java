package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger(FaultyConnection.class);
    private boolean isFail = false;

    @Override
    public void execute(String command) {
        if (checkIfConnectionWillFail()) {
            throw new ConnectionException("Execution has been failed.");
        }
        LOGGER.info("Faulty connection tries to execute command " + command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Stable connection closed.");
    }

    protected boolean checkIfConnectionWillFail() {
        if (isFail) {
            isFail = false;
            return true;
        }
        isFail = true;
        return false;
    }
}
