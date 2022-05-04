package com.example.fakestockmarket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WalletFragment extends Fragment {
    Button btn_deposit;
    Button btn_withdraw;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_deposit = requireView().findViewById(R.id.button_deposit);
        btn_deposit.setOnClickListener(e -> {
            // stuff
        });

        btn_withdraw = requireView().findViewById(R.id.button_withdraw);
        btn_withdraw.setOnClickListener(e -> {
            // stuff
        });
    }
}

