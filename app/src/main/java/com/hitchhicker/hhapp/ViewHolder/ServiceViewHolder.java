package com.hitchhicker.hhapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hitchhicker.hhapp.Interface.ItemClickListner;
import com.hitchhicker.hhapp.R;

public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ItemClickListner listner;
    public TextView service_name,service_desc;
    public ImageView service_logo;

    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);

        service_name = (TextView)itemView.findViewById(R.id.service_name);
        service_desc = (TextView)itemView.findViewById(R.id.service_desc);
        service_logo = (ImageView)itemView.findViewById(R.id.service_logo);

    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(),false);
    }
}
