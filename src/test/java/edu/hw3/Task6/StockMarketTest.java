package edu.hw3.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockMarketTest {
    @Test
    @DisplayName("Самая ценная бумага в пустой бирже")
    void emptyMarketMostValuable() {
        StockMarket stockMarket = new StockMarket();

        StockMarket.Stock actualResult = stockMarket.mostValuableStock();

        assertThat(actualResult).isNull();
    }

    @Test
    @DisplayName("Удаление из пустой биржи")
    void emptyMarketRemove() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> {
                StockMarket stockMarket = new StockMarket();

                stockMarket.remove(new StockMarket.Stock(2000, "Gazprom"));
            }
        );
    }

    @Test
    @DisplayName("Удаление несуществующего элемента")
    void removeWrongStock() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> {
                StockMarket stockMarket = new StockMarket();

                stockMarket.add(new StockMarket.Stock(2000, "Gazprom"));

                stockMarket.remove(new StockMarket.Stock(200, "Gazprom"));
            }
        );
    }

    @Test
    @DisplayName("Самая ценная бумага")
    void mostValuable() {
        StockMarket stockMarket = new StockMarket();

        stockMarket.add(new StockMarket.Stock(1600, "BashNeft"));
        stockMarket.add(new StockMarket.Stock(2000, "Gazprom"));
        stockMarket.add(new StockMarket.Stock(500, "Rosneft"));

        StockMarket.Stock actualResult = stockMarket.mostValuableStock();

        assertThat(actualResult).isEqualTo(new StockMarket.Stock(2000, "Gazprom"));
    }

    @Test
    @DisplayName("Удаление элемента")
    void removeCorrectly() {
        StockMarket actualResult = new StockMarket();

        actualResult.add(new StockMarket.Stock(1600, "BashNeft"));
        actualResult.add(new StockMarket.Stock(2000, "Gazprom"));
        actualResult.add(new StockMarket.Stock(500, "Rosneft"));

        actualResult.remove(new StockMarket.Stock(500, "Rosneft"));

        assertThat(actualResult.getStockQueue()).containsExactly(
            new StockMarket.Stock(2000, "Gazprom"),
            new StockMarket.Stock(1600, "BashNeft")
        );
    }

    @Test
    @DisplayName("Удаление null")
    void removeNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                StockMarket stockMarket = new StockMarket();

                stockMarket.remove(null);
            }
        );
    }

    @Test
    @DisplayName("Добавление null")
    void addNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                StockMarket stockMarket = new StockMarket();

                stockMarket.add(null);
            }
        );
    }
}
