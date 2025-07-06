package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.LoginAndSignUp.Select_Role;
import com.thesis.trafficinfo.Models.drivers;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByLicense extends AppCompatActivity {
    EditText search;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Drivers_adapter adapter;
    List<drivers> modelList;
    TextView backbtn;
    String nid;
    SessionManager sessionManager;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_license);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.pb);
        backbtn = findViewById(R.id.backbtn);
        getSergent_info();

        number=getIntent().getStringExtra("licensenumber");
        getDataa(number);
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
                   // getDataa(number);




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

    public void filter(String text) //search button er kaj new list e add kora
    {
        ArrayList<drivers> modelArrayList = new ArrayList<>();
        for(drivers model: modelList)
        {
            if(model.getRef_no().toLowerCase().contains(text.toLowerCase())){
                modelArrayList.add(model);

            }
            adapter.filteredList(modelArrayList);
        }
    }

    public void getDataa(String nid){
        progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"get_user.php?ref_no="+nid;
        RequestQueue requestQueue = Volley.newRequestQueue(FindByLicense.this);
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
                        modelList.add(new drivers(
                                data.getString("id"),
                                data.getString("ref_no"),
                                data.getString("type_of_app"),
                                data.getString("dctb_serial"),
                                data.getString("type_of_license"),
                                data.getString("velicle_class"),
                                data.getString("name"),
                                data.getString("user_photo"),
                                data.getString("fathers_name"),
                                data.getString("mothers_name"),
                                data.getString("spouse_name"),
                                data.getString("dob"),
                                data.getString("card_delivery_date"),
                                data.getString("address"),
                                data.getString("ref_date"),
                                data.getString("application"),
                                data.getString("dctb_date"),
                                data.getString("issue_authority"),
                                data.getString("NID_No"),
                                data.getString("ref_expiry_date")
                        ));

                    }
                    adapter = new Drivers_adapter(modelList,FindByLicense.this);
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
    public void getData(){
        String url=BASE_URL+"get_user.php";
        Log.d("TAG", "datashow: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(FindByLicense.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new drivers(
                                data.getString("id"),
                                data.getString("ref_no"),
                                data.getString("type_of_app"),
                                data.getString("dctb_serial"),
                                data.getString("type_of_license"),
                                data.getString("velicle_class"),
                                data.getString("name"),
                                data.getString("user_photo"),
                                data.getString("fathers_name"),
                                data.getString("mothers_name"),
                                data.getString("spouse_name"),
                                data.getString("dob"),
                                data.getString("card_delivery_date"),
                                data.getString("address"),
                                data.getString("ref_date"),
                                data.getString("application"),
                                data.getString("dctb_date"),
                                data.getString("issue_authority")
                        ));

                    }
                    adapter = new Drivers_adapter(modelList,FindByLicense.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(FindByLicense.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(stringRequest);

    }
}