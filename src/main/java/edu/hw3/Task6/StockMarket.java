package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class StockMarket {
    private final Queue<Stock> stockQueue = new PriorityQueue<>(Comparator.comparing(Stock::price).reversed());

    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock can't be null.");
        }
        stockQueue.add(stock);
    }

    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("You can't remove null.");
        }
        if (stockQueue.isEmpty() || !stockQueue.contains(stock)) {
            throw new UnsupportedOperationException("You can't remove element that doesn't exist.");
        }
        stockQueue.remove(stock);
    }

    public Queue<Stock> getStockQueue() {
        return stockQueue;
    }

    public Stock mostValuableStock() {
        if (stockQueue.isEmpty()) {
            return null;
        }
        return stockQueue.peek();
    }

    public record Stock(int price, String name) {}
}
