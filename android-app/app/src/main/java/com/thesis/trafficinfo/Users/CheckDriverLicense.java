package com.thesis.trafficinfo.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.FindByLicense;
import com.thesis.trafficinfo.TraficPolicePanel.WriteNID;

public class CheckDriverLicense extends AppCompatActivity {
    EditText searchByLicenseNumber;
    TextView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_driver_license);
        searchByLicenseNumber=findViewById(R.id.search);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void searchByDLicense(View view) {
        String number=searchByLicenseNumber.getText().toString();
        Intent intent=new Intent(CheckDriverLicense.this, GetDriverInfo.class);
        intent.putExtra("licensenumber",number);
        startActivity(intent);

    }
}