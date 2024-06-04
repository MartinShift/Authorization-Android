package com.example.authorization;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;
    private String currentLogin;

    private Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefManager = SharedPrefManager.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLogin = sharedPrefManager.getCurrentLogin();
        if(currentLogin == null) {
            selectedFragment = new LoginFragment();
        }
        else
        {
            selectedFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("login", currentLogin);
            selectedFragment.setArguments(bundle);
        }
        fragmentChanged();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_login) {
            selectedFragment = new LoginFragment();
        } else if(item.getItemId() == R.id.action_register) {
            selectedFragment = new RegisterFragment();
        }
        else if(item.getItemId() == R.id.action_exit) {
            sharedPrefManager.setCurrentLogin(null);
            selectedFragment = new LoginFragment();
        }
        fragmentChanged();

        return super.onOptionsItemSelected(item);
    }

    private void fragmentChanged() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
    }
}