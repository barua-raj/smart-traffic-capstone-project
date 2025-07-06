package com.thesis.trafficinfo.LoginAndSignUp;

import static com.thesis.trafficinfo.Constant.BASE_URL;
import static com.thesis.trafficinfo.Users.AddDriverMyVehicle.PREF_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.BothActivity;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.PoliceDashboard;
import com.thesis.trafficinfo.Users.DriverActivity;
import com.thesis.trafficinfo.Users.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    TextView signup;
    ProgressBar progressBar;
    SessionManager sessionManager;
    SharedPreferences sharedPreferences;
    String  role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences=getApplicationContext().getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        progressBar = findViewById(R.id.pb);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,NID.class));
            }
        });
    }

    public void login(View view) {
        progressBar.setVisibility(View.VISIBLE);
        sessionManager = new SessionManager(this);
        String url = BASE_URL+"signin.php?user_name="+username.getText().toString()+"&password="+password.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                progressBar.setVisibility(View.GONE);
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                JSONObject userObject = jsonArray.getJSONObject(0);
                if (!userObject.getString("error").equalsIgnoreCase("no")) {

                    Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();

                } else {

                    redirecttocorrectdashboard();
                }

            } catch (JSONException e) { Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }
        );
        requestQueue.add(stringRequest);

    }
    public void redirecttocorrectdashboard(){

        sessionManager = new SessionManager(this);
        String url = BASE_URL+"signin.php?user_name="+username.getText().toString()+"&password="+password.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject data = jsonArray.getJSONObject(0);
                    if (data.getString("Role").equals("Traffic Police")){
                        startActivity( new Intent(LoginActivity.this, PoliceDashboard.class));
                        sessionManager.createSession(username.getText().toString(),password.getText().toString(),data.getString("Role"));

                        SharedPreferences.Editor editor=sharedPreferences.edit();
                     //   editor.putString("role","police");

                        editor.apply();
                        editor.commit();
                        finish();
                    }
                    else if (data.getString("Role").equals("Owner")){
                        startActivity( new Intent(LoginActivity.this, UserActivity.class));
                        sessionManager.createSession(username.getText().toString(),password.getText().toString(),data.getString("Role"));
                        String uu_name=data.getString("user_name");
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.clear();
                        editor.putString("role","owner");
                        editor.putString("u_name",uu_name);

                        editor.commit();
                        finish();
                    }
                    else if (data.getString("Role").equals("Driver")){
                        startActivity( new Intent(LoginActivity.this, DriverActivity.class));
                        sessionManager.createSession(username.getText().toString(),password.getText().toString(),data.getString("Role"));
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("role","driver");

                        editor.apply();
                        editor.commit();
                        finish();
                    }
                    else if (data.getString("Role").equals("Both")){
                        startActivity( new Intent(LoginActivity.this, BothActivity.class));
                        sessionManager.createSession(username.getText().toString(),password.getText().toString(),data.getString("Role"));
                        String uu_name=data.getString("user_name");
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("role","both");
                        editor.putString("u_name",uu_name);
                        editor.apply();
                        editor.commit();
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }




                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}