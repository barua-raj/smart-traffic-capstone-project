
package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.LoginAndSignUp.Select_Role;
import com.thesis.trafficinfo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDriver extends AppCompatActivity {
    EditText license,vehicle,username,password,phone,police;
    ArrayList<String> category1;
    ArrayAdapter<String> catAdapter1;
    ProgressBar progressBar;
    String name,father,mother,nid,dob,address,blood_group,issue,place_of_birth,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        license = findViewById(R.id.license);
        vehicle = findViewById(R.id.vehicle);
        progressBar = findViewById(R.id.pb);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);

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
    }

    public void submit(View view) {
        if (username.getText().toString().equals(null) || password.getText().toString().equals(null) || phone.getText().toString().equals(null)){
            Toast.makeText(this, "Credentials Cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            addDriver();
        }
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
            RequestQueue requestQueue = Volley.newRequestQueue(AddDriver.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Toast.makeText(AddDriver.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDriver.this, UserActivity.class));
                    finish();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                    Toast.makeText(AddDriver.this, "1", Toast.LENGTH_SHORT).show();


                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringStringMap = new HashMap<>();

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
}