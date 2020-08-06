package com.example.panoslice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<String> mAvatimage = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mFullname = new ArrayList<>();
    private ArrayList<String> mWatchercount = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mAvatimage,
                               ArrayList<String> mName, ArrayList<String> mFullname, ArrayList<String> mWatchercount) {
        this.mContext = mContext;
        this.mAvatimage = mAvatimage;
        this.mName = mName;
        this.mFullname = mFullname;
        this.mWatchercount = mWatchercount;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        Glide.with(mContext).load(mAvatimage.get(position)).into(holder.imageView);
        holder.name.setText(mName.get(position));
        holder.fullname.setText(mFullname.get(position));
        holder.watchlist.setText(mWatchercount.get(position));

    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,fullname,watchlist;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtname);
            fullname = itemView.findViewById(R.id.txtfullname);
            watchlist = itemView.findViewById(R.id.txWatchcounter);
            imageView = itemView.findViewById(R.id.image);

        }
    }
}
