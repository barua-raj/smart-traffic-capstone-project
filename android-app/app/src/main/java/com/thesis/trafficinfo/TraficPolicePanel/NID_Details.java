package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.Adapters.Vehicle_adapter;
import com.thesis.trafficinfo.Adapters.owned_vehicle_adapter;
import com.thesis.trafficinfo.LoginAndSignUp.personalInfo;
import com.thesis.trafficinfo.Models.vehicles;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NID_Details extends AppCompatActivity {
    String id,nid;
    TextView name,father,mother,dob,nidtext,address,blood_group,issue,place_of_birth,roleTv,vehicleRegNoTv,driverLicenseNotv;
    ImageView imageView;
    TextView backbtn;
    LinearLayout rest;
    ProgressBar pb;
    RecyclerView recyclerView;
    ProgressBar pb1;
    owned_vehicle_adapter adapter;
    TextView emergency_contact;
    List<vehicles> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nid_details);
        id = getIntent().getStringExtra("id");
        nid = getIntent().getStringExtra("nid");
        name = findViewById(R.id.name);
        father = findViewById(R.id.father);
        mother = findViewById(R.id.mother);
        backbtn = findViewById(R.id.backbtn);
        vehicleRegNoTv=findViewById(R.id.vehicleRegNoTv);
        driverLicenseNotv=findViewById(R.id.driverLicenseNotv);
        recyclerView = findViewById(R.id.recyclerView);
        dob = findViewById(R.id.dob);
        nidtext = findViewById(R.id.nidtext);
        emergency_contact=findViewById(R.id.emergency_contact);
        address = findViewById(R.id.address);
        blood_group = findViewById(R.id.blood_group);
        issue = findViewById(R.id.issue);
        place_of_birth = findViewById(R.id.place_of_birth);
        imageView= findViewById(R.id.imageView);
        rest= findViewById(R.id.rest);
        pb= findViewById(R.id.pb);
        roleTv=findViewById(R.id.roleTv);
        pb1= findViewById(R.id.pb1);
        modelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getInfo();
        getApplicantType();
    }
    public void getInfo(){
        pb.setVisibility(View.VISIBLE);
        nid = getIntent().getStringExtra("nid");
        String url = BASE_URL+"get_details.php?NID_No="+nid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                pb.setVisibility(View.GONE);
                rest.setVisibility(View.VISIBLE);
                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);
                    name.setText(data.getString("Name"));
                    father.setText(data.getString("Father"));
                    mother.setText(data.getString("Mother"));
                    dob.setText(data.getString("Date_of_birth"));
                    nidtext.setText(data.getString("NID_No"));
                    address.setText(data.getString("Address"));
                    blood_group.setText(data.getString("Blood_group"));
                    issue.setText(data.getString("Issue_Date"));
                    roleTv.setText(data.getString("Role"));
                    vehicleRegNoTv.setText(data.getString("Vehicle_reg_no"));
                    driverLicenseNotv.setText(data.getString("Driving_license_no"));
                    place_of_birth.setText(data.getString("Place_of_Birth"));
                    String photo =data.getString("photo");
                    //data.getString("photo");
                    String imageurl3 =BASE_URL+"NID/"+data.getString("photo");
                    Picasso.get().load(imageurl3).into(imageView);
                    getData(data.getString("NID_No"));



                } catch (JSONException e) {
                    pb.setVisibility(View.GONE);
                    rest.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb.setVisibility(View.GONE);
                rest.setVisibility(View.GONE);
                Toast.makeText(NID_Details.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

    }
    public void getData(String NID){
        pb1.setVisibility(View.VISIBLE);
        String url=BASE_URL+"getVehicleByNID.php?NID_No="+NID;
        Log.d("TAG", "datashow: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(NID_Details.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    pb1.setVisibility(View.GONE);
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
                    adapter = new owned_vehicle_adapter(modelList,NID_Details.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    pb1.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb1.setVisibility(View.GONE);
                Toast.makeText(NID_Details.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(stringRequest);

    }
    public void getApplicantType(){


       String url = BASE_URL+"getApplicantType.php?NID_No="+nid;
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
                    String Role= data.getString("Role");
                    getEmergencyContact(data.getString("user_name"));



                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NID_Details.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
    public void checkCases(View view) {
        Intent intent = new Intent(NID_Details.this,CaseHistory.class);
        intent.putExtra("nid",nidtext.getText().toString());
        startActivity(intent);
    }
    public void getEmergencyContact(String owner_name){
        String url = BASE_URL+"getEmergencyContactofDriver.php?owner_name="+owner_name;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                // Log.d("Res=", url);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    for(int i=0; i<=jsonArray.length(); i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        data.optString("number");
                        emergency_contact.setText(data.optString("number"));

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                    //  Toast.makeText(CaseByLicense.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(CaseByLicense.this, ""+error, Toast.LENGTH_SHORT).show();


            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("owner_name",  owner_name);


                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
}