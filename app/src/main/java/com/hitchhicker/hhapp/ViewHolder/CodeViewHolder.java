package com.hitchhicker.hhapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hitchhicker.hhapp.Interface.ItemClickListner;
import com.hitchhicker.hhapp.R;

public class CodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ItemClickListner listner;
    public TextView titleOfCode;
    public CardView codeCard;

    public CodeViewHolder(@NonNull View itemView) {
        super(itemView);

        titleOfCode = (TextView)itemView.findViewById(R.id.code_title_layout);
        codeCard = (CardView) itemView.findViewById(R.id.code_card_layout);
    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(),false);
    }
}
