package com.thesis.trafficinfo.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE =0;
    private static final String PREF_NAME ="LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String EMAIL = "EMAIL";
    public static final String USER = "USER";
    public static final String PASS = "PASS";
    public static final String ROLE = "Role";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    public void notlogin()
    {
        editor.putBoolean(LOGIN, false);
    }
    public void createSession(String phone, String pass,String role)
    {
        editor.putBoolean(LOGIN, true);

        editor.putString(EMAIL, phone);
        editor.putString(PASS, pass);
        editor.putString(ROLE, role);
        editor.apply();
    }
    public boolean isLoggin()
    {
        return sharedPreferences.getBoolean(LOGIN, false);

    }

    public void checkLogin()

    {
        if (!this.isLoggin())
        {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            //((DeshboardActivity) context).finish();
            // ((UserPanel) context).finish();
            // ((PoliceProfile) context).finish();
        }

    }

    public HashMap<String, String> getUserDetail()
    {
        HashMap<String, String> user = new HashMap<>();
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(USER, sharedPreferences.getString(USER, null));
        user.put(PASS, sharedPreferences.getString(PASS, null));
        user.put(ROLE, sharedPreferences.getString(ROLE, null));
        return user;

    }
    public void logout()
    {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

        //((UserPanel) context).finish();
        //((PoliceProfile) context).finish();

    }
}
