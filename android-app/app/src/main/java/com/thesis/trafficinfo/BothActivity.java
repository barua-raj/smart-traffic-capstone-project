package com.thesis.trafficinfo;

import static com.thesis.trafficinfo.Constant.BASE_URL;
import static com.thesis.trafficinfo.Users.AddDriverMyVehicle.PREF_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.TrafficRules;
import com.thesis.trafficinfo.Users.AddEmergencyCOntact;
import com.thesis.trafficinfo.Users.CheckDriverLicense;
import com.thesis.trafficinfo.Users.DriverActivity;
import com.thesis.trafficinfo.Users.MyCaseHistory;
import com.thesis.trafficinfo.Users.MyDrivedVehicles;
import com.thesis.trafficinfo.Users.MyDriverList;
import com.thesis.trafficinfo.Users.MyVehicleList;
import com.thesis.trafficinfo.Users.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class BothActivity extends AppCompatActivity {
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout rest;
    SessionManager sessionManager;
    String u_photo,d_license,d_nid;
    SharedPreferences sharedPreferences;
    TextView bothNameTv,bothNIDTv,bothLicense,bothRolesTv,bothBloodGroupTv,bothDOBTv,bothAddressTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both);
        sharedPreferences=getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        bothNameTv=findViewById(R.id.bothNameTv);
        bothNIDTv=findViewById(R.id.bothNIDTv);
        bothLicense=findViewById(R.id.bothLicense);
        bothRolesTv=findViewById(R.id.bothRolesTv);
        bothBloodGroupTv=findViewById(R.id.bothBloodGroupTv);
        bothDOBTv=findViewById(R.id.bothDOBTv);
        bothAddressTv=findViewById(R.id.bothAddressTv);

        imageView = findViewById(R.id.imageView);
        //   progressBar = findViewById(R.id.pb);
        rest = findViewById(R.id.rest);
        getUserInfo();
    }

    public void license(View view) {
        startActivity(new Intent(BothActivity.this, CheckDriverLicense.class));
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


    public void ListofDrivers(View view) {
        startActivity( new Intent(BothActivity.this, MyDriverList.class));

    }

    public void ListofVehicles(View view) {
        startActivity(new Intent(BothActivity.this, MyVehicleList.class));

    }

    public void addEmergencyContact(View view) {
        startActivity(new Intent(BothActivity.this, AddEmergencyCOntact.class));
    }
    public void drivedVehicles(View view) {
        Intent intent=new Intent(BothActivity.this, MyDrivedVehicles.class);
        startActivity(intent);
    }

    public void listOfCases(View view) {
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);
        Intent intent = new Intent(BothActivity.this, MyCaseHistory.class);
        intent.putExtra("username",username);
        intent.putExtra("u_photo",u_photo);
        startActivity(intent);


    }

    public void logout(View view) {
        sessionManager = new SessionManager(this);
        sessionManager.logout();
    }

    public void getUserInfo(){
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);
        //  progressBar.setVisibility(View.VISIBLE);
        String url = BASE_URL+"getuserdetails.php?user_name="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                //  progressBar.setVisibility(View.GONE);
                rest.setVisibility(View.VISIBLE);
                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);
                    String  name=data.getString("name");
                    bothNameTv.setText(name);
                    bothNIDTv.setText(data.getString("NID_No"));
                    bothLicense.setText(data.getString("Driving_license_no"));
                    bothRolesTv.setText(data.getString("Role"));
                    bothBloodGroupTv.setText(data.getString("Blood_group"));
                    bothDOBTv.setText(data.getString("date_of_birth"));
                    bothAddressTv.setText(data.getString("division"));


                    u_photo=data.getString("photo_name");

                    d_license=data.getString("Driving_license_no");
                    d_nid=data.getString("NID_No");

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("d_license",d_license);
                    editor.putString("d_nid",d_nid);
                    editor.putString("d_name",name);
                    editor.apply();
                    editor.commit();

                    //data.getString("photo");
                    String imageurl3 =BASE_URL+"NID/"+data.getString("photo_name");
                    Picasso.get().load(imageurl3).into(imageView);



                } catch (JSONException e) {
                    //      progressBar.setVisibility(View.GONE);
                    rest.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    progressBar.setVisibility(View.GONE);
                rest.setVisibility(View.GONE);
                Toast.makeText(BothActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
    public void notification(View view) {
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);
        Intent intent=new Intent( this, NotificationActivity.class);
        intent.putExtra("username",username);
        //intent.putExtra("u_photo",u_photo);
        //intent.putExtra("nid",n_id);
        startActivity(intent);

    }
    public void viewTrafficRules(View view) {
        startActivity(new Intent(BothActivity.this, TrafficRules.class));

    }


}