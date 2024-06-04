package com.example.authorization;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "users_passwords";
    private static final String KEY_CURRENT_LOGIN = "current_login";

    private static SharedPrefManager instance;
    private SharedPreferences sharedPreferences;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveLoginPassword(String login, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(login, password);
        setCurrentLogin(login);
        editor.apply();
    }

    public String getPassword(String login) {
        return sharedPreferences.getString(login, null);
    }

    public void setCurrentLogin(String login) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CURRENT_LOGIN, login);
        editor.apply();
    }

    public String getCurrentLogin() {
        return sharedPreferences.getString(KEY_CURRENT_LOGIN, null);
    }

    public boolean loginExists(String login) {
        return sharedPreferences.contains(login);
    }

    public boolean checkPassword(String login, String password) {
        return password.equals(getPassword(login));
    }
}