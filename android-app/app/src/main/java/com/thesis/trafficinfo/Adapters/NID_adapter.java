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
import com.thesis.trafficinfo.Models.NID_model;
import com.thesis.trafficinfo.Models.drivers;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.CaseByLicense;
import com.thesis.trafficinfo.TraficPolicePanel.NID_Details;

import java.util.ArrayList;
import java.util.List;

public class NID_adapter extends RecyclerView.Adapter<NID_adapter.ViewHolder> {
    List<NID_model> modelList;
    Context context;

    public NID_adapter(List<NID_model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }
    public void filteredList(ArrayList<NID_model> modelArrayList) //search result er jnno
    {
        modelList = modelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NID_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NID_adapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.nid_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NID_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(modelList.get(position).getName());
        holder.license_no.setText(modelList.get(position).getNID_No());
        holder.vehicle_no.setText(modelList.get(position).getAddress());
        String imageurl3 =BASE_URL+"NID/"+modelList.get(position).getPhoto();
        Picasso.get().load(imageurl3).into(holder.imageView);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context, NID_Details.class);
                intent.putExtra("id", modelList.get(position).getId());
                intent.putExtra("nid", modelList.get(position).getNID_No());

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
        ImageView imageView;
        LinearLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.admin_name);
            phone =itemView.findViewById(R.id.admin_phone);
            license_no = itemView.findViewById(R.id.admin_Licence);
            vehicle_no = itemView.findViewById(R.id.admin_vehicle_no);
            imageView = itemView.findViewById(R.id.imageView);
            card = itemView.findViewById(R.id.card);
        }
    }
}
