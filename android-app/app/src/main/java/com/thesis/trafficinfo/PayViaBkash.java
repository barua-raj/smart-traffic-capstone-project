package com.thesis.trafficinfo;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayViaBkash extends AppCompatActivity {

    EditText phoneEdit,pinEdit,payment;
    ProgressBar pb;
    String  fine,id,c_status;
    TextView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_via_bkash);
        id = getIntent().getStringExtra("id");
        fine=getIntent().getStringExtra("fine");
        phoneEdit=findViewById(R.id.phoneEdit);
        pb=findViewById(R.id.pb);
        pinEdit=findViewById(R.id.pinEdit);
        payment=findViewById(R.id.payment); backbtn = findViewById(R.id.backbtn);
        payment.setText("Fine Amount="+fine);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void Pay(View view) {
        String p=phoneEdit.getText().toString();
        String pi=pinEdit.getText().toString();
        login(p,pi);


    }
    public void login(String p, String pin) {
        pb.setVisibility(View.VISIBLE);
        //sessionManager = new SessionManager(this);
        String url = BASE_URL+"getbkashacc.php?phone="+p+"&pin="+pin;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                pb.setVisibility(View.GONE);
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                JSONObject userObject = jsonArray.getJSONObject(0);
                if (!userObject.getString("error").equalsIgnoreCase("no")) {

                    Toast.makeText(PayViaBkash.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent=new Intent(PayViaBkash.this,ConfirmPayment.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }

            } catch (JSONException e) { Toast.makeText(PayViaBkash.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PayViaBkash.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }
        }
        );
        requestQueue.add(stringRequest);

    }
}