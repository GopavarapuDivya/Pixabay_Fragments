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

public class TeddyBearAdapter extends RecyclerView.Adapter<TeddyBearAdapter.MyViewHolder>
{
    Context context;
    ArrayList<ModelClass> modelClasses;

    public TeddyBearAdapter(Context context, ArrayList<ModelClass> modelClasses)
    {
        this.context = context;
        this.modelClasses = modelClasses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v=LayoutInflater.from(context).inflate(R.layout.design,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        ModelClass search=modelClasses.get(i);
        Picasso.with(context).load(search.imageone).into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.text);
        }
    }
}
