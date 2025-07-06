package com.thesis.trafficinfo.Users;

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
import com.thesis.trafficinfo.R;

import java.util.ArrayList;
import java.util.List;

public class MyVehicleAdapter extends RecyclerView.Adapter<MyVehicleAdapter.ViewHolder> {
    List<MyVehicle> modelList;
    Context context;

    public MyVehicleAdapter(List<MyVehicle> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    public void filteredList(ArrayList<MyVehicle> modelArrayList) //search result er jnno
    {
        modelList = modelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVehicleAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVehicleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(modelList.get(position).getOwner_name());
        holder.license_no.setText(modelList.get(position).getRegistration_No());
        holder.vehicle_no.setText(modelList.get(position).getVehicle_class());
        String imageurl3 = BASE_URL + "NID/" + modelList.get(position).getPhoto();
        Picasso.get().load(imageurl3).into(holder.imageView);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CheckMyVehicleDetails.class);
                intent.putExtra("id", modelList.get(position).getId());
                intent.putExtra("nid", modelList.get(position).getNID_No());
                intent.putExtra("reg", modelList.get(position).getRegistration_No());
                intent.putExtra("issuer", modelList.get(position).getIssuing_Authority());
                intent.putExtra("photo", modelList.get(position).getPhoto());
                intent.putExtra("owner_name", modelList.get(position).getOwner_name());
                intent.putExtra("v_class", modelList.get(position).getVehicle_class());
                intent.putExtra("chassis_no", modelList.get(position).getChassis_No());
                intent.putExtra("engine_no", modelList.get(position).getEngine_No());
                intent.putExtra("cc", modelList.get(position).getCC());
                intent.putExtra("color", modelList.get(position).getColor());
                intent.putExtra("mfgyear", modelList.get(position).getMfg_Year());
                intent.putExtra("route_permit_certificateno", modelList.get(position).getRoutepermit_certificate_no());
                intent.putExtra("r_e_d", modelList.get(position).getRoutepermit_expiry_date());
                intent.putExtra("taxtokenno", modelList.get(position).getTaxtoken_no());
                intent.putExtra("taxtokenno_ed", modelList.get(position).getTaxperiod_expiry_date());
                intent.putExtra("fitness", modelList.get(position).getFitness_certificate_no());
                intent.putExtra("fitness_ed", modelList.get(position).getFitness_certificate_expiry_date());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, license_no, vehicle_no;
        ImageView imageView;
        LinearLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.admin_name);
            license_no = itemView.findViewById(R.id.admin_Licence);
            vehicle_no = itemView.findViewById(R.id.admin_vehicle_no);
            imageView = itemView.findViewById(R.id.imageView);
            card = itemView.findViewById(R.id.card);
        }
    }
}