package com.thesis.trafficinfo;

import static com.thesis.trafficinfo.Constant.BASE_URL;
import static com.thesis.trafficinfo.SessionManager.SessionManager.ROLE;
import static com.thesis.trafficinfo.Users.AddDriverMyVehicle.PREF_NAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.LoginAndSignUp.Select_Role;
import com.thesis.trafficinfo.Users.DriverActivity;
import com.thesis.trafficinfo.Users.UserActivity;

import java.util.HashMap;
import java.util.Map;

public class ConfirmPayment extends AppCompatActivity {

    String id,role;
    TextView backbtn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        sharedPreferences=getApplicationContext().getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        role=sharedPreferences.getString(ROLE,"nai");
        Toast.makeText(this, ""+role, Toast.LENGTH_SHORT).show();
        id = getIntent().getStringExtra("id");backbtn = findViewById(R.id.backbtn);
        ConfirmPayment(id);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role.contains("owner")){
                    startActivity(new Intent(ConfirmPayment.this, UserActivity.class));
                }
                else if (role.contains("driver")){
                    startActivity(new Intent(ConfirmPayment.this, DriverActivity.class));
                }
                else if (role.contains("both")){
                    startActivity(new Intent(ConfirmPayment.this, BothActivity.class));
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (role.contains("owner")){
            startActivity(new Intent(ConfirmPayment.this, UserActivity.class));
        }
        else if (role.contains("driver")){
            startActivity(new Intent(ConfirmPayment.this, DriverActivity.class));
        }
        else if (role.contains("both")){
            startActivity(new Intent(ConfirmPayment.this, BothActivity.class));
        }
    }

    public void ConfirmPayment(String id){


            String url = BASE_URL+"confirmpayment.php?id="+id;
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(ConfirmPayment.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    Toast.makeText(ConfirmPayment.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(ConfirmPayment.this, "3", Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringStringMap = new HashMap<>();

                    stringStringMap.put("id", id);

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);

    }
}