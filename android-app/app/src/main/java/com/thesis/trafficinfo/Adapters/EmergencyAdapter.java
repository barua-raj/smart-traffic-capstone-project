package com.thesis.trafficinfo.Adapters;

import static com.thesis.trafficinfo.Constant.BASE_URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.thesis.trafficinfo.LoginAndSignUp.History;
import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.LoginAndSignUp.Select_Role;
import com.thesis.trafficinfo.Models.ContactModel;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.TraficPolicePanel.Case_Details;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ViewHolder> {
    List<ContactModel> modelList;
    Context context;

    public EmergencyAdapter(List<ContactModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmergencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmergencyAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_contact_row, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.contacPhoneTv.setText(modelList.get(position).getNumber());
        holder.contactNameTv.setText(modelList.get(position).getContact_name());
        String  id=modelList.get(position).getId();
        holder.emergencyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Are you sure you want to delete this contact?")
//set message

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                deleteDriver(id);
                            }
                        })
//set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
                                Toast.makeText(context,"Nothing Happened",Toast.LENGTH_LONG).show();
                            }
                        })

                .show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameTv, contacPhoneTv;
        LinearLayout emergencyC;


        TextView case_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contacPhoneTv = itemView.findViewById(R.id.contacPhoneTv);
            contactNameTv = itemView.findViewById(R.id.contactNameTv);
            emergencyC=itemView.findViewById(R.id.emergencyC);

        }
    }
    public void deleteDriver(String  id){


            String url = BASE_URL+"deleteEmergency.php?id="+id;
            Log.d("TAG", "url: "+url);
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    ((Activity)context).finish();
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringStringMap = new HashMap<>();

                    stringStringMap.put("id",id );

                    return stringStringMap;
                }
            };


            requestQueue.add(stringRequest);
        }
    }

