
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyVehicleList extends AppCompatActivity {

    RecyclerView VehiclerecyclerView;
    ProgressBar progressBar;
    MyVehicleAdapter adapter;
    List<MyVehicle> modelList;
    TextView backbtn;
    String nid;
    SessionManager sessionManager;
    String number;
    SharedPreferences sharedPreferences;
    public static final String EMAIL = "EMAIL";

    int PRIVATE_MODE =0;
    String user_name,d_nid;
    private static final String PREF_NAME ="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicle_list);

        VehiclerecyclerView=findViewById(R.id.VehiclerecyclerView);

        progressBar = findViewById(R.id.pb);
        backbtn = findViewById(R.id.backbtn);
        sharedPreferences=getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        user_name=sharedPreferences.getString(EMAIL,null);
        d_nid=sharedPreferences.getString("d_nid",null);
        modelList = new ArrayList<>();
        VehiclerecyclerView.setHasFixedSize(true);
      //  VehiclerecyclerView.setAdapter(adapter);
        VehiclerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataa(d_nid);


    }
    public void getDataa(String nid){
        progressBar.setVisibility(View.VISIBLE);
        String url=BASE_URL+"myVehicelList.php?NID_No="+nid;
        RequestQueue requestQueue = Volley.newRequestQueue(MyVehicleList.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);
                    Log.d("TAG", "Url: "+url);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new MyVehicle(
                                data.getString("id"),
                                data.getString("Registration_No"),
                                data.getString("Vehicle_Description"),
                                data.getString("Vehicle_class"),
                                data.getString("CC"),
                                data.getString("Color"),
                                data.getString("Fuel"),
                                data.getString("Seat"),
                                data.getString("Engine_No"),
                                data.getString("Chassis_No"),
                                data.getString("Hire"),
                                data.getString("Date"),
                                data.getString("Wheel_Base"),
                                data.getString("Issuing_Authority"),
                                data.getString("Weight_Unladen"),
                                data.getString("Weight_laden"),
                                data.getString("NID_No"),
                                data.getString("photo"),
                                data.getString("Owner_name"),
                                data.getString("Owner_address"),
                                data.getString("Tyre_size"),
                                data.getString("H.P"),
                                data.getString("Mfg_Year"),
                                data.getString("routepermit_certificate_no"),
                                data.getString("routepermit_expiry_date"),
                                data.getString("fitness_certificate_no"),
                                data.getString("fitness_certificate_expiry_date"),
                                data.getString("taxtoken_no"),
                                data.getString("taxperiod_expiry_date")
                        ));

                    }
               adapter = new MyVehicleAdapter(modelList,MyVehicleList.this);
                    VehiclerecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    progressBar.setVisibility(View.GONE);

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);



            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("ref_no",  nid);


                return stringStringMap;
            }
        };


        requestQueue.add(stringRequest);
    }
}