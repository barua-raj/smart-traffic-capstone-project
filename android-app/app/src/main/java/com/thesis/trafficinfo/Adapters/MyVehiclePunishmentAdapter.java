package com.thesis.trafficinfo.Adapters;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.LoginAndSignUp.History;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.Case_Details;
import com.thesis.trafficinfo.Users.CheckVehiclePunishmentDetails;

import java.util.List;

public class MyVehiclePunishmentAdapter extends RecyclerView.Adapter<MyVehiclePunishmentAdapter.ViewHolder> {
    List<History> modelList;
    Context context;

    public MyVehiclePunishmentAdapter(List<History> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVehiclePunishmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVehiclePunishmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.vehicle_punishment_row,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyVehiclePunishmentAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(modelList.get(position).getApplicant_name());
        holder.license_no.setText(modelList.get(position).getLicense_no());
        holder.vehicle_no.setText(modelList.get(position).getCrime());
       holder.case_status.setText(modelList.get(position).getStatus());

        //  Api.getPicture(holder.imageView,context,modelList.get(position).getNID_No());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String photo=modelList.get(position).getPhoto();
                Intent intent = new Intent(context, CheckVehiclePunishmentDetails.class);
                intent.putExtra("id", modelList.get(position).getId());
                intent.putExtra("photo", modelList.get(position).getPhoto());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,license_no,vehicle_no;

        LinearLayout card;

        TextView case_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.admin_name);
            phone =itemView.findViewById(R.id.admin_phone);
            license_no = itemView.findViewById(R.id.admin_Licence);
            vehicle_no = itemView.findViewById(R.id.admin_vehicle_no);

            card = itemView.findViewById(R.id.card);
            case_status = itemView.findViewById(R.id.case_status);
        }
    }
}
