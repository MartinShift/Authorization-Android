package com.example.authorization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private TextView textViewHello;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        textViewHello = view.findViewById(R.id.welcomeTextView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String login = bundle.getString("login", "");
            textViewHello.setText("Hello, " + login);
        }

        return view;
    }
}