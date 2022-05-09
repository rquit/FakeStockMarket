package com.example.fakestockmarket;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
//import javax.swing.JOptionPane;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WalletFragment extends Fragment {


    Button btn_deposits;
    Button btn_withdraw;


   TextView tV;
   TextView Tv;
    int count;
    int portfolio;

    private static final String COUNT_PREF = "count";

    private static final String fixed_Value = "portfolio";

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        count = sharedPreferences.getInt(COUNT_PREF,0);

        portfolio = sharedPreferences.getInt(fixed_Value, 5000);

        tV.setText("$" + count );
        Tv.setText(("$" + portfolio));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    btn_deposits = requireView().findViewById(R.id.button_deposit);
    btn_withdraw = requireView().findViewById(R.id.button_withdraws);

    tV = requireView().findViewById(R.id.tvCash);
    Tv = requireView().findViewById(R.id.tvPortfolioValue);


    sharedPreferences = requireActivity().getSharedPreferences("MyPref", 0);

    editor = sharedPreferences.edit();

    count = sharedPreferences.getInt(COUNT_PREF, 0);
    portfolio = sharedPreferences.getInt(fixed_Value, 5000);



    btn_deposits.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        count=count + 100 ;
        tV.setText("$" + count);

        editor.putInt(COUNT_PREF, count).apply();

    }
});
    btn_withdraw.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count = count - 100;
            tV.setText("$" + count);

            editor.putInt(COUNT_PREF, count).apply();


        }
    });


   }



}

