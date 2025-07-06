package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.LoginAndSignUp.Select_Role;
import com.thesis.trafficinfo.LoginAndSignUp.personalInfo;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CheckDriverInfo extends AppCompatActivity {
    String nid,photo;
    TextView name,father,mother,dob,nidtext,address,blood_group,issue,place_of_birth;
    ImageView imageView;
    TextView backbtn;
    LinearLayout rest;
    ProgressBar pb;
    ArrayList<String> category1;
    ArrayAdapter<String> catAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_driver_info);
        nid = getIntent().getStringExtra("nid");
        name = findViewById(R.id.name);
        father = findViewById(R.id.father);
        mother = findViewById(R.id.mother);
        backbtn = findViewById(R.id.backbtn);
        dob = findViewById(R.id.dob);
        nidtext = findViewById(R.id.nidtext);
        address = findViewById(R.id.address);
        blood_group = findViewById(R.id.blood_group);
        issue = findViewById(R.id.issue);
        place_of_birth = findViewById(R.id.place_of_birth);
        imageView= findViewById(R.id.imageView);
        rest= findViewById(R.id.rest);
        pb= findViewById(R.id.pb);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getInfo();



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
                    place_of_birth.setText(data.getString("Place_of_Birth"));
                    photo =data.getString("photo");
                    //data.getString("photo");
                    String imageurl3 =BASE_URL+"NID/"+data.getString("photo");
                    Picasso.get().load(imageurl3).into(imageView);



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
                Toast.makeText(CheckDriverInfo.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

    }

    public void next(View view) {
        Intent intent = new Intent(CheckDriverInfo.this, AddDriver.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("father", father.getText().toString());
        intent.putExtra("mother", mother.getText().toString());
        intent.putExtra("dob", dob.getText().toString());
        intent.putExtra("nidtext", nidtext.getText().toString());
        intent.putExtra("address", address.getText().toString());
        intent.putExtra("blood_group", blood_group.getText().toString());
        intent.putExtra("issue", issue.getText().toString());
        intent.putExtra("place_of_birth", place_of_birth.getText().toString());
        intent.putExtra("photo", photo);
        startActivity(intent);

    }
}