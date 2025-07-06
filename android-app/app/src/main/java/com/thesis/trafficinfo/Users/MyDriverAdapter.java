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
import com.thesis.trafficinfo.Adapters.Drivers_adapter;
import com.thesis.trafficinfo.Models.drivers;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.CaseByLicense;

import java.util.ArrayList;
import java.util.List;

public class MyDriverAdapter extends RecyclerView.Adapter<MyDriverAdapter.ViewHolder> {
    List<MyDriver> modelList;
    Context context;

    public MyDriverAdapter(List<MyDriver> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    public void filteredList(ArrayList<MyDriver> modelArrayList) //search result er jnno
    {
        modelList = modelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyDriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyDriverAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drivers_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyDriverAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(modelList.get(position).getName());
        holder.license_no.setText(modelList.get(position).getDriving_license_no());
        holder.vehicle_no.setText(modelList.get(position).getVehicle_reg_no());
        String imageurl3 = BASE_URL + "NID/" + modelList.get(position).getImage();
        Picasso.get().load(imageurl3).into(holder.imageView);

      holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CheckMyDriverHistory.class);
                intent.putExtra("id", modelList.get(position).getId());
                intent.putExtra("license", modelList.get(position).getDriving_license_no());
                intent.putExtra("issue", "BRTA, Chittagong Metro (Ctg)");
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
            phone = itemView.findViewById(R.id.admin_phone);
            license_no = itemView.findViewById(R.id.admin_Licence);
            vehicle_no = itemView.findViewById(R.id.admin_vehicle_no);
            imageView = itemView.findViewById(R.id.imageView);
            card = itemView.findViewById(R.id.card);
        }
    }
}