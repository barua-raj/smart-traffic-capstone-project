package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsOfVehicles extends AppCompatActivity {
    TextView nameTv,v_regTv,phoneTv,locationTv;
    String name, v_reg, phone, location,id;
    String engine_no,v_cc,v_color,mfgyear,route_permit_certificateno,r_e_d,taxtokenno,taxtokenno_ed,fitness,fitness_ed;
    String reg,photo,nid,owner_name,v_class,chassis_no,issuer;
    TextView vehicle_class,nidtext,chassis,issue,backbtn,license,vehicleRegNoTV,engineEt,chassisET,routePermitCertificateNoET
            ,routePermitExpiryDate,fitnessCertificateET,fitnessCertificateExpiryDate,mfgyearEt,blood_group,emergency_contact,ccET,colorEt
            ,taxTokenTV,taxtokenExpiryDate;
    ImageView imageView;
    String nidNo;
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
        setContentView(R.layout.activity_details_of_vehicles);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        engineEt=findViewById(R.id.engineEt);
        chassisET=findViewById(R.id.chassis);
        routePermitCertificateNoET=findViewById(R.id.routePermitCertificateNo);
        routePermitExpiryDate=findViewById(R.id.routePermitExpiryDate);
        fitnessCertificateET=findViewById(R.id.fitnessCertificateET);
        fitnessCertificateExpiryDate=findViewById(R.id.fitnessCertificateExpiryDate);
        mfgyearEt=findViewById(R.id.mfgyearEt);

        ccET=findViewById(R.id.ccET);
        colorEt=findViewById(R.id.colorEt);

        case_LocEt=findViewById(R.id.case_LocEt);
        case_timeEt=findViewById(R.id.case_timeEt);
        appearanceDateEt=findViewById(R.id.appearanceDateEt);
        seized_documentSp=findViewById(R.id.seized_documentSp);
        taxTokenTV=findViewById(R.id.taxTokenTV);
        taxtokenExpiryDate=findViewById(R.id.taxtokenExpiryDate);
        imageView = findViewById(R.id.imageView);
        String imageurl3 =BASE_URL+"/NID/"+photo;
        Picasso.get().load(imageurl3).into(imageView);
        nameTv= findViewById(R.id.name);
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



        v_reg=getIntent().getStringExtra("v_no");

    getDataa(v_reg);

    }


    public void getDataa(String Registration_No){
        progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"myDrivedVehicelInfo.php?Registration_No="+Registration_No;
        RequestQueue requestQueue = Volley.newRequestQueue(DetailsOfVehicles.this);
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
                       nameTv.setText(data.getString("Owner_name"));
                       vehicle_class.setText(data.getString("Vehicle_class"));
                       nidNo=data.getString("NID_No");
                       nidtext.setText(data.getString("NID_No"));
                        engineEt.setText(data.getString("Engine_No"));
                       chassis.setText(data.getString("Chassis_No"));
                       ccET.setText(data.getString("CC"));
                       colorEt.setText(data.getString("Color"));
                       routePermitCertificateNoET.setText(data.getString("routepermit_certificate_no"));
                       routePermitExpiryDate.setText(data.getString("routepermit_expiry_date"));
                       fitnessCertificateET.setText(data.getString("fitness_certificate_no"));
                       fitnessCertificateExpiryDate.setText(data.getString("fitness_certificate_expiry_date"));
                       taxTokenTV.setText(data.getString("taxtoken_no"));
                       taxtokenExpiryDate.setText(data.getString("taxperiod_expiry_date"));
                       mfgyearEt.setText(data.getString("Mfg_Year"));
                       issue.setText(data.getString("Issuing_Authority"));


                    }


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
                stringStringMap.put("Registration_No",  Registration_No);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
    public void checkhistory(View view) {
        Intent intent = new Intent(DetailsOfVehicles.this, CaseHistory2.class);
        intent.putExtra("nid",nidNo);
        intent.putExtra("vehicle_reg",v_reg);

        startActivity(intent);
    }

}