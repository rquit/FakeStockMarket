package com.example.fakestockmarket;

import com.example.fakestockmarket.models.Stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import yahoofinance.YahooFinance;

public class YahooFinanceClient {
    public static Stock getStock(String ticker) throws IOException {
        yahoofinance.Stock yf = YahooFinance.get(ticker);
        if(yf != null) {
            BigDecimal price = yf.getQuote().getPrice();
            BigDecimal change = yf.getQuote().getChange();
            String name = yf.getSymbol();
            return new Stock(name, change, price);
        }
        return null;
    }

    public static List<Stock> getAllSimilarStocks(String ticker) throws IOException {
        Set<String> distanceOne = TextUtils.levenshteinDistanceOnce(ticker);
        List<Stock> stocks = new ArrayList<>();
        for(String s: distanceOne) {
            yahoofinance.Stock yf = YahooFinance.get(s);
            if(yf != null) {
                BigDecimal price = yf.getQuote().getPrice();
                BigDecimal change = yf.getQuote().getChange();
                String name = yf.getSymbol();
                stocks.add(new Stock(name, change, price));
            }
        }
        return stocks;
    }
}
