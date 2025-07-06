package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.Case_Details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckVehiclePunishmentDetails extends AppCompatActivity {
    String id;
    TextView name,license,vehicle,crime,imprisonment,fine,sergent,batch,status;
    ImageView imageView;

    String photo;
    TextView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vehicle_punishment_details);
        id = getIntent().getStringExtra("id");
        name = findViewById(R.id.name);
        license = findViewById(R.id.license);
        vehicle = findViewById(R.id.vehicle);
        crime = findViewById(R.id.crime);
        imageView= findViewById(R.id.imageView);
        backbtn = findViewById(R.id.backbtn);
        photo=getIntent().getStringExtra("photo");
        imprisonment = findViewById(R.id.imprisonment);
        fine = findViewById(R.id.fine);
        sergent = findViewById(R.id.sergent);
        batch = findViewById(R.id.batch);
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
                    fine.setText("BDT "+data.getString("fine"));
                    status.setText(data.getString("Status"));
                    data.getString("Police_id");

                    getSergent(data.getString("Police_id"));


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckVehiclePunishmentDetails.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

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
                    batch.setText(data.getString("Police_id"));




                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckVehiclePunishmentDetails.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
}