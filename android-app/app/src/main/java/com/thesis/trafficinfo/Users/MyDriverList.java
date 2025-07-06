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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.Adapters.Drivers_adapter;
import com.thesis.trafficinfo.Models.drivers;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.FindByLicense;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDriverList extends AppCompatActivity {
    EditText search;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MyDriverAdapter adapter;
    List<MyDriver> modelList;
    TextView backbtn;
    String nid;
    SessionManager sessionManager;
    String number;
    SharedPreferences sharedPreferences;
    public static final String EMAIL = "EMAIL";

    int PRIVATE_MODE =0;
    String user_name;
    private static final String PREF_NAME ="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_driver_list);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.pb);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sharedPreferences=getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        user_name=sharedPreferences.getString(EMAIL,null);
        modelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataa(user_name);


    }

    public void getDataa(String nid){
        progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"getMyDriverList.php?user_name="+nid;
        RequestQueue requestQueue = Volley.newRequestQueue(MyDriverList.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new MyDriver(
                                data.getString("id"),
                                data.getString("d_name"),
                                data.getString("Driving_license_no"),
                                data.getString("Vehicle_reg_no"),
                                data.getString("user_name"),
                                data.getString("phone"),
                                data.getString("location"),
                                data.getString("status"),
                                data.getString("image")
                        ));

                    }
                    adapter = new MyDriverAdapter(modelList,MyDriverList.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    progressBar.setVisibility(View.GONE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);



            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("ref_no",  nid);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
}