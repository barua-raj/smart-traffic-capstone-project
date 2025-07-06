package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.CaseHistory2;
import com.thesis.trafficinfo.TraficPolicePanel.CheckDues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckMyVehicleDetails extends AppCompatActivity {
    String engine_no,v_cc,v_color,mfgyear,route_permit_certificateno,r_e_d,taxtokenno,taxtokenno_ed,fitness,fitness_ed;
    String reg,photo,nid,owner_name,v_class,chassis_no,issuer;
    TextView name,vehicle_class,nidtext,chassis,issue,backbtn,license,vehicleRegNoTV,engineEt,chassisET,routePermitCertificateNoET
            ,routePermitExpiryDate,fitnessCertificateET,fitnessCertificateExpiryDate,mfgyearEt,blood_group,emergency_contact,ccET,colorEt
            ,taxTokenTV,taxtokenExpiryDate;
    ImageView imageView;
    ProgressBar progressBar1,progressBar;
    EditText applicantTypeET;
    EditText vehicle_number,feedback,fine_amount,case_timeEt,case_LocEt,appearanceDateEt;
    Spinner spinner,spinner1,spinner2,spinner3,addressSpinner,seized_documentSp;
    ArrayList<String> category0,category1,category2,category3,category,divArray,seizSpinnerArray;;
    ArrayAdapter<String> catAdapter0,catAdapter1,catAdapter2,catAdapter3,catAdapter,divAdapter,seizSpinnerAdapter;
    LinearLayout rest;
    LinearLayout licenseLinearLayout,vehiclLinearLayout;
    String police_id;
    SessionManager sessionManager;
    String first_fine,second_fine,fine,cc;
    TextView law;
    String formattedDate;
    private SmartMaterialSpinner<String> spinnercrime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_vehicle_details);
        engineEt=findViewById(R.id.engineEt);
        chassisET=findViewById(R.id.chassis);
        routePermitCertificateNoET=findViewById(R.id.routePermitCertificateNo);
        routePermitExpiryDate=findViewById(R.id.routePermitExpiryDate);
        fitnessCertificateET=findViewById(R.id.fitnessCertificateET);
        fitnessCertificateExpiryDate=findViewById(R.id.fitnessCertificateExpiryDate);
        mfgyearEt=findViewById(R.id.mfgyearEt);
        emergency_contact=findViewById(R.id.emergency_contact);
        blood_group=findViewById(R.id.blood_group);
        ccET=findViewById(R.id.ccET);
        colorEt=findViewById(R.id.colorEt);

        case_LocEt=findViewById(R.id.case_LocEt);
        case_timeEt=findViewById(R.id.case_timeEt);
        appearanceDateEt=findViewById(R.id.appearanceDateEt);
        seized_documentSp=findViewById(R.id.seized_documentSp);
        taxTokenTV=findViewById(R.id.taxTokenTV);
        taxtokenExpiryDate=findViewById(R.id.taxtokenExpiryDate);

        getSergent_info();
        reg = getIntent().getStringExtra("reg");
        photo = getIntent().getStringExtra("photo");
        nid = getIntent().getStringExtra("nid");
        owner_name = getIntent().getStringExtra("owner_name");
        v_class = getIntent().getStringExtra("v_class");
        chassis_no = getIntent().getStringExtra("chassis_no");
        engine_no = getIntent().getStringExtra("engine_no");
        v_cc = getIntent().getStringExtra("cc");
        v_color = getIntent().getStringExtra("color");
        mfgyear = getIntent().getStringExtra("mfgyear");
        route_permit_certificateno = getIntent().getStringExtra("route_permit_certificateno");
        r_e_d = getIntent().getStringExtra("r_e_d");
        taxtokenno = getIntent().getStringExtra("taxtokenno");
        taxtokenno_ed = getIntent().getStringExtra("taxtokenno_ed");
        fitness = getIntent().getStringExtra("fitness");
        fitness_ed = getIntent().getStringExtra("fitness_ed");
        issuer = getIntent().getStringExtra("issuer");





        imageView = findViewById(R.id.imageView);
        String imageurl3 =BASE_URL+"/NID/"+photo;
        Picasso.get().load(imageurl3).into(imageView);
        name= findViewById(R.id.name);
        vehicle_class= findViewById(R.id.vehicle_class);


        license = findViewById(R.id.license);
        licenseLinearLayout=findViewById(R.id.licenseLinearLayout);
        vehiclLinearLayout=findViewById(R.id.vehiclLinearLayout);
        vehicleRegNoTV=findViewById(R.id.vehicleRegNoTV);
        nidtext= findViewById(R.id.nidtext);


        chassis= findViewById(R.id.chassis);

        issue= findViewById(R.id.issue);




        rest = findViewById(R.id.rest);
        progressBar = findViewById(R.id.pb);
        progressBar1 = findViewById(R.id.pb1);

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        name.setText(owner_name);
        vehicle_class.setText(v_class);
        nidtext.setText(nid);
        chassis.setText(chassis_no);
        issue.setText(issuer);
        //  vehicle_number.setText(reg);
        engineEt.setText(engine_no);
        ccET.setText(v_cc);
        colorEt.setText(v_color);
        routePermitCertificateNoET.setText(route_permit_certificateno);
        routePermitExpiryDate.setText(r_e_d);
        fitnessCertificateET.setText(fitness);
        fitnessCertificateExpiryDate.setText(fitness_ed);
        taxTokenTV.setText(taxtokenno);
        taxtokenExpiryDate.setText(taxtokenno_ed);
        mfgyearEt.setText(mfgyear);


        //Vehicle Class type
        getApplicantType();


    }
    public void getCrimeCategory(){

        String url = BASE_URL+"get_crime_cat.php";

        //+categoryStringList.get(catSpinner.getSelectedItemPosition());
        Log.d("TAG", "datashow: "+url);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                // Log.d("Res=", url);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Results");
                    for(int i=0; i<=jsonArray.length(); i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        String cat = data.optString("crime_type");
                        category.add(cat);
                        spinnercrime.setItem(category);
                        /*catAdapter = new ArrayAdapter<>(CaseByVehicle.this, android.R.layout.simple_spinner_item, category);
                        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(catAdapter);
                        catAdapter.notifyDataSetChanged();*/
                    }




                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }
        );

        requestQueue.add(stringRequest);

    }
    public void getApplicantType(){


        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);

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
//                    applicantTypeET.setText(data.getString("Role"));

                    String Role= data.getString("Role");
                    if (Role.contains("Driver")){
                        licenseLinearLayout.setVisibility(View.VISIBLE);
                        license.setText(data.getString("Driving_license_no"));
                        blood_group.setText(data.getString("Blood_group"));
                        getEmergencyContact(data.getString("user_name"));

                    }
                    else if(Role.contains("Owner")){
                        vehiclLinearLayout.setVisibility(View.VISIBLE);
                        vehicleRegNoTV.setText(data.getString("Vehicle_reg_no"));
                        blood_group.setText(data.getString("Blood_group"));
                        getEmergencyContact(data.getString("user_name"));

                    }
                    else if(Role.contains("Both")){
                        licenseLinearLayout.setVisibility(View.VISIBLE);
                        vehiclLinearLayout.setVisibility(View.VISIBLE);
                        license.setText(data.getString("Driving_license_no"));
                        vehicleRegNoTV.setText(data.getString("Vehicle_reg_no"));
                        blood_group.setText(data.getString("Blood_group"));
                        getEmergencyContact(data.getString("user_name"));

                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckMyVehicleDetails.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
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
                    police_id = data.getString("id");




                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckMyVehicleDetails.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
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


    public void checkhistory(View view) {

        Intent intent = new Intent(CheckMyVehicleDetails.this, CaseHistory2.class);
        intent.putExtra("nid",nidtext.getText().toString());
        intent.putExtra("vehicle_reg",reg);

        startActivity(intent);

    }

    public void checkdues(View view) {
        Intent intent = new Intent(CheckMyVehicleDetails.this, CheckDues.class);
        intent.putExtra("vehicle_reg",reg);
        startActivity(intent);

    }
}