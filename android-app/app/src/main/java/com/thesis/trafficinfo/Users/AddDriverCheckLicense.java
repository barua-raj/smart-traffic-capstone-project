package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.LoginAndSignUp.NID;
import com.thesis.trafficinfo.LoginAndSignUp.personalInfo;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddDriverCheckLicense extends AppCompatActivity {
    EditText nid;
    TextView backbtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver_check_license);
        backbtn = findViewById(R.id.backbtn);
        nid = findViewById(R.id.nid);
        progressBar = findViewById(R.id.pb);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getInfo(){
        progressBar.setVisibility(View.VISIBLE);
        String url = BASE_URL+"get_details.php?NID_No="+nid.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Log.d("Res=", url);
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);
                    Intent intent = new Intent(AddDriverCheckLicense.this, CheckDriverInfo.class);
                    intent.putExtra("nid", nid.getText().toString());
                    startActivity(intent);




                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AddDriverCheckLicense.this, "NID Exception!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddDriverCheckLicense.this, "NID Not Found!!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

    }
    public void next(View view) {
        getInfo();

    }
}