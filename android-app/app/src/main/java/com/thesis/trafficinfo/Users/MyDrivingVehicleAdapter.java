package com.thesis.trafficinfo.Users;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.trafficinfo.Adapters.DrivingRequestAdapter;
import com.thesis.trafficinfo.R;

import java.util.List;

public class MyDrivingVehicleAdapter extends RecyclerView.Adapter<MyDrivingVehicleAdapter.DrivingRequestAdapterHolder> {
    List<MyDriver> modelList;
    Context context;

    public MyDrivingVehicleAdapter(List<MyDriver> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyDrivingVehicleAdapter.DrivingRequestAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyDrivingVehicleAdapter.DrivingRequestAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drivingrequestrowview, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyDrivingVehicleAdapter.DrivingRequestAdapterHolder holder, int position) {

        holder.checkVehicleNOTV.setText(modelList.get(position).getVehicle_reg_no());
        holder.checkVehicleOwnerTV.setText(modelList.get(position).getUser_name());
        holder.checkVehicleOwnerPhoneTV.setText(modelList.get(position).getPhone());
        holder.drivingRequestLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsOfVehicles.class);
                intent.putExtra("id",modelList.get(position).getId());
                intent.putExtra("name",modelList.get(position).getName());
                intent.putExtra("owner_name",modelList.get(position).getUser_name());
                intent.putExtra("phone",modelList.get(position).getPhone());
                intent.putExtra("address",modelList.get(position).getLocation());
                intent.putExtra("v_no",modelList.get(position).getVehicle_reg_no());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class DrivingRequestAdapterHolder extends RecyclerView.ViewHolder{

        TextView checkVehicleNOTV,checkVehicleOwnerTV,checkVehicleOwnerPhoneTV;
        LinearLayout drivingRequestLv;
        public DrivingRequestAdapterHolder(@NonNull View itemView) {
            super(itemView);
            checkVehicleNOTV=itemView.findViewById(R.id.checkVehicleNOTV);
            checkVehicleOwnerTV=itemView.findViewById(R.id.checkVehicleOwnerTV);
            checkVehicleOwnerPhoneTV=itemView.findViewById(R.id.checkVehicleOwnerPhoneTV);
            drivingRequestLv=itemView.findViewById(R.id.drivingRequestLv);
        }
    }
}
