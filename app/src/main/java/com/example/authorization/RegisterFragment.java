package com.example.authorization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;
    private SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);
        buttonSignUp = view.findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString();
            String password = editTextPassword.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();

            if (sharedPrefManager.loginExists(login)) {
                Toast.makeText(getActivity(), "Login already exists", Toast.LENGTH_SHORT).show();
            } else if (password.equals(confirmPassword)) {
                sharedPrefManager.saveLoginPassword(login, password);
                MainFragment mainFragment = new MainFragment();
                Bundle bundle = new Bundle();
                bundle.putString("login", login);
                mainFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mainFragment)
                        .commit();
            } else {
                Toast.makeText(getActivity(), "Error, passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}