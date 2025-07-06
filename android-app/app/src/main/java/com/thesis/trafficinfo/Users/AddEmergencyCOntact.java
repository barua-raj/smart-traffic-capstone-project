package com.thesis.trafficinfo.Users;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.Adapters.EmergencyAdapter;
import com.thesis.trafficinfo.Models.ContactModel;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEmergencyCOntact extends AppCompatActivity {
    String user_name;
    public static final String EMAIL = "EMAIL";
    int PRIVATE_MODE =0;
    Button buttton_login;
    private static final String PREF_NAME ="LOGIN";
    SharedPreferences sharedPreferences;
    EditText emergencyNameEt,emergencyPhoneEt;
    List<ContactModel> contactModels;
    RecyclerView emergecnyContactRv;
    TextView backbtn;
    EmergencyAdapter emergencyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency_contact);
        contactModels=new ArrayList<>();
        backbtn = findViewById(R.id.backbtn);
        buttton_login=findViewById(R.id.buttton_login);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        emergencyNameEt=findViewById(R.id.emergencyNameEt);
        emergecnyContactRv=findViewById(R.id.emergecnyContactRv);
        emergencyPhoneEt=findViewById(R.id.emergencyPhoneEt);

        sharedPreferences=getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        user_name=sharedPreferences.getString(EMAIL,null);

        emergecnyContactRv.setLayoutManager(new LinearLayoutManager(this));
        emergecnyContactRv.setHasFixedSize(true);
        getDataa(user_name);
    }

    public void uploadBtn(View view) {

        insertPhone();
    }

    public  void insertPhone(){
        String url=BASE_URL+"addemergencynumber.php";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                finish();
                Toast.makeText(AddEmergencyCOntact.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();

                stringStringMap.put("contact_name", emergencyNameEt.getText().toString());
                stringStringMap.put("number",emergencyPhoneEt.getText().toString());
                stringStringMap.put("owner_name", user_name);


                return stringStringMap;
            }
        }

                ;
        requestQueue.add(stringRequest);

    }
    public void getDataa(String nid){
     //   progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"getEmergencyNumber.php?owner_name="+nid;
        RequestQueue requestQueue = Volley.newRequestQueue(AddEmergencyCOntact.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        contactModels.add(new ContactModel(
                                data.getString("id"),
                                data.getString("owner_name"),
                                data.getString("number"),
                                data.getString("contact_name")
                        ));

                    }
                    emergencyAdapter = new EmergencyAdapter(contactModels,AddEmergencyCOntact.this);
                    emergecnyContactRv.setAdapter(emergencyAdapter);
                    emergencyAdapter.notifyDataSetChanged();
                    emergencyNameEt.setVisibility(View.GONE);
                    emergencyPhoneEt.setVisibility(View.GONE);
                    buttton_login.setVisibility(View.GONE);


                }catch (JSONException e){

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("owner_name",  nid);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
}