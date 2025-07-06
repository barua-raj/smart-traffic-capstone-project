package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.Adapters.Vehicle_adapter;
import com.thesis.trafficinfo.Adapters.feesAdapter;
import com.thesis.trafficinfo.Models.Fee;
import com.thesis.trafficinfo.Models.vehicles;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckDues extends AppCompatActivity {
    String vehicle_reg;
    List<Fee> modelList;
    feesAdapter adapter;
    TextView routepermit,fitness,cc,taxtoken,ownership,reg,backbtn,nodata;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_dues);
        vehicle_reg = getIntent().getStringExtra("vehicle_reg");
        modelList = new ArrayList<>();
        routepermit = findViewById(R.id.routepermit);
        recyclerView = findViewById(R.id.recyclerView);
        nodata = findViewById(R.id.nodata);
        progressBar = findViewById(R.id.pb);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fitness = findViewById(R.id.fitness);
        taxtoken = findViewById(R.id.taxtoken);
        cc = findViewById(R.id.cc);
        ownership = findViewById(R.id.ownership);
        reg = findViewById(R.id.reg);
        getVehicleInfo();
        reg.setText(vehicle_reg);
        getdues();


    }
    public void getVehicleInfo(){
        String url = BASE_URL+"getVehicle.php?Registration_no="+vehicle_reg;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);
                    data.getString("id");
                    routepermit.setText(data.getString("Road_permit_expire"));
                    fitness.setText(data.getString("Fitness_expire"));
                    taxtoken.setText(data.getString("Tax_token_expire"));
                    ownership.setText(data.getString("Ownership_type"));
                    cc.setText(data.getString("CC"));
                    if (data.getString("Role").equals("None")){
                        nodata.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                    else{
                        recyclerView.setVisibility(View.VISIBLE);
                    }




                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckDues.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }

    public void getdues(){
        progressBar.setVisibility(View.VISIBLE);
        String url = BASE_URL+"getVehicle.php?Registration_no="+vehicle_reg;
        Log.d("TAG", "datashow: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new Fee(
                                data.getString("id"),
                                data.getString("Registration_no"),
                                data.getString("Ownership_type"),
                                data.getString("CC"),
                                data.getString("Tax_token_expire"),
                                data.getString("Fitness_expire"),
                                data.getString("Road_permit_expire"),
                                data.getString("Rule"),
                                data.getString("Main_Fees"),
                                data.getString("Extra_price"),
                                data.getString("Inspection_fee"),
                                data.getString("Label_fees"),
                                data.getString("Application_fee"),
                                data.getString("Delay_fines"),
                                data.getString("Other_fees"),
                                data.getString("VAT"),
                                data.getString("Total_fees")
                        ));

                    }
                    adapter = new feesAdapter(modelList,CheckDues.this);
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
                nodata.setVisibility(View.VISIBLE);
                Toast.makeText(CheckDues.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(stringRequest);
    }
}