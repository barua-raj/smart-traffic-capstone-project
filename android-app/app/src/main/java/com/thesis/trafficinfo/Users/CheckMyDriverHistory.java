package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.thesis.trafficinfo.TraficPolicePanel.CaseByLicense;
import com.thesis.trafficinfo.TraficPolicePanel.CaseHistory;
import com.thesis.trafficinfo.TraficPolicePanel.PoliceDashboard;
import com.thesis.trafficinfo.TraficPolicePanel.TrafficRules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckMyDriverHistory extends AppCompatActivity {
    String user_id,license1;
    ImageView imageView;
    TextView name,dob,nidtext,address,blood_group,license,issue,backbtn;
    Spinner spinner0,spinner,spinner1,spinner2,spinner3;
    EditText feedback,fine_amount,vehicle_number,userPhoneEt;
    ProgressBar progressBar,progressBar1;
    SessionManager sessionManager;
    String cat;
    LinearLayout rest;
    String u_photo;
    String authority;
    String UphoneNo;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    ArrayList<String> category0,category1,category2,category3,category;
    ArrayAdapter<String> catAdapter0,catAdapter1,catAdapter2,catAdapter3,catAdapter;
    String police_id;
    String first_fine,second_fine,fine;
    TextView law;
    private SmartMaterialSpinner<String> spinnercrime;
//cost variable , chap 6 , 6.1,6.3
AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_driver_history);
        user_id = getIntent().getStringExtra("id");
        license1 = getIntent().getStringExtra("license");
        authority = getIntent().getStringExtra("issue");
        // Toast.makeText(this, ""+license1, Toast.LENGTH_SHORT).show();
        getPhone(license1);
        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        nidtext = findViewById(R.id.nidtext);
        address = findViewById(R.id.address);
        blood_group = findViewById(R.id.blood_group);
        backbtn = findViewById(R.id.backbtn);
        license = findViewById(R.id.license);
        issue = findViewById(R.id.issue);



        progressBar = findViewById(R.id.pb);
        rest = findViewById(R.id.rest);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSergent_info();
        getUserInfo();




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
                        /*catAdapter = new ArrayAdapter<>(CaseByLicense.this, android.R.layout.simple_spinner_item, category);
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
    public void getPhone(String license){
        String url = BASE_URL+"getPhone.php?Driving_license_no=CM1234567NP";

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
                        cat = data.optString("phone");

                        //userPhoneEt.setText(cat);
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
                stringStringMap.put("Driving_license_no",  license);


                return stringStringMap;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void getFineAmount(){
        String url = BASE_URL+"getCrime_fine.php?crime_type="+category.get(spinnercrime.getSelectedItemPosition());
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
                    first_fine=data.getString("first_fine");
                    second_fine=data.getString("next_fine_s");




                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckMyDriverHistory.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

    }


    public void getUserInfo(){
        progressBar.setVisibility(View.VISIBLE);
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);
        progressBar.setVisibility(View.VISIBLE);
        String url = BASE_URL+"get_details_from_license.php?ref_no="+license1;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                rest.setVisibility(View.VISIBLE);
                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);
                   u_photo=data.getString("user_photo");
                    name.setText(data.getString("name"));
                    license.setText(license1);
                    dob.setText(data.getString("dob"));
                    nidtext.setText(data.getString("NID_No"));
                    address.setText(data.getString("address"));

                    issue.setText(authority);


                    //data.getString("photo");
                    String imageurl3 =BASE_URL+"NID/"+data.getString("user_photo");
                    Picasso.get().load(imageurl3).into(imageView);



                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    rest.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                rest.setVisibility(View.GONE);
                Toast.makeText(CheckMyDriverHistory.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }

    public void getSergent_info(){
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.EMAIL);
        progressBar.setVisibility(View.VISIBLE);
        String url =BASE_URL+ "getuserdetails.php?user_name="+username;
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

                Toast.makeText(CheckMyDriverHistory.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }


    public void checkhistory(View view) {
        Intent intent = new Intent(CheckMyDriverHistory.this, CaseHistory.class);
        intent.putExtra("nid",nidtext.getText().toString());
        intent.putExtra("user_photo",u_photo);
        startActivity(intent);



    }

    public void register(View view) {
        if (fine_amount.getText().toString().equals(null) || category.get(spinnercrime.getSelectedItemPosition()).equals(" ") || category.get(spinnercrime.getSelectedItemPosition()).equals("--Select Crime Type--")
                || category0.get(spinner0.getSelectedItemPosition()).equals("--Select User Type--") || category1.get(spinner1.getSelectedItemPosition()).equals("--Select Timeline--")
                || category2.get(spinner2.getSelectedItemPosition()).equals("--Select Vehicle Type--")
                || category3.get(spinner3.getSelectedItemPosition()).equals("--Select Fine Type--") || vehicle_number.getText().toString().equals(null)){
            Toast.makeText(this, "Fields should not be empty...", Toast.LENGTH_SHORT).show();
        }
        else{

            sendSMSMessage();
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Work In Progress...");
            progressDialog.show();
            String url = BASE_URL+"add_case.php";
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(CheckMyDriverHistory.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    progressDialog.dismiss();
                    Toast.makeText(CheckMyDriverHistory.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckMyDriverHistory.this, PoliceDashboard.class));
                    finish();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();


                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringStringMap = new HashMap<>();

                    stringStringMap.put("Applicant_name", name.getText().toString());
                    stringStringMap.put("Applicant_type", category0.get(spinner0.getSelectedItemPosition()));
                    stringStringMap.put("License_no",license.getText().toString());
                    stringStringMap.put("crime", category.get(spinnercrime.getSelectedItemPosition()));
                    stringStringMap.put("imprisonment", category1.get(spinner1.getSelectedItemPosition()));
                    stringStringMap.put("fine", fine_amount.getText().toString());
                    stringStringMap.put("fine_type",  category3.get(spinner3.getSelectedItemPosition()));
                    stringStringMap.put("Vehicle_type",  category2.get(spinner2.getSelectedItemPosition()));
                    stringStringMap.put("Vehicle_number",  vehicle_number.getText().toString());
                    stringStringMap.put("Type_of_fine",  category3.get(spinner3.getSelectedItemPosition()));
                    stringStringMap.put("NID_No",  nidtext.getText().toString());
                    stringStringMap.put("Police_id",  police_id);
                    stringStringMap.put("feedback",  feedback.getText().toString());

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }


    }


    protected void sendSMSMessage() {
        UphoneNo = userPhoneEt.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+880"+UphoneNo, null, "You got punishment for illegal traffic action", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }


    public void removeThisDriver(View view) {

        deleteDriver(user_id);
    }


    public void deleteDriver(String id){

        String url=BASE_URL+"deleteDriver.php?id="+id;
        RequestQueue requestQueue = Volley.newRequestQueue(CheckMyDriverHistory.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(CheckMyDriverHistory.this, ""+response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CheckMyDriverHistory.this,UserActivity.class));

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
                stringStringMap.put("id",  id);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
}