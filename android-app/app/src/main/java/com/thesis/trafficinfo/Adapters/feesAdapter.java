package com.thesis.trafficinfo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.trafficinfo.Models.Fee;
import com.thesis.trafficinfo.R;

import java.util.List;

public class feesAdapter extends RecyclerView.Adapter<feesAdapter.ViewHolder> {
    List<Fee> modelList;
    Context context;

    public feesAdapter(List<Fee> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public feesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new feesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.fees_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull feesAdapter.ViewHolder holder, int position) {
        holder.rule.setText(modelList.get(position).getRule());
        holder.main_fees.setText(modelList.get(position).getMain_Fees());
        holder.inspection.setText(modelList.get(position).getInspection_fee());
        holder.applicationfees.setText(modelList.get(position).getApplication_fee());
        holder.delay.setText(modelList.get(position).getDelay_fines());
        holder.vat.setText(modelList.get(position).getVAT());
        holder.total.setText(modelList.get(position).getTotal_fees());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rule,main_fees,inspection,label,applicationfees,delay,vat,total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rule = itemView.findViewById(R.id.rule);
            main_fees = itemView.findViewById(R.id.main_fees);
            inspection = itemView.findViewById(R.id.inspection);
            label = itemView.findViewById(R.id.label);
            applicationfees = itemView.findViewById(R.id.applicationfees);
            delay = itemView.findViewById(R.id.delay);
            vat = itemView.findViewById(R.id.vat);
            total = itemView.findViewById(R.id.total);
        }
    }
}
