package com.thesis.trafficinfo;

import static com.thesis.trafficinfo.Constant.BASE_URL;
import static com.thesis.trafficinfo.SessionManager.SessionManager.EMAIL;
import static com.thesis.trafficinfo.SessionManager.SessionManager.ROLE;
import static com.thesis.trafficinfo.SessionManager.SessionManager.USER;
import static com.thesis.trafficinfo.Users.AddDriverMyVehicle.PREF_NAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.Adapters.History_Adapter;
import com.thesis.trafficinfo.Adapters.NotificationAdapter;
import com.thesis.trafficinfo.Adapters.RequestNotificationAdapter;
import com.thesis.trafficinfo.LoginAndSignUp.History;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.Users.MyCaseHistory;
import com.thesis.trafficinfo.Users.MyDriver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationActivity extends AppCompatActivity {

    String n_id;
    SessionManager sessionManager;
    List<History> modelList;
    RecyclerView recyclerView;
    TextView backbtn;
    List<MyDriver> myDrivers;
    ProgressBar progressBar;

    String uname;
    NotificationAdapter adapter;
    RequestNotificationAdapter requestNotificationAdapter;
    String u_photo;
    String type;
    String  u_name,uuname;
    SharedPreferences sharedPreferences;
    RecyclerView requestacceptedRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sharedPreferences=getApplicationContext().getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        type = sharedPreferences.getString(ROLE,null);
        recyclerView = findViewById(R.id.notificationRecycler);
        progressBar = findViewById(R.id.pb);
        requestacceptedRv=findViewById(R.id.requestacceptedRv);
        requestacceptedRv.setHasFixedSize(true);
        requestacceptedRv.setLayoutManager(new LinearLayoutManager(this));

        uname=sharedPreferences.getString("d_name",null);
        u_name=getIntent().getStringExtra("username");
        uuname=sharedPreferences.getString(EMAIL,null);


        n_id = getIntent().getStringExtra("nid");
        //Toast.makeText(this, ""+uname, Toast.LENGTH_SHORT).show();


        u_photo=getIntent().getStringExtra("u_photo");
       // Toast.makeText(this, u_photo+"", Toast.LENGTH_SHORT).show();

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myDrivers=new ArrayList<>();
        modelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (type.contains("both")){


            getAcceptedNoti(uuname);
        }else if (type.contains("driver")){
            getDataa(u_name);
        }

        else {
          //  Toast.makeText(this, ""+uuname, Toast.LENGTH_SHORT).show();
            getDataa(u_name);
            getAcceptedNoti(uuname);
        }
    }

    public void getDataa(String  username){

        String url=BASE_URL+"getmycases.php?Applicant_name="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(NotificationActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                 //   progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new History(
                                data.getString("id"),
                                data.getString("Applicant_name"),
                                data.getString("Applicant_type"),
                                data.getString("License_no"),
                                data.getString("crime"),
                                data.getString("imprisonment"),
                                data.getString("fine"),
                                data.getString("fine_type"),
                                data.getString("Vehicle_type"),
                                data.getString("Vehicle_number"),
                                data.getString("Type_of_fine"),
                                data.getString("NID_No"),
                                data.getString("Police_id"),
                                data.getString("feedback"),
                                data.getString("Status"),
                                u_photo,
                                data.getString("case_date")
                        ));

                    }
                    adapter = new NotificationAdapter(modelList,NotificationActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                   // progressBar.setVisibility(View.GONE);
                   // nodata.setVisibility(View.VISIBLE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // progressBar.setVisibility(View.GONE);
             //   nodata.setText("Server Down!");
            //    nodata.setVisibility(View.VISIBLE);



            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("Applicant_name",  username);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
    public void getBothDataa(String  username){

        String url=BASE_URL+"getmycases.php?Applicant_name="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(NotificationActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //   progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new History(
                                data.getString("id"),
                                data.getString("Applicant_name"),
                                data.getString("Applicant_type"),
                                data.getString("License_no"),
                                data.getString("crime"),
                                data.getString("imprisonment"),
                                data.getString("fine"),
                                data.getString("fine_type"),
                                data.getString("Vehicle_type"),
                                data.getString("Vehicle_number"),
                                data.getString("Type_of_fine"),
                                data.getString("NID_No"),
                                data.getString("Police_id"),
                                data.getString("feedback"),
                                data.getString("Status"),
                                u_photo,
                                data.getString("case_date")
                        ));

                    }
                    adapter = new NotificationAdapter(modelList,NotificationActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    // progressBar.setVisibility(View.GONE);
//                    nodata.setVisibility(View.VISIBLE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressBar.setVisibility(View.GONE);



            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("Applicant_name",  username);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
    public void getAcceptedNoti(String  username){

        String url=BASE_URL+"getDrivingRequests.php?user_name="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(NotificationActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
              //      nodata.setVisibility(View.GONE);
                    getBothDataa(uname);
                    //   progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        myDrivers.add(new MyDriver(
                                data.getString("d_name"),
                                data.getString("status")
                        ));

                    }
                    requestNotificationAdapter = new RequestNotificationAdapter(myDrivers,NotificationActivity.this);
                    requestacceptedRv.setAdapter(requestNotificationAdapter);
                    requestNotificationAdapter.notifyDataSetChanged();

                }catch (JSONException e){
                    // progressBar.setVisibility(View.GONE);
                 //   nodata.setVisibility(View.VISIBLE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressBar.setVisibility(View.GONE);
               // nodata.setText("Server Down!");
              //  nodata.setVisibility(View.VISIBLE);



            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("user_name",  username);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
}