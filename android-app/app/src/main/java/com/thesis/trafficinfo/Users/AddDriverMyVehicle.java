package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;
import static com.thesis.trafficinfo.SessionManager.SessionManager.ROLE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.thesis.trafficinfo.BothActivity;
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

public class AddDriverMyVehicle extends AppCompatActivity {
    String user_id,license1;
    ImageView imageView;
    TextView name,dob,nidtext,address,blood_group,license,issue,backbtn;

    EditText feedback,locationEt,userPhoneEt,phoneET;
    ProgressBar progressBar,progressBar1;
    Spinner vehicle_number;
    SessionManager sessionManager;
    String cat;
    String user_photo;
    private ArrayList<String> Vehicles;
    LinearLayout rest;
    String authority;
    String UphoneNo;
    String myVehicle;
    private  JSONArray result;
    SharedPreferences sharedPreferences;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    ArrayList<String> category0,category1,category2,category3,category;
    ArrayAdapter<String> catAdapter0,catAdapter1,catAdapter2,catAdapter3,catAdapter;
    String police_id;
    String first_fine,second_fine,fine;
    TextView law;
    int PRIVATE_MODE =0;
    public static final String PREF_NAME ="LOGIN";
    private SmartMaterialSpinner<String> spinnercrime;
    //cost variable , chap 6 , 6.1,6.3
    String role,d_license,d_nid;
    public static final String EMAIL = "EMAIL";
    EditText imageET;
    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver_my_vehicle);
        sharedPreferences=getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        role=sharedPreferences.getString(ROLE,null);
      //  Toast.makeText(this, ""+role, Toast.LENGTH_SHORT).show();
        d_license=sharedPreferences.getString("d_license",null);
        d_nid=sharedPreferences.getString("d_nid",null);

        Vehicles=new ArrayList<>();
        user_name=sharedPreferences.getString(EMAIL,null);
        user_id = getIntent().getStringExtra("id");
        license1 = getIntent().getStringExtra("license");
        authority = getIntent().getStringExtra("issue");

        getPhone(license1);
        imageView = findViewById(R.id.imageView);

        vehicle_number = findViewById(R.id.vehicle_numberSp);
        vehicle_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                myVehicle=vehicle_number.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        nidtext = findViewById(R.id.nidtext);
        address = findViewById(R.id.address);
        blood_group = findViewById(R.id.blood_group);
        backbtn = findViewById(R.id.backbtn);
        license = findViewById(R.id.license);
        issue = findViewById(R.id.issue);

        progressBar = findViewById(R.id.pb);
        phoneET=findViewById(R.id.phoneET);
        rest = findViewById(R.id.rest);
        imageET=findViewById(R.id.imageET);
        locationEt = findViewById(R.id.locaationET);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSergent_info();
        getUserInfo();


        //select applicant type
        category0 = new ArrayList<>();
        category0.add("--Select User Type--");
        category0.add("Driver");
        category0.add("Owner");
        category0.add("Both");

        catAdapter0 = new ArrayAdapter<>(AddDriverMyVehicle.this, android.R.layout.simple_spinner_item,category0);


        //select applicant type


        //select imprisonment
        category1 = new ArrayList<>();
        category1.add("--Select Timeline--");
        category1.add("15 Days");
        category1.add("1 Month");
        category1.add("3 Months");
        category1.add("6 Months");
        category1.add("None");
        catAdapter1 = new ArrayAdapter<>(AddDriverMyVehicle.this, android.R.layout.simple_spinner_item,category1);
        catAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         //select imprisonment

        //Vehicle Class type

        category2 = new ArrayList<>();
        category2.add("--Select Vehicle Type--");
        category2.add("Motorcycle");
        category2.add("Motorcar");
        category2.add("SUV");
        category2.add("Microbus");
        category2.add("Taxicab");
        category2.add("Ambulance");
        category2.add("Minibus");
        category2.add("School bus/College bus");
        category2.add("Auto rickshaws (Baby taxi, CNG)");
        category2.add("Auto tempo");
        category2.add("Delivery van/Mini-truck");
        category2.add("Cargo truck");
        category2.add("Private articulated vehicle");
        category2.add("Oil tanker/Water tanker");
        category2.add("Agricultural vehicle (power tiller, tractor)");
        category2.add("Prime Minister's office (any vehicle)");
        category2.add("President's office (any vehicle)");
        category2.add("Other");
        catAdapter2 = new ArrayAdapter<>(AddDriverMyVehicle.this, android.R.layout.simple_spinner_item,category2);
        catAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Vehicle Class type


        // FIne type
        category3 = new ArrayList<>();
        category3.add("--Select Fine Type--");
        category3.add("First Timer");
        category3.add("Second Timer");
        category3.add("Other");
        catAdapter3 = new ArrayAdapter<>(AddDriverMyVehicle.this, android.R.layout.simple_spinner_item,category3);
        catAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Fine Type

        //get crime category
        category = new ArrayList<>();
        category.add("--Select Crime Type--");
        getCrimeCategory();
        //get crime category

        getData(d_nid);


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
                    //    category.add(cat);
                    //    spinnercrime.setItem(category);
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

                       // userPhoneEt.setText(cat);
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

                Toast.makeText(AddDriverMyVehicle.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

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
                    name.setText(data.getString("name"));
                    license.setText(license1);
                    dob.setText(data.getString("dob"));
                    nidtext.setText(data.getString("NID_No"));
                    address.setText(data.getString("address"));
                    imageET.setText(data.getString("user_photo"));

                    issue.setText(authority);
                    user_photo=data.getString("user_photo");


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
                Toast.makeText(AddDriverMyVehicle.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

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

                Toast.makeText(AddDriverMyVehicle.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }


    public void checkhistory(View view) {
        Intent intent = new Intent(AddDriverMyVehicle.this, CaseHistory.class);
        intent.putExtra("nid",nidtext.getText().toString());
        intent.putExtra("user_photo",user_photo);
        startActivity(intent);



    }
    private void getData(String  owner_name){
        String  url=BASE_URL+"getmyvehicle.php?NID_No="+owner_name;
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(AddDriverMyVehicle.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject   j = new JSONObject(response);
                            result = j.getJSONArray("Result");
                            getVehicles(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getVehicles(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                Vehicles.add(json.getString("Registration_No"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        vehicle_number.setAdapter(new ArrayAdapter<String>(AddDriverMyVehicle.this, android.R.layout.simple_spinner_dropdown_item, Vehicles));
    }
    public void register(View view) {
        String dLicense=license1;
        if (role.equals("both")&& license1.equals(d_license)){
            addOwnDriver();
        }

        else{
            mySelfDriver();

        }


    }


    public void addOwnDriver(){
        if (locationEt.getText().toString().equals(null)
        ){
            Toast.makeText(this, "Fields should not be empty...", Toast.LENGTH_SHORT).show();
        }
        else{

            //
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Work In Progress...");
            progressDialog.show();
            String url = BASE_URL+"addmydriverinvehicle.php";
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(AddDriverMyVehicle.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    progressDialog.dismiss();
                    Toast.makeText(AddDriverMyVehicle.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDriverMyVehicle.this, BothActivity.class));
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

                    stringStringMap.put("name", name.getText().toString());
                    stringStringMap.put("Driving_license_no",license.getText().toString());
                    stringStringMap.put("Vehicle_reg_no", myVehicle);
                    stringStringMap.put("user_name", user_name);
                    stringStringMap.put("location",  locationEt.getText().toString());
                    stringStringMap.put("phone",  phoneET.getText().toString());
                    stringStringMap.put("image",  imageET.getText().toString());

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }

    }
    public void mySelfDriver(){
        if (locationEt.getText().toString().equals(null)
        ){
            Toast.makeText(this, "Fields should not be empty...", Toast.LENGTH_SHORT).show();
        }
        else{

            //
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Work In Progress...");
            progressDialog.show();
            String url = BASE_URL+"addmydriverinmyvehicle.php";
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(AddDriverMyVehicle.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    progressDialog.dismiss();
                    Toast.makeText(AddDriverMyVehicle.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDriverMyVehicle.this, UserActivity.class));
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

                    stringStringMap.put("name", name.getText().toString());
                    stringStringMap.put("Driving_license_no",license.getText().toString());
                    stringStringMap.put("Vehicle_reg_no", myVehicle);
                    stringStringMap.put("user_name", user_name);
                    stringStringMap.put("location",  locationEt.getText().toString());
                    stringStringMap.put("image",  imageET.getText().toString());
                    stringStringMap.put("phone",  phoneET.getText().toString());

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }

    }


    protected void sendSMSMessage() {
      //  UphoneNo = userPhoneEt.getText().toString();

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


}