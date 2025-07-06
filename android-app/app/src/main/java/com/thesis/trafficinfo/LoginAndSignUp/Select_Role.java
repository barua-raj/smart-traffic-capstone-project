package com.thesis.trafficinfo.LoginAndSignUp;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Select_Role extends AppCompatActivity {
    Spinner spinner;
    EditText license,vehicle,username,password,phone,police;
    ArrayList<String> category1;
    ArrayAdapter<String> catAdapter1;
    ProgressBar progressBar;
    String name,father,mother,nid,dob,address,blood_group,issue,place_of_birth,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
        spinner = findViewById(R.id.spinner);
        license = findViewById(R.id.license);
        vehicle = findViewById(R.id.vehicle);
        progressBar = findViewById(R.id.pb);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        police = findViewById(R.id.police);

        //getting data from other activity
        name = getIntent().getStringExtra("name");
        father = getIntent().getStringExtra("father");
        mother = getIntent().getStringExtra("mother");
        nid = getIntent().getStringExtra("nidtext");
        address = getIntent().getStringExtra("address");
        blood_group = getIntent().getStringExtra("blood_group");
        issue = getIntent().getStringExtra("issue");
        place_of_birth = getIntent().getStringExtra("place_of_birth");
        photo = getIntent().getStringExtra("photo");
        dob = getIntent().getStringExtra("dob");
        //getting data from other activity


        category1 = new ArrayList<>();
        category1.add("--Select User Type--");
        category1.add("Driver");
        category1.add("Owner");
        category1.add("Both");
        category1.add("Traffic Police");
        catAdapter1 = new ArrayAdapter<>(Select_Role.this, android.R.layout.simple_spinner_item,category1);
        catAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){

                }
                else if (i==1){
                    license.setVisibility(View.VISIBLE);
                    vehicle.setVisibility(View.GONE);
                    police.setVisibility(View.GONE);
                }
                else if (i==2){
                    license.setVisibility(View.GONE);
                    vehicle.setVisibility(View.VISIBLE);
                    police.setVisibility(View.GONE);
                }
                else if (i==3){
                    license.setVisibility(View.VISIBLE);
                    vehicle.setVisibility(View.VISIBLE);
                    police.setVisibility(View.GONE);
                }
                else if (i==4){
                    license.setVisibility(View.GONE);
                    vehicle.setVisibility(View.GONE);
                    police.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void submit(View view) {

        if (username.getText().toString().equals(null) || password.getText().toString().equals(null) || phone.getText().toString().equals(null)){
            Toast.makeText(this, "Credentials Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (username.getText().toString().length()<8 || password.getText().toString().length()<15 || phone.getText().toString().length()<11){
            Toast.makeText(this, "Credentials Must Have Proper Length", Toast.LENGTH_SHORT).show();
        }
        else{
            isUserNameExist();
        }
        

    }

    public void isUserNameExist(){
        //isUserNameExist.php
        String url = BASE_URL+"isUserNameExist.php?user_name="+username.getText().toString();
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
                    Toast.makeText(Select_Role.this, "User name is taken by someone else", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    if (category1.get(spinner.getSelectedItemPosition()).equals("Driver")){
                        getLicenseInfo();

                    }
                    else if(category1.get(spinner.getSelectedItemPosition()).equals("Owner")){
                        getVehicleLicenseInfo();
                    }
                    else if(category1.get(spinner.getSelectedItemPosition()).equals("Both")){
                        getBoth();
                    }
                    else if(category1.get(spinner.getSelectedItemPosition()).equals("Traffic Police")){
                        getPoliceInfo();
                    }
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (category1.get(spinner.getSelectedItemPosition()).equals("Driver")){
                    getLicenseInfo();
                    Toast.makeText(Select_Role.this, "1", Toast.LENGTH_SHORT).show();

                }
                else if(category1.get(spinner.getSelectedItemPosition()).equals("Owner")){
                    getVehicleLicenseInfo();

                    Toast.makeText(Select_Role.this, "2", Toast.LENGTH_SHORT).show();
                }
                else if(category1.get(spinner.getSelectedItemPosition()).equals("Both")){
                    getBoth();
                    Toast.makeText(Select_Role.this, "3", Toast.LENGTH_SHORT).show();
                }
                else if(category1.get(spinner.getSelectedItemPosition()).equals("Traffic Police")){
                    getPoliceInfo();
                }


            }
        });

        requestQueue.add(stringRequest);
    }
    public void getLicenseInfo()
    {
        //getValid_license_info.php

        String url = BASE_URL+"getValid_license_info.php?ref_no="+license.getText().toString();
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
                    addDriver();

                } catch (JSONException e) {
                    Toast.makeText(Select_Role.this, "Invalid License", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Select_Role.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();



            }
        });

        requestQueue.add(stringRequest);
    }
    public void getPoliceInfo()
    {
        //getValid_license_info.php

        String url = BASE_URL+"getPoliceUniqueId.php?police_unique_id="+police.getText().toString();
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
                    addpolice();

                } catch (JSONException e) {
                    Toast.makeText(Select_Role.this, "Invalid Police Id", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Select_Role.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();



            }
        });

        requestQueue.add(stringRequest);
    }
    public void getVehicleLicenseInfo()
    {
        //getValid_license_info.php

        String url = BASE_URL+"getVehicle_license_info.php?Registration_No="+vehicle.getText().toString();
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
                    addOwner();

                } catch (JSONException e) {
                    Toast.makeText(Select_Role.this, "Invalid License", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Select_Role.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();



            }
        });

        requestQueue.add(stringRequest);
    }
    public void getBoth()
    {
        //getValid_license_info.php

        String url = BASE_URL+"getVehicle_license_info.php?Registration_No="+vehicle.getText().toString();
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
                    addBoth();

                } catch (JSONException e) {
                    Toast.makeText(Select_Role.this, "Invalid License", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Select_Role.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();



            }
        });

        requestQueue.add(stringRequest);
    }

    public void addDriver(){
       if (license.getText().toString().equals(null)){
           Toast.makeText(this, "Add Driving License Ref. Number", Toast.LENGTH_SHORT).show();
       }
       else{
           ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Work In Progress...");
           progressDialog.show();
           String url = BASE_URL+"sign_up.php";
           Log.d("TAG", "url: "+url);
           RequestQueue requestQueue = Volley.newRequestQueue(Select_Role.this);
           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   progressDialog.dismiss();
                   Toast.makeText(Select_Role.this, "Updated", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(Select_Role.this,LoginActivity.class));
                   finish();


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   progressDialog.dismiss();

                   Toast.makeText(Select_Role.this, "1", Toast.LENGTH_SHORT).show();
                   Log.d("TAG", "onErrorResponse: "+category1.get(spinner.getSelectedItemPosition()));
                   Log.d("TAG", "onErrorResponse: "+name);
                   Log.d("TAG", "onErrorResponse: "+dob);
                   Log.d("TAG", "onErrorResponse: "+blood_group);
                   Log.d("TAG", "onErrorResponse: "+license.getText().toString());
                   Log.d("TAG", "onErrorResponse: "+username.getText().toString());
                   Log.d("TAG", "onErrorResponse: "+password.getText().toString());
                   Log.d("TAG", "onErrorResponse: "+photo);
                   Log.d("TAG", "onErrorResponse: "+nid);
                   Log.d("TAG", "onErrorResponse: "+phone.getText().toString());
                   Log.d("TAG", "onErrorResponse: "+address);

               }
           }){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> stringStringMap = new HashMap<>();

                   stringStringMap.put("Role", category1.get(spinner.getSelectedItemPosition()));
                   stringStringMap.put("name", name);
                   stringStringMap.put("date_of_birth",dob);
                   stringStringMap.put("Blood_group", blood_group);
                   stringStringMap.put("Driving_license_no", license.getText().toString());
                   stringStringMap.put("Vehicle_reg_no", "Not Applicable");
                   stringStringMap.put("user_name",  username.getText().toString());
                   stringStringMap.put("password",  password.getText().toString());
                   stringStringMap.put("photo_name",  photo);
                   stringStringMap.put("NID_No",  nid);
                   stringStringMap.put("phone",  phone.getText().toString());
                   stringStringMap.put("division",  address);
                   stringStringMap.put("Police_id",  "Not Applicable");

                   return stringStringMap;
               }
           };


           requestQueue.add(stringRequest);
       }
    }

    public void addOwner(){
       if (vehicle.getText().toString().equals(null)){
           Toast.makeText(this, "Enter your Vehicle registration Number", Toast.LENGTH_SHORT).show();
       }
       else{
           ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Work In Progress...");
           progressDialog.show();
           String url = BASE_URL+"sign_up.php";
           Log.d("TAG", "url: "+url);
           RequestQueue requestQueue = Volley.newRequestQueue(Select_Role.this);
           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   progressDialog.dismiss();
                   Toast.makeText(Select_Role.this, "Updated", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(Select_Role.this,LoginActivity.class));
                   finish();

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   progressDialog.dismiss();

                   Toast.makeText(Select_Role.this, "2", Toast.LENGTH_SHORT).show();

               }
           }){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> stringStringMap = new HashMap<>();

                   stringStringMap.put("Role", category1.get(spinner.getSelectedItemPosition()));
                   stringStringMap.put("name", name);
                   stringStringMap.put("date_of_birth",dob);
                   stringStringMap.put("Blood_group", blood_group);
                   stringStringMap.put("Driving_license_no", "Not Applicable");
                   stringStringMap.put("Vehicle_reg_no", vehicle.getText().toString());
                   stringStringMap.put("user_name",  username.getText().toString());
                   stringStringMap.put("password",  password.getText().toString());
                   stringStringMap.put("photo_name",  photo);
                   stringStringMap.put("NID_No",  nid);
                   stringStringMap.put("phone",  phone.getText().toString());
                   stringStringMap.put("division",  address);
                   stringStringMap.put("Police_id",  "Not Applicable");

                   return stringStringMap;
               }
           };


           requestQueue.add(stringRequest);
       }
    }

    public void addBoth(){
        if (vehicle.getText().toString().equals(null)){
            Toast.makeText(this, "Enter your Vehicle registration Number", Toast.LENGTH_SHORT).show();
        }
        else{
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Work In Progress...");
            progressDialog.show();
            String url = BASE_URL+"sign_up.php";
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(Select_Role.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Toast.makeText(Select_Role.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Select_Role.this,LoginActivity.class));
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                    Toast.makeText(Select_Role.this, "2", Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringStringMap = new HashMap<>();

                    stringStringMap.put("Role", category1.get(spinner.getSelectedItemPosition()));
                    stringStringMap.put("name", name);
                    stringStringMap.put("date_of_birth",dob);
                    stringStringMap.put("Blood_group", blood_group);
                    stringStringMap.put("Driving_license_no", license.getText().toString());
                    stringStringMap.put("Vehicle_reg_no", vehicle.getText().toString());
                    stringStringMap.put("user_name",  username.getText().toString());
                    stringStringMap.put("password",  password.getText().toString());
                    stringStringMap.put("photo_name",  photo);
                    stringStringMap.put("NID_No",  nid);
                    stringStringMap.put("phone",  phone.getText().toString());
                    stringStringMap.put("division",  address);
                    stringStringMap.put("Police_id",  "Not Applicable");

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }
    }

    public void addAll(){
       if(license.getText().toString().equals(null) || vehicle.getText().toString().equals(null)){
           Toast.makeText(this, "Enter Required Fields License number and Vehicle registration Number. Police id is optional", Toast.LENGTH_SHORT).show();
       }
       else{
           ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Work In Progress...");
           progressDialog.show();
           String url = BASE_URL+"sign_up.php";
           Log.d("TAG", "url: "+url);
           RequestQueue requestQueue = Volley.newRequestQueue(Select_Role.this);
           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   progressDialog.dismiss();
                   Toast.makeText(Select_Role.this, "Updated", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(Select_Role.this,LoginActivity.class));
                   finish();

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   progressDialog.dismiss();

                   Toast.makeText(Select_Role.this, "3", Toast.LENGTH_SHORT).show();

               }
           }){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> stringStringMap = new HashMap<>();

                   stringStringMap.put("Role", category1.get(spinner.getSelectedItemPosition()));
                   stringStringMap.put("name", name);
                   stringStringMap.put("date_of_birth",dob);
                   stringStringMap.put("Blood_group", blood_group);
                   stringStringMap.put("Driving_license_no", license.getText().toString());
                   stringStringMap.put("Vehicle_reg_no", vehicle.getText().toString());
                   stringStringMap.put("user_name",  username.getText().toString());
                   stringStringMap.put("password",  password.getText().toString());
                   stringStringMap.put("photo_name",  photo);
                   stringStringMap.put("NID_No",  nid);
                   stringStringMap.put("phone",  phone.getText().toString());
                   stringStringMap.put("division",  address);
                   stringStringMap.put("Police_id",  police.getText().toString());

                   return stringStringMap;
               }
           };


           requestQueue.add(stringRequest);
       }
    }
    public void addpolice(){
        if (police.getText().toString().equals(null)){
            Toast.makeText(this, "Enter Police Id", Toast.LENGTH_SHORT).show();
        }
        else{
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Work In Progress...");
            progressDialog.show();
            String url = BASE_URL+"sign_up.php";
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(Select_Role.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Toast.makeText(Select_Role.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Select_Role.this,LoginActivity.class));
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                    Toast.makeText(Select_Role.this, "3", Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringStringMap = new HashMap<>();

                    stringStringMap.put("Role", category1.get(spinner.getSelectedItemPosition()));
                    stringStringMap.put("name", name);
                    stringStringMap.put("date_of_birth",dob);
                    stringStringMap.put("Blood_group", blood_group);
                    stringStringMap.put("Driving_license_no", "Not Applicable");
                    stringStringMap.put("Vehicle_reg_no", "Not Applicable");
                    stringStringMap.put("user_name",  username.getText().toString());
                    stringStringMap.put("password",  password.getText().toString());
                    stringStringMap.put("photo_name",  photo);
                    stringStringMap.put("NID_No",  nid);
                    stringStringMap.put("phone",  phone.getText().toString());
                    stringStringMap.put("division",  address);
                    stringStringMap.put("Police_id",  police.getText().toString());

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }
    }
}