package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;
import static com.thesis.trafficinfo.SessionManager.SessionManager.ROLE;
import static com.thesis.trafficinfo.Users.AddDriverMyVehicle.PREF_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.API.Api;
import com.thesis.trafficinfo.PayViaBkash;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Case_Details extends AppCompatActivity {
    String id,role;
    TextView name,license,vehicle,crime,imprisonment,fine,sergent,batch,status,caseNoTv,seizedDocTv,lastDateofAppearanceTv;
    ImageView imageView;
    SharedPreferences sharedPreferences;
    String photo,fineS,c_status,p_id;
    TextView backbtn;
    Button payViaBkash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);
        sharedPreferences=getApplicationContext().getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        role=sharedPreferences.getString(ROLE,"nai");

        id = getIntent().getStringExtra("id");
        p_id = getIntent().getStringExtra("p_id");
   // Toast.makeText(this, ""+role, Toast.LENGTH_SHORT).show();
        name = findViewById(R.id.name);
        payViaBkash=findViewById(R.id.payViaBkah);
        license = findViewById(R.id.license);
        vehicle = findViewById(R.id.vehicle);
        caseNoTv=findViewById(R.id.caseNoTv);
        seizedDocTv=findViewById(R.id.seizedDocTv);
        lastDateofAppearanceTv=findViewById(R.id.lastDateofAppearanceTv);
        crime = findViewById(R.id.crime);
        batch = findViewById(R.id.batch);
        imageView= findViewById(R.id.imageView);
        backbtn = findViewById(R.id.backbtn);
        photo=getIntent().getStringExtra("photo");
        imprisonment = findViewById(R.id.imprisonment);
        fine = findViewById(R.id.fine);
        batch.setText(p_id);
        sergent = findViewById(R.id.sergent);

        status = findViewById(R.id.status);

        String imageurl3 =BASE_URL+"NID/"+photo;
        Picasso.get().load(imageurl3).into(imageView);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData();

    }
    public void getData(){
        id = getIntent().getStringExtra("id");
        String url =BASE_URL+ "getCaseInfo.php?id="+id;
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
                    name.setText(data.getString("Applicant_name"));
                    license.setText(data.getString("License_no"));
                    vehicle.setText(data.getString("Vehicle_number"));
                    crime.setText(data.getString("crime"));
                    imprisonment.setText(data.getString("imprisonment"));
                    fineS=data.getString("fine");
                    fine.setText("BDT "+data.getString("fine"));
                    status.setText(data.getString("Status"));
                    c_status=data.getString("Status");
                    if (c_status.contains("Paid")||role.contains("Traffic Police")){
                        payViaBkash.setVisibility(View.GONE);
                    }
                    else{
                        payViaBkash.setVisibility(View.VISIBLE);
                    }
                    seizedDocTv.setText(data.getString("seized_document"));
                    lastDateofAppearanceTv.setText(data.getString("appearance_last_date"));
                    caseNoTv.setText(data.getString("id")+ data.getString("case_date").replace("-",""));
                    data.getString("Police_id");

                    getSergent(data.getString("Police_id"));


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Case_Details.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
    public void getSergent(String id){
        String url = BASE_URL+"get_sergent_info.php?id="+id;
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
                    sergent.setText(data.getString("name"));





                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Case_Details.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }

    public void payviaBkash(View view) {
        id = getIntent().getStringExtra("id");
        Intent intent=new Intent(Case_Details.this, PayViaBkash.class);
        intent.putExtra("fine",fineS);
        intent.putExtra("id",id);
        startActivity(intent);

    }
}