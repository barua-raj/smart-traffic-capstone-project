package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.thesis.trafficinfo.LoginAndSignUp.History;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.CaseHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCaseHistory extends AppCompatActivity {
    String nid;
    EditText search;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    History_Adapter adapter;
    List<History> modelList;
    TextView backbtn;


    TextView nodata;

    SessionManager sessionManager;
    String u_photo,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_case_history);
        nid = getIntent().getStringExtra("nid");
        search = findViewById(R.id.search);
        u_photo=getIntent().getStringExtra("u_photo");
        username=getIntent().getStringExtra("username");
      //  Toast.makeText(this, ""+username, Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.recyclerView);
      //  Toast.makeText(this, ""+u_photo, Toast.LENGTH_SHORT).show();
        progressBar = findViewById(R.id.pb);
        nodata = findViewById(R.id.nodata);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        modelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataa();

    }
    public void getDataa(){


        progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"getMyHistory.php?Applicant_name="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(MyCaseHistory.this);
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
                                u_photo
                        ));

                    }
                    adapter = new History_Adapter(modelList,MyCaseHistory.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    progressBar.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                nodata.setText("Server Down!");
                nodata.setVisibility(View.VISIBLE);



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
}