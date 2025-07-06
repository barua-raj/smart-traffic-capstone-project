package com.thesis.trafficinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thesis.trafficinfo.TraficPolicePanel.FindByLicense;
import com.thesis.trafficinfo.TraficPolicePanel.FindByNID;
import com.thesis.trafficinfo.TraficPolicePanel.WriteNID;

public class WriteNationalIDCard extends AppCompatActivity {
    EditText searchByLicenseNumber;
    TextView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_national_idcard);
        searchByLicenseNumber=findViewById(R.id.searchByLicenseNumber);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void nidFilterBtn(View view) {
        String number=searchByLicenseNumber.getText().toString();
        Intent intent=new Intent(WriteNationalIDCard.this, FindByNID.class);
        intent.putExtra("licensenumber",number);
        startActivity(intent);
        //  String url=BASE_URL+"";

    }
}