package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.DriverViewVehicleDetaisl;
import com.thesis.trafficinfo.R;

import java.util.HashMap;
import java.util.Map;

public class AcceptorRejectRequest extends AppCompatActivity {
    TextView nameTv,v_regTv,phoneTv,locationTv;
    String name, v_reg, phone, location,id;
    TextView backbtn,viewSDetailsRTv;
    String  photo_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptor_reject_request);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nameTv=findViewById(R.id.nameTv);
        v_regTv=findViewById(R.id.v_regTv);
        phoneTv=findViewById(R.id.phoneTv);
        locationTv=findViewById(R.id.locationTv);
    viewSDetailsRTv=findViewById(R.id.viewSDetailsRTv);

    photo_name=getIntent().getStringExtra("photo_name");
        name=getIntent().getStringExtra("owner_name");
        phone=getIntent().getStringExtra("phone");
        location=getIntent().getStringExtra("address");
        v_reg=getIntent().getStringExtra("v_no");
        id=getIntent().getStringExtra("id");
       // Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        nameTv.setText(name);
        phoneTv.setText(phone);
        v_regTv.setText(v_reg);
        locationTv.setText(location);
        viewSDetailsRTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AcceptorRejectRequest.this, DriverViewVehicleDetaisl.class);
                intent.putExtra("v_reg",v_reg);
                intent.putExtra("photo_name",photo_name);
                startActivity(intent);
            }
        });
    }

    public void Accept(View view) {
        AcceptRequest(id);



    }

    public void Reject(View view) {
        RejectRequest(id);
    }

    public void AcceptRequest(String id){

        String url=BASE_URL+"acceptRequest.php?id="+id;
        RequestQueue requestQueue = Volley.newRequestQueue(AcceptorRejectRequest.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AcceptorRejectRequest.this, ""+response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AcceptorRejectRequest.this,DriverActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  progressBar.setVisibility(View.GONE);



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
    public void RejectRequest(String id){

        String url=BASE_URL+"rejectRequest.php?id="+id;
        RequestQueue requestQueue = Volley.newRequestQueue(AcceptorRejectRequest.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AcceptorRejectRequest.this, ""+response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AcceptorRejectRequest.this,DriverActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  progressBar.setVisibility(View.GONE);



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