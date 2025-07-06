package com.thesis.trafficinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thesis.trafficinfo.TraficPolicePanel.FindByLicense;
import com.thesis.trafficinfo.TraficPolicePanel.FindByVehicle;
import com.thesis.trafficinfo.TraficPolicePanel.WriteNID;

public class WriteVehicleRegistrationID extends AppCompatActivity {
    EditText searchByLicenseNumber;
    TextView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_vehicle_registration_id);
        backbtn=findViewById(R.id.backbtn);
        searchByLicenseNumber=findViewById(R.id.searchByVehicleReg);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void searchBtn(View view) {
        String number=searchByLicenseNumber.getText().toString();
        Intent intent=new Intent(WriteVehicleRegistrationID.this, FindByVehicle.class);
        intent.putExtra("licensenumber",number);
        startActivity(intent);
        //  String url=BASE_URL+"";

    }
}