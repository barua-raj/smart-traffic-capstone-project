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
import com.thesis.trafficinfo.NotificationActivity;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.FindByNID;
import com.thesis.trafficinfo.TraficPolicePanel.FindByVehicle;
import com.thesis.trafficinfo.TraficPolicePanel.PoliceDashboard;
import com.thesis.trafficinfo.TraficPolicePanel.TrafficRules;
import com.thesis.trafficinfo.TraficPolicePanel.WriteNID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {
    ImageView imageView;
    TextView name,address;
    ProgressBar progressBar;
    LinearLayout rest;
    SessionManager sessionManager;
      String usernames,d_nid;
      SharedPreferences sharedPreferences;
      TextView ownerNameTv,ownerNIDTv,ownerRolesTv,ownerBloodGroupTv,ownerDOBTv,ownerAddressTv;
      String  u_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sharedPreferences=getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        imageView = findViewById(R.id.imageView);
        ownerNameTv=findViewById(R.id.ownerNameTv);
        ownerNIDTv=findViewById(R.id.ownerNIDTv);
        ownerRolesTv=findViewById(R.id.ownerRolesTv);
        ownerBloodGroupTv=findViewById(R.id.ownerBloodGroupTv);
        ownerDOBTv=findViewById(R.id.ownerDOBTv);
        ownerAddressTv=findViewById(R.id.ownerAddressTv);


       // policeid = findViewById(R.id.policeid);
     //   progressBar = findViewById(R.id.pb);
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
                    ownerNameTv.setText(data.getString("name"));
                    ownerAddressTv.setText(data.getString("division"));
                    ownerNIDTv.setText(data.getString("NID_No"));
                    ownerRolesTv.setText(data.getString("Role"));
                    ownerBloodGroupTv.setText(data.getString("Blood_group"));
                    ownerDOBTv.setText(data.getString("date_of_birth"));
                    d_nid=data.getString("NID_No");
                usernames=data.getString("name");
                    u_photo=data.getString("photo_name");

                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putString("d_nid",d_nid);
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
                Toast.makeText(UserActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }

    public void logout(View view) {
        sessionManager = new SessionManager(this);
        sessionManager.logout();
    }
    public void license(View view) {
        startActivity(new Intent(UserActivity.this, CheckDriverLicense.class));
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


    public void ListofDrivers(View view) {
        startActivity( new Intent(UserActivity.this,MyDriverList.class));

    }

    public void ListofVehicles(View view) {
        startActivity(new Intent(UserActivity.this,MyVehicleList.class));

    }

    public void addEmergencyContact(View view) {
        startActivity(new Intent(UserActivity.this,AddEmergencyCOntact.class));
    }
    public void notification(View view) {
        sessionManager = new SessionManager(this);


        Intent intent=new Intent( this, NotificationActivity.class);
        intent.putExtra("type","both");
        intent.putExtra("username",usernames);
        intent.putExtra("u_photo",u_photo);
        //intent.putExtra("nid",n_id);
        startActivity(intent);

    }
    public void viewTrafficRules(View view) {
        startActivity(new Intent(UserActivity.this, TrafficRules.class));
    }
}