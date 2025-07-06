package com.thesis.trafficinfo.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.trafficinfo.LoginAndSignUp.History;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.Case_Details;
import com.thesis.trafficinfo.Users.MyDriver;

import java.util.List;

public class RequestNotificationAdapter extends RecyclerView.Adapter<RequestNotificationAdapter.ViewHolder> {
    List<MyDriver> modelList;
    Context context;

    public RequestNotificationAdapter(List<MyDriver> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestNotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestNotificationAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.notification_row_view,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RequestNotificationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.notificationText.setText(modelList.get(position).getD_name()+" has "+modelList.get(position).getStatus()+"  your driving request");


        /*holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String photo=modelList.get(position).getPhoto();
                Intent intent = new Intent(context, Case_Details.class);
                intent.putExtra("id", modelList.get(position).getId());
                intent.putExtra("photo", modelList.get(position).getPhoto());
                context.startActivity(intent);


            }
        });*/


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notificationText;

        LinearLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notificationText = itemView.findViewById(R.id.notificationText);
            card=itemView.findViewById(R.id.card);
        }
    }
}
