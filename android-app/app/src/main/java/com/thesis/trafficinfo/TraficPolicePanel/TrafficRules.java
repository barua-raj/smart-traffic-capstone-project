package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.thesis.trafficinfo.Adapters.LawsAdapter;
import com.thesis.trafficinfo.Adapters.Vehicle_adapter;
import com.thesis.trafficinfo.Models.laws;
import com.thesis.trafficinfo.Models.vehicles;
import com.thesis.trafficinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrafficRules extends AppCompatActivity {
    EditText search;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LawsAdapter adapter;
    List<laws> modelList;
    TextView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_rules);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.pb);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        modelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
        getData();
    }
    public void filter(String text) //search button er kaj new list e add kora
    {
        ArrayList<laws> modelArrayList = new ArrayList<>();
        for(laws model: modelList)
        {
            if(model.getCrime_type().toLowerCase().contains(text.toLowerCase())){
                modelArrayList.add(model);

            }
            adapter.filteredList(modelArrayList);
        }
    }
    public void getData(){
        String url=BASE_URL+"getRules.php";
        Log.d("TAG", "datashow: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(TrafficRules.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new laws(
                                data.getString("id"),
                                data.getString("section"),
                                data.getString("crime_type"),
                                data.getString("imprisonment"),
                                data.getString("fine"),
                                data.getString("first_fine"),
                                data.getString("next_fine_s")
                        ));

                    }
                    adapter = new LawsAdapter(modelList,TrafficRules.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(TrafficRules.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(stringRequest);

    }
}