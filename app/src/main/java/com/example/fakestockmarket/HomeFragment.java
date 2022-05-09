package com.example.fakestockmarket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakestockmarket.models.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    RecyclerView homeStocks;
    List<Stock> stocks;
    StocksAdapter stocksAdapter;
    List<String> stockNames;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeStocks = requireView().findViewById(R.id.homeStocks);

        stocks = new ArrayList<>();
        stockNames = new ArrayList<String>();
        stockNames.add("AAPL");
        stockNames.add("MSFT");
        stockNames.add("NKE");
        stockNames.add("AMZN");
        stockNames.add("TSLA");
        stockNames.add("SBUX");
        stockNames.add("TWTR");
        stockNames.add("FB");
        stockNames.add("DIS");
        stockNames.add("BAC");

        stocksAdapter = new StocksAdapter(getActivity(), stocks);

        homeStocks.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeStocks.setAdapter(stocksAdapter);

        for (String name: stockNames) {
            addStockToAdapter(name);
        }

    }
// addStockAdapter is use to search the stock you input (Above) and then find the relate info.
    private void addStockToAdapter(String ticker) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try {
                Stock ex = YahooFinanceClient.getStock(ticker);
                if(ex != null && ex.getPrice() != null) {
                    handler.post(() -> {
                        stocksAdapter.addToTop(ex);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
