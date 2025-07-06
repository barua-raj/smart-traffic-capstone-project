package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.thesis.trafficinfo.Adapters.DrivingRequestAdapter;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckDrivingRequest extends AppCompatActivity {

    SessionManager sessionManager;
    List<MyDriver> modelList;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    public static final String EMAIL = "EMAIL";
    TextView backbtn;
    DrivingRequestAdapter drivingRequestAdapter;

    int PRIVATE_MODE =0;
    TextView noDataFoundTv;
    String user_name;
    private static final String PREF_NAME ="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_driving_request);
        noDataFoundTv=findViewById(R.id.noDataFoundTv);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        user_name=getIntent().getStringExtra("my_name");
        Toast.makeText(this, ""+user_name, Toast.LENGTH_SHORT).show();

        sharedPreferences=getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        modelList=new ArrayList<>();
        recyclerView=findViewById(R.id.drivingRequestRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDrivingRequest();

    }


    public void getDrivingRequest(){
        sessionManager = new SessionManager(this);


        String url=BASE_URL+"checkdrivingrequest.php?name="+user_name;
        RequestQueue requestQueue = Volley.newRequestQueue(CheckDrivingRequest.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new MyDriver(
                                data.getString("id"),
                                data.getString("name"),
                                data.getString("Driving_license_no"),
                                data.getString("Vehicle_reg_no"),
                                data.getString("user_name"),
                                data.getString("phone"),
                                data.getString("location"),
                                data.getString("status"),
                                data.getString("photo_name"),
                                data.getString("d_name")
                        ));

                    }
                    drivingRequestAdapter = new DrivingRequestAdapter(modelList,CheckDrivingRequest.this);
                    recyclerView.setAdapter(drivingRequestAdapter);
                    drivingRequestAdapter.notifyDataSetChanged();

                }catch (JSONException e){
              //      progressBar.setVisibility(View.GONE);

                    noDataFoundTv.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   progressBar.setVisibility(View.GONE);



            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("name",  user_name);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
}