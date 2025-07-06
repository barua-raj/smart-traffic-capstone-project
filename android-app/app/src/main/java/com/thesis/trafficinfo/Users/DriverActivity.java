package com.thesis.trafficinfo.Users;

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
import com.thesis.trafficinfo.BothActivity;
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.NotificationActivity;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.CaseHistory;
import com.thesis.trafficinfo.TraficPolicePanel.TrafficRules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DriverActivity extends AppCompatActivity {
    ImageView imageView;

    ProgressBar progressBar;
    LinearLayout rest;
    String u_photo;
    SessionManager sessionManager;
    String  my_name;
    String n_id,d_nid;
    SharedPreferences sharedPreferences;

    TextView driverNameTv,driverNIDTv,driverLicenseTv,driverRolesTv,driverBloodGroupTv,driverDOBTv,driverAddressTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        sharedPreferences=getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        imageView = findViewById(R.id.imageView);
        driverNameTv=findViewById(R.id.driverNameTv);
        driverNIDTv=findViewById(R.id.driverNIDTv);
        driverLicenseTv=findViewById(R.id.driverLicenseTv);
        driverRolesTv=findViewById(R.id.driverRolesTv);
        driverBloodGroupTv=findViewById(R.id.driverBloodGroupTv);
        driverDOBTv=findViewById(R.id.driverDOBTv);
        driverAddressTv=findViewById(R.id.driverAddressTv);


        rest = findViewById(R.id.rest);
        getUserInfo();
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
                    driverNameTv.setText(data.getString("name"));
                    driverAddressTv.setText(data.getString("division"));
                    driverNIDTv.setText(data.getString("NID_No"));
                    driverRolesTv.setText(data.getString("Role"));
                    driverBloodGroupTv.setText(data.getString("Blood_group"));
                    driverDOBTv.setText(data.getString("date_of_birth"));
                    driverLicenseTv.setText(data.getString("Driving_license_no"));

                    my_name=data.getString("name");



                    n_id=getIntent().getStringExtra("NID_No");
                    u_photo=data.getString("photo_name");
//                    d_nid=data.getString("d_nid");
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                   // editor.putString("d_nid",d_nid);
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
                Toast.makeText(DriverActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
    public void drivedVehicles(View view) {
        Intent intent=new Intent(DriverActivity.this,MyDrivedVehicles.class);
        intent.putExtra("my_name",my_name);
        startActivity(intent);
    }

    public void drivingRequests(View view) {
        Intent intent=new Intent(DriverActivity.this,CheckDrivingRequest.class);
        intent.putExtra("my_name",my_name);
        startActivity(intent);
    }

    public void listOfCases(View view) {
       /* sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);*/
            Intent intent = new Intent(DriverActivity.this, MyCaseHistory.class);
            intent.putExtra("username",my_name);
            intent.putExtra("u_photo",u_photo);
            startActivity(intent);


    }

    public void logout(View view) {
        Intent intent=new Intent(DriverActivity.this, LoginActivity.class);

        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void notification(View view) {
      /*  sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);*/
        Intent intent=new Intent( this, NotificationActivity.class);
        intent.putExtra("username",my_name);
        intent.putExtra("u_photo",u_photo);
        intent.putExtra("nid",n_id);
        startActivity(intent);

    }

    public void viewTrafficRules(View view) {
        startActivity(new Intent(DriverActivity.this, TrafficRules.class));
    }

    public void emergencyContact(View view) {

        startActivity(new Intent(DriverActivity.this, AddEmergencyCOntact.class));
    }
}