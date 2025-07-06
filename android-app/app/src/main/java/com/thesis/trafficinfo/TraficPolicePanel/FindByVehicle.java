package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.thesis.trafficinfo.Adapters.Drivers_adapter;
import com.thesis.trafficinfo.Adapters.Vehicle_adapter;
import com.thesis.trafficinfo.Models.drivers;
import com.thesis.trafficinfo.Models.vehicles;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByVehicle extends AppCompatActivity {
    EditText search;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Vehicle_adapter adapter;
    List<vehicles> modelList;
    TextView backbtn;
    String nid;
    SessionManager sessionManager;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_vehicle);
        getSergent_info();
        number=getIntent().getStringExtra("licensenumber");
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.pb);
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
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

       // getData();
    }
    public void filter(String text) //search button er kaj new list e add kora
    {
        ArrayList<vehicles> modelArrayList = new ArrayList<>();
        for(vehicles model: modelList)
        {
            if(model.getRegistration_No().toLowerCase().contains(text.toLowerCase())){
                modelArrayList.add(model);

            }
            adapter.filteredList(modelArrayList);
        }
    }
    public void getSergent_info(){
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);

        String url = BASE_URL+"getuserdetails.php?user_name="+username;
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
                    nid = data.getString("NID_No");
                    Log.d("TAG", "NID no: "+nid);
                    getDataa();




                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        requestQueue.add(stringRequest);
    }
    public void getDataa(){
        progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"getVehicles.php?Registration_No="+number;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new vehicles(
                                data.getString("id"),
                                data.getString("Registration_No"),
                                data.getString("Vehicle_Description"),
                                data.getString("Vehicle_class"),
                                data.getString("CC"),
                                data.getString("Color"),
                                data.getString("Fuel"),
                                data.getString("Seat"),
                                data.getString("Engine_No"),
                                data.getString("Chassis_No"),
                                data.getString("Hire"),
                                data.getString("Date"),
                                data.getString("Wheel_Base"),
                                data.getString("Issuing_Authority"),
                                data.getString("Weight_Unladen"),
                                data.getString("Weight_laden"),
                                data.getString("Owner_name"),
                                data.getString("Owner_address"),
                                data.getString("Mfg_Year"),
                                data.getString("NID_No"),
                                data.getString("photo"),
                                data.getString("routepermit_certificate_no"),
                                data.getString("routepermit_expiry_date"),
                                data.getString("fitness_certificate_no"),
                                data.getString("fitness_certificate_expiry_date"),
                                data.getString("taxtoken_no"),
                                data.getString("taxperiod_expiry_date")
                        ));

                    }
                    adapter = new Vehicle_adapter(modelList,FindByVehicle.this);
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
                stringStringMap.put("NID_No",  nid);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }

}