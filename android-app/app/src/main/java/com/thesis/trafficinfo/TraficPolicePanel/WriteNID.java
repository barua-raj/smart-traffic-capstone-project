package com.thesis.trafficinfo.TraficPolicePanel;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thesis.trafficinfo.R;

public class WriteNID extends AppCompatActivity {
    EditText searchByLicenseNumber;
    TextView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_nid);
        searchByLicenseNumber=findViewById(R.id.searchByLicenseNumber);
        backbtn=findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void searchBtn(View view) {
        String number=searchByLicenseNumber.getText().toString();
        Intent intent=new Intent(WriteNID.this,FindByLicense.class);
        intent.putExtra("licensenumber",number);
        startActivity(intent);
      //  String url=BASE_URL+"";

    }
}