package com.example.fakestockmarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakestockmarket.models.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        Stock example_stock1 = new Stock("AAPL", new BigDecimal("1.43"), new BigDecimal("240.65"));
        Stock example_stock2 = new Stock("AMC", new BigDecimal("20.83"), new BigDecimal("2133.87"));

        stocks.add(example_stock1);
        stocks.add(example_stock2);
    }
}
