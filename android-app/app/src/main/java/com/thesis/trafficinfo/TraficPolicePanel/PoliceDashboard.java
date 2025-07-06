package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.thesis.trafficinfo.LoginAndSignUp.personalInfo;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.WriteNationalIDCard;
import com.thesis.trafficinfo.WriteVehicleRegistrationID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PoliceDashboard extends AppCompatActivity {
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout rest;
    SessionManager sessionManager;
    TextView policeNameTv,policeNIDTv,policeID,policeRolesTv,policeBloodGroupTv,policeDOBTv,policeAddressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_dashboard);
        imageView = findViewById(R.id.imageView);
        policeNameTv=findViewById(R.id.policeNameTv);
        policeNIDTv=findViewById(R.id.policeNIDTv);
        policeID=findViewById(R.id.policeID);
        policeRolesTv=findViewById(R.id.policeRolesTv);
        policeBloodGroupTv=findViewById(R.id.policeBloodGroupTv);
        policeDOBTv=findViewById(R.id.policeDOBTv);
        policeAddressTv=findViewById(R.id.policeAddressTv);


        progressBar = findViewById(R.id.pb);
        rest = findViewById(R.id.rest);
        getUserInfo();

    }

    public void getUserInfo(){
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);
        progressBar.setVisibility(View.VISIBLE);
        String url = BASE_URL+"getuserdetails.php?user_name="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                rest.setVisibility(View.VISIBLE);
                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);
                    policeNameTv.setText(data.getString("name"));
                    policeID.setText(data.getString("Police_id"));
                    policeAddressTv.setText(data.getString("division"));
                    policeNIDTv.setText(data.getString("NID_No"));
                    policeRolesTv.setText(data.getString("Role"));
                    policeBloodGroupTv.setText(data.getString("Blood_group"));
                    policeDOBTv.setText(data.getString("date_of_birth"));



                    //data.getString("photo");
                    String imageurl3 =BASE_URL+"NID/"+data.getString("photo_name");
                    Picasso.get().load(imageurl3).into(imageView);



                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    rest.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                rest.setVisibility(View.GONE);
                Toast.makeText(PoliceDashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }

    public void logout(View view) {
        sessionManager = new SessionManager(this);
        sessionManager.logout();
    }
    public void license(View view) {
        startActivity(new Intent(PoliceDashboard.this,WriteNID.class));
    }
    public void vehicle(View view) {
        startActivity(new Intent(PoliceDashboard.this, WriteVehicleRegistrationID.class));
    }
    public void nidsearch(View view) {
        startActivity(new Intent(PoliceDashboard.this, WriteNationalIDCard.class));
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


    public void viewTrafficRules(View view) {
        startActivity(new Intent(PoliceDashboard.this,TrafficRules.class));
    }
}