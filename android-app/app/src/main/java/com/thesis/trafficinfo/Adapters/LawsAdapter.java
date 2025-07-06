package com.thesis.trafficinfo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.trafficinfo.Models.drivers;
import com.thesis.trafficinfo.Models.laws;
import com.thesis.trafficinfo.R;

import java.util.ArrayList;
import java.util.List;

public class LawsAdapter extends RecyclerView.Adapter<LawsAdapter.ViewHolder> {
    List<laws> modelList;
    Context context;

    public LawsAdapter(List<laws> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    public void filteredList(ArrayList<laws> modelArrayList) //search result er jnno
    {
        modelList = modelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LawsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LawsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.laws_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LawsAdapter.ViewHolder holder, int position) {
        holder.section.setText("Section: "+modelList.get(position).getSection());
        holder.crime.setText(modelList.get(position).getCrime_type());
        holder.first.setText(modelList.get(position).getFirst_fine()+" Taka");
        holder.next.setText(modelList.get(position).getNext_fine_s()+" Taka");


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView section,crime,first,next;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            section = itemView.findViewById(R.id.section);
            crime = itemView.findViewById(R.id.crime);
            first = itemView.findViewById(R.id.first);
            next = itemView.findViewById(R.id.next);
        }
    }
}
