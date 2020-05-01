package com.hitchhicker.hhapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hitchhicker.hhapp.Interface.ItemClickListner;
import com.hitchhicker.hhapp.R;

public class BlogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView blog_title,blog_title_opp;
    public ImageView blog_pic,blog_pic_opp;
    public ItemClickListner listner;

    public BlogViewHolder(@NonNull View itemView) {
        super(itemView);

        blog_title = (TextView) itemView.findViewById(R.id.blog_title);
        blog_pic = (ImageView) itemView.findViewById(R.id.blog_thumbnail);

        blog_title_opp = (TextView) itemView.findViewById(R.id.blog_title_opp);
        blog_pic_opp = (ImageView) itemView.findViewById(R.id.blog_thumbnail_opp);

    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(),false);
    }
}
