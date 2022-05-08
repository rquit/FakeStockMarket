package com.example.fakestockmarket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class SearchFragment extends Fragment {
    EditText etSearchQuery;
    RecyclerView rvStocks;
    List<Stock> stocks;
    StocksAdapter stocksAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearchQuery = requireView().findViewById(R.id.etSearchQuery);
        rvStocks = requireView().findViewById(R.id.rvStocks);

        stocks = new ArrayList<>();
        stocksAdapter = new StocksAdapter(getActivity(), stocks);

        rvStocks.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStocks.setAdapter(stocksAdapter);

        etSearchQuery.addTextChangedListener(updateQueryResults());

    }

    private TextWatcher updateQueryResults() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(before > count) {
                    stocksAdapter.clearStock(etSearchQuery.getText().toString().toUpperCase(Locale.ROOT));
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(before < count){
                    addStockToAdapter(etSearchQuery.getText().toString().toUpperCase(Locale.ROOT));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

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
