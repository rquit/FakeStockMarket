package com.example.fakestockmarket;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakestockmarket.models.Stock;

import java.util.List;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.ViewHolder> {

    Context context;
    List<Stock> stocks;

    public StocksAdapter(Context context, List<Stock> stocks) {
        this.context = context;
        this.stocks = stocks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stock stock = stocks.get(position);
        holder.bind(stock);
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public void clearAll() {
        int stockLength = stocks.size();
        stocks.clear();
        notifyItemRangeRemoved(0, stockLength);
    }

    public void clearStock(String ticker) {
        for(int i = 0; i < stocks.size(); i++) {
            if(stocks.get(i).getTicker().equals(ticker)) {
                stocks.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    public void addAll(List<Stock> stocksToAdd) {
        int stockLength = this.stocks.size();
        this.stocks.addAll(stocksToAdd);
        notifyItemRangeInserted(stockLength, stocksToAdd.size());
    }

    public void add(Stock stockToAdd) {
        this.stocks.add(stockToAdd);
        notifyItemInserted(stocks.size()-1);
    }

    public void addToTop(Stock stockToAdd) {
        this.stocks.add(0, stockToAdd);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStockName;
        TextView tvPrice;
        TextView tvPriceChange;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStockName = itemView.findViewById(R.id.tvStockName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPriceChange = itemView.findViewById(R.id.tvPriceChange);
        }

        public void bind(Stock stock) {
            tvStockName.setText(stock.getTicker());
            tvPriceChange.setText(stock.getPriceChange().toString());
            tvPrice.setText(stock.getPrice().toString());
        }
    }
}
