package com.example.acer.pixabayfragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChoclateAdapter extends RecyclerView.Adapter<ChoclateAdapter.MyViewHolder>
{

    Context context;
    ArrayList<ModelClass> mclass;

    public ChoclateAdapter(Context context, ArrayList<ModelClass> mclass) {
        this.context = context;
        this.mclass = mclass;
    }

    @NonNull
    @Override
    public ChoclateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v=LayoutInflater.from(context).inflate(R.layout.design,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoclateAdapter.MyViewHolder myViewHolder, int i)
    {
        ModelClass search=mclass.get(i);
        Picasso.with(context).load(search.imageone).into(myViewHolder.iv);

    }

    @Override
    public int getItemCount() {
        return mclass.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            iv=itemView.findViewById(R.id.text);
        }
    }
}
