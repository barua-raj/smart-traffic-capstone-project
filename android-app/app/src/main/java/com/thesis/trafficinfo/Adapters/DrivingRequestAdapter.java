package com.thesis.trafficinfo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.Users.AcceptorRejectRequest;
import com.thesis.trafficinfo.Users.MyDriver;
import com.thesis.trafficinfo.Users.MyDriverAdapter;

import java.util.List;

public class DrivingRequestAdapter extends RecyclerView.Adapter<DrivingRequestAdapter.DrivingRequestAdapterHolder> {
    List<MyDriver> modelList;
    Context context;

    public DrivingRequestAdapter(List<MyDriver> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public DrivingRequestAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrivingRequestAdapter.DrivingRequestAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drivingrequestrowview, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DrivingRequestAdapterHolder holder, int position) {

        holder.checkVehicleNOTV.setText(modelList.get(position).getVehicle_reg_no());
        holder.checkVehicleOwnerTV.setText(modelList.get(position).getName());
        holder.checkVehicleOwnerPhoneTV.setText(modelList.get(position).getPhone());
        holder.drivingRequestLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AcceptorRejectRequest.class);
                intent.putExtra("id",modelList.get(position).getId());
                intent.putExtra("name",modelList.get(position).getD_name());
                intent.putExtra("owner_name",modelList.get(position).getName());
                intent.putExtra("phone",modelList.get(position).getPhone());
                intent.putExtra("address",modelList.get(position).getLocation());
                intent.putExtra("v_no",modelList.get(position).getVehicle_reg_no());
                intent.putExtra("photo_name",modelList.get(position).getImage());

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
