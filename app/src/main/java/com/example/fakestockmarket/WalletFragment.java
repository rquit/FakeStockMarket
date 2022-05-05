package com.example.fakestockmarket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WalletFragment extends Fragment {


    Button btn_deposits;
    Button btn_withdraw;


   TextView tV;
    int count;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    btn_deposits = requireView().findViewById(R.id.button_deposit); btn_withdraw = requireView().findViewById(R.id.button_withdraws);

    tV = requireView().findViewById(R.id.tvCash);

    btn_deposits.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        count=count + 100 ;
        tV.setText("$" + count);

    }
});
    btn_withdraw.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count = count - 100;
            tV.setText("$" + count);


        }
    });


   }



}

