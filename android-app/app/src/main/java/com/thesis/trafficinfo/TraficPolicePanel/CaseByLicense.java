package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.LoginAndSignUp.Select_Role;
import com.thesis.trafficinfo.LoginAndSignUp.personalInfo;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CaseByLicense extends AppCompatActivity {
    String user_id,license1,ref_expiry_date,names;
    final Calendar myCalendar= Calendar.getInstance();
    ImageView imageView;
    TextView name,dob,nidtext,address,blood_group,license,issue,backbtn,vehicleRegNoTV,ref_expiry_dateTv,emergency_contactET;
    Spinner spinner0,spinner,spinner1,spinner2,spinner3,addressSpinner,seized_documentSp;
    EditText feedback,fine_amount,vehicle_number,userPhoneEt,applicantTypeET,case_timeEt,case_LocEt,appearanceDateEt;
    ProgressBar progressBar,progressBar1;

    SessionManager sessionManager;
    String cat;
    LinearLayout rest;
    String authority;
    String formattedDate;
    LinearLayout licenseLinearLayout,vehiclLinearLayout;

    String user_photo,NID_No;
    String UphoneNo;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    ArrayList<String> category0,category1,category2,category3,category,divArray,seizSpinnerArray;
    ArrayList<String> Barishal,Chattogram,Dhaka,Khulna,Rajshahi,Rangpur,Mymensingh,Sylhet;
    ArrayAdapter<String> catAdapter0,catAdapter1,catAdapter2,catAdapter3,catAdapter,divAdapter,seizSpinnerAdapter;
    String police_id;
    String first_fine,second_fine,fine;
    String  username;
    TextView law;
    private SmartMaterialSpinner<String> spinnercrime;
//cost variable , chap 6 , 6.1,6.3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_by_license);

        user_id = getIntent().getStringExtra("id");
        license1 = getIntent().getStringExtra("license");
        authority = getIntent().getStringExtra("issue");
        NID_No = getIntent().getStringExtra("NID_No");
        ref_expiry_date = getIntent().getStringExtra("ref_expiry_date");

        getPhone(license1);
        imageView = findViewById(R.id.imageView);
        case_LocEt=findViewById(R.id.case_LocEt);
        case_timeEt=findViewById(R.id.case_timeEt);
        appearanceDateEt=findViewById(R.id.appearanceDateEt);
        case_timeEt.setClickable(true);
        case_timeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showTimePicker();
            }
        });
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        appearanceDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CaseByLicense.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        emergency_contactET=findViewById(R.id.emergency_contact);


        seized_documentSp=findViewById(R.id.seized_documentSp);
        ref_expiry_dateTv=findViewById(R.id.ref_expiry_dateTv);
        vehicle_number = findViewById(R.id.vehicle_number);
        emergency_contactET=findViewById(R.id.emergency_contact);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        nidtext = findViewById(R.id.nidtext);
        address = findViewById(R.id.address);
        licenseLinearLayout=findViewById(R.id.licenseLinearLayout);
        vehiclLinearLayout=findViewById(R.id.vehiclLinearLayout);
        applicantTypeET=findViewById(R.id.applicantTypeET);
        blood_group = findViewById(R.id.blood_group);
        vehicleRegNoTV=findViewById(R.id.vehicleRegNoTV);
        backbtn = findViewById(R.id.backbtn);
        addressSpinner=findViewById(R.id.addressSpinner);
        license = findViewById(R.id.license);
        issue = findViewById(R.id.issue);
        spinner = findViewById(R.id.spinner);
        ref_expiry_dateTv.setText(ref_expiry_date);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        feedback = findViewById(R.id.feedback);
        spinnercrime = findViewById(R.id.spinnercrime);
        userPhoneEt=findViewById(R.id.UserPhoneET);
        law = findViewById(R.id.law);
        progressBar = findViewById(R.id.pb);
        progressBar1 = findViewById(R.id.pb1);
        rest = findViewById(R.id.rest);
        fine_amount = findViewById(R.id.fine_amount);
        getApplicantType();
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CaseByLicense.this,TrafficRules.class));
            }
        });
        getSergent_info();
        getUserInfo();
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat(("yyyy-MM-dd"), Locale.getDefault());
         formattedDate = df.format(c);
        Toast.makeText(this, ""+formattedDate, Toast.LENGTH_SHORT).show();


        //select applicant type
        category0 = new ArrayList<>();
        category0.add("--Select User Type--");
        category0.add("Driver");
        category0.add("Owner");
        category0.add("Both");

        divArray=new ArrayList<>();
        divArray.add("Barishal");
        divArray.add("Chattogram");
        divArray.add("Dhaka");
        divArray.add("Khulna");
        divArray.add("Rajshahi");
        divArray.add("Rangpur");
        divArray.add("Mymensingh");
        divArray.add("Sylhet");



        divAdapter = new ArrayAdapter<>(CaseByLicense.this, android.R.layout.simple_spinner_item,divArray);
        divAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(divAdapter);

        //select applicant type


        //select imprisonment
        category1 = new ArrayList<>();
        category1.add("--Select Timeline--");
        category1.add("15 Days");
        category1.add("1 Month");
        category1.add("3 Months");
        category1.add("6 Months");
        category1.add("None");
        catAdapter1 = new ArrayAdapter<>(CaseByLicense.this, android.R.layout.simple_spinner_item,category1);
        catAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(catAdapter1);
        //select imprisonment
        seizSpinnerArray=new ArrayList<>();
        seizSpinnerArray.add("None");
        seizSpinnerArray.add("License");
        seizSpinnerArray.add("Route Permit Certificate");
        seizSpinnerArray.add("Fitness Certificate");
        seizSpinnerArray.add("Certificate of Registration");
        seizSpinnerAdapter=new ArrayAdapter<>(CaseByLicense.this, android.R.layout.simple_spinner_item,seizSpinnerArray);
        seizSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seized_documentSp.setAdapter(seizSpinnerAdapter);

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
        catAdapter2 = new ArrayAdapter<>(CaseByLicense.this, android.R.layout.simple_spinner_item,category2);
        catAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(catAdapter2);

        //Vehicle Class type


        // FIne type
        category3 = new ArrayList<>();
        category3.add("--Select Fine Type--");
        category3.add("First Timer");
        category3.add("Second Timer");
        category3.add("Other");
        catAdapter3 = new ArrayAdapter<>(CaseByLicense.this, android.R.layout.simple_spinner_item,category3);
        catAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(catAdapter3);


        //Fine Type

        //get crime category
        category = new ArrayList<>();
        category.add("--Select Crime Type--");
        getCrimeCategory();
        //get crime category

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getFineAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnercrime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getFineAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(category3.get(spinner3.getSelectedItemPosition()).equals("First Timer")){
                    fine = first_fine;
                    fine_amount.setText(fine);
                }
                else{
                    fine = second_fine;
                    fine_amount.setText(fine);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        seized_documentSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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
        String url = BASE_URL+"getPhone.php?Driving_license_no="+license;

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
                         blood_group.setText(data.optString("Blood_group"));
                           username=data.optString("user_name");

                           getEmergencyContact(username);
                        userPhoneEt.setText(cat);
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
                        emergency_contactET.setText(data.optString("number"));

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
    public void getApplicantType(){


    //    HashMap<String, String> user = sessionManager.getUserDetail();
     //   String username = user.get(sessionManager.EMAIL);

        String url = BASE_URL+"getApplicantType.php?NID_No="+NID_No;
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
                    applicantTypeET.setText(data.getString("Role"));
                    String Role= data.getString("Role");
                    if (Role.contains("Driver")){
                        licenseLinearLayout.setVisibility(View.VISIBLE);
                        license.setText(data.getString("Driving_license_no"));

                    }
                    else if(Role.contains("Owner")){
                        vehiclLinearLayout.setVisibility(View.VISIBLE);
                        vehicleRegNoTV.setText(data.getString("Vehicle_reg_no"));

                    }
                    else if(Role.contains("Both")){
                        licenseLinearLayout.setVisibility(View.VISIBLE);
                        vehiclLinearLayout.setVisibility(View.VISIBLE);
                        license.setText(data.getString("Driving_license_no"));
                        vehicleRegNoTV.setText(data.getString("Vehicle_reg_no"));

                    }




                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CaseByLicense.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

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

                Toast.makeText(CaseByLicense.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(CaseByLicense.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

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
                        police_id = data.getString("Police_id");




                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(CaseByLicense.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });

            requestQueue.add(stringRequest);
        }


    public void checkhistory(View view) {
        Intent intent = new Intent(CaseByLicense.this,CaseHistory.class);
        intent.putExtra("nid",nidtext.getText().toString());
        intent.putExtra("user_photo",user_photo);
        startActivity(intent);



    }

    public void register(View view) {
        if (fine_amount.getText().toString().equals(null) || category.get(spinnercrime.getSelectedItemPosition()).equals(" ") || category.get(spinnercrime.getSelectedItemPosition()).equals("--Select Crime Type--")
        || category1.get(spinner1.getSelectedItemPosition()).equals("--Select Timeline--")
                || category2.get(spinner2.getSelectedItemPosition()).equals("--Select Vehicle Type--")
                || category3.get(spinner3.getSelectedItemPosition()).equals("--Select Fine Type--") || vehicle_number.getText().toString().equals(null)){
            Toast.makeText(this, "Fields should not be empty...", Toast.LENGTH_SHORT).show();
        }
        else{


            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Work In Progress...");
            progressDialog.show();
            String url = BASE_URL+"add_case.php";
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(CaseByLicense.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    UphoneNo = userPhoneEt.getText().toString();
                    String messageText = "A case has been filed against you for traffic violation. Check your Smart Traffic profile for more details.";
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage("+88"+UphoneNo, null, messageText, null, null);
                        progressDialog.dismiss();
                        Toast.makeText(CaseByLicense.this, "Updated", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(CaseByLicense.this, PoliceDashboard.class));
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
                    stringStringMap.put("Applicant_type", applicantTypeET.getText().toString());
                    stringStringMap.put("License_no",license.getText().toString());
                    stringStringMap.put("crime", category.get(spinnercrime.getSelectedItemPosition()));
                    stringStringMap.put("imprisonment", category1.get(spinner1.getSelectedItemPosition()));
                    stringStringMap.put("fine", fine_amount.getText().toString());
                    stringStringMap.put("fine_type",  category3.get(spinner3.getSelectedItemPosition()));
                    stringStringMap.put("Vehicle_type",  category2.get(spinner2.getSelectedItemPosition()));
                    stringStringMap.put("address",  divArray.get(addressSpinner.getSelectedItemPosition()));
                    stringStringMap.put("Vehicle_number",  vehicle_number.getText().toString());
                    stringStringMap.put("Type_of_fine",  category3.get(spinner3.getSelectedItemPosition()));
                    stringStringMap.put("NID_No",  nidtext.getText().toString());
                    stringStringMap.put("Police_id",  police_id);
                    stringStringMap.put("feedback",  feedback.getText().toString());
                    stringStringMap.put("case_time",  case_timeEt.getText().toString());
                    stringStringMap.put("case_location",  case_LocEt.getText().toString());
                    stringStringMap.put("case_date",  formattedDate);
                    stringStringMap.put("seized_document", seizSpinnerArray.get(seized_documentSp.getSelectedItemPosition()));
                    stringStringMap.put("appearance_last_date", appearanceDateEt.getText().toString());

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }


    }


    protected void sendSMSMessage() {
         UphoneNo = userPhoneEt.getText().toString();

        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("+8801812972570"));
        smsIntent.putExtra("sms_body"  , "Test ");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CaseByLicense.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
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
                    smsManager.sendTextMessage("+8801812972570", null, "You got punishment for illegal traffic action", null, null);
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

    private void showTimePicker(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(CaseByLicense.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                case_timeEt.setText(selectedHour+":"+selectedMinute);

            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        appearanceDateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

}