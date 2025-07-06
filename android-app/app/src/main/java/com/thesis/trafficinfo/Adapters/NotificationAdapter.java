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

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    List<History> modelList;
    Context context;

    public NotificationAdapter(List<History> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.notification_row_view,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.notificationText.setText("Your case No. "+modelList.get(position).getId()+""+ modelList.get(position).getCase_date().replace("-","")+" " +" is "+modelList.get(position).getStatus());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String photo=modelList.get(position).getPhoto();
                Intent intent = new Intent(context, Case_Details.class);
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
        TextView notificationText;

        LinearLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notificationText = itemView.findViewById(R.id.notificationText);
            card=itemView.findViewById(R.id.card);
        }
    }
}
