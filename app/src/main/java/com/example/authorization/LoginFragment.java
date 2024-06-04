package com.example.authorization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonLogin = view.findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString();
            String password = editTextPassword.getText().toString();

            if (!sharedPrefManager.loginExists(login)) {
                Toast.makeText(getActivity(), "Login does not exist", Toast.LENGTH_SHORT).show();
            } else if (!sharedPrefManager.checkPassword(login, password)) {
                Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
            }
            else {
                sharedPrefManager.setCurrentLogin(login);
                MainFragment mainFragment = new MainFragment();
                Bundle bundle = new Bundle();
                bundle.putString("login", login);
                mainFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mainFragment)
                        .commit();
            }
        });

        return view;
    }
}