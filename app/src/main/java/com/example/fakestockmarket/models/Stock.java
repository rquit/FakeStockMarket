package com.example.fakestockmarket.models;

import java.math.BigDecimal;
import java.util.Currency;

public class Stock {

    private final String ticker;
    private final BigDecimal priceChange;
    private final BigDecimal price;

    // If you need to use parceler, don't forget to uncomment below.
    // public Stock() {};

    public Stock(String ticker, BigDecimal priceChange, BigDecimal price) {
        this.ticker = ticker;
        this.priceChange = priceChange;
        this.price = price;
    }

    public boolean equals(Stock stock) {
        return (
                ticker.equals(stock.getTicker()) &&
                        priceChange.equals(stock.getPriceChange()) &&
                        price.equals(stock.getPrice())
                );
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
