package com.wahyudemo.recyclerview;

import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;
import java.util.*;

public class Adapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>
{
    private Context mContext;
    private LayoutInflater inflater;
    private List <Data> data;
    Data current;

    public Adapter(Context mContext, List<Data> data)
    {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.data = data;
        data = new ArrayList<>();
        data.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        MyHolder myHolder = (MyHolder) holder;
        Data item = data.get(position);

        myHolder.title.setText(item.mTitle);
        Glide.with(mContext).load(item.mImage).into(myHolder.image);
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        public MyHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new 
                View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        Context mContext = v.getContext();
                        Toast.makeText(mContext, data.get(getAdapterPosition()).mTitle + " has clicked", Toast.LENGTH_LONG).show();
                    }
                });
        }
    }
}

