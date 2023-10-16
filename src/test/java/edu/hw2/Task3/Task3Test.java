package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    private static final FaultyConnection dummyFaultyConnectionAlwaysFails = new FaultyConnection() {
        @Override
        protected boolean checkIfConnectionWillFail() {
            return true;
        }
    };
    private static final DefaultConnectionManager dummyAlwaysFaultyDefaultManager = new DefaultConnectionManager() {
        @Override
        public Connection getConnection() {
            return dummyFaultyConnectionAlwaysFails;
        }
    };

    private static final DefaultConnectionManager dummyAlwaysFaultyFaultyManager = new DefaultConnectionManager() {
        @Override
        public Connection getConnection() {
            return dummyFaultyConnectionAlwaysFails;
        }
    };

    @Test
    @DisplayName("Дефолтный менеджер возвращает сломанное подключение")
    void defaultManagerReturnsAlwaysFaultyConnection() {
        assertThrows(
            ConnectionException.class,
            () -> {
                PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(
                    dummyAlwaysFaultyDefaultManager,
                    10);
                popularCommandExecutor.updatePackages();
            }
        );
    }

    @Test
    @DisplayName("Дефолтный менеджер возвращает стабильное подключение")
    void defaultManagerReturnsAlwaysStableConnection() {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(
            new DefaultConnectionManager(),
            10);
        assertDoesNotThrow(popularCommandExecutor :: updatePackages);
    }

    @Test
    @DisplayName("Faulty менеджер возвращает всегда сломанное подключение")
    void faultyManagerWithAlwaysFaultyConnection() {
        assertThrows(
            ConnectionException.class,
            () -> {
                PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(
                    dummyAlwaysFaultyFaultyManager,
                    10);
                popularCommandExecutor.updatePackages();
            }
        );
    }

}
