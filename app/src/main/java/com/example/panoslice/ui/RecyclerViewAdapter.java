package com.example.panoslice.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.panoslice.R;
import com.example.panoslice.data.model.GitModel;
import com.example.panoslice.data.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements
        Filterable {

    private Context mContext;
    private ArrayList<ItemModel> mAvatimage = new ArrayList<>();
    private ArrayList<GitModel> mName = new ArrayList<>();
    private ArrayList<GitModel> mFullname = new ArrayList<>();
    private ArrayList<GitModel> mWatchercount = new ArrayList<>();
    private ArrayList<ItemModel> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<ItemModel> mData) {
        this.mContext = mContext;
        this.mData = mData;


    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder,final int position) {

        System.out.println("Check3");
        ItemModel gitModel = mData.get(position);






            Glide.with(mContext).load(gitModel.getOwner().getAvatarURL()).into(holder.imageView);
            holder.name.setText(gitModel.getName());
            holder.fullname.setText(gitModel.getFullName());
            holder.watchlist.setText(gitModel.getWatchersCount().toString());
















    }

    @Override
    public int getItemCount() {
System.out.println("Mdata size:"+mData.size());
        return mData.size();
    }

    public void clear() {
        int size = mData.size();
        mData.clear();
        notifyDataSetChanged();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parent;
        TextView name,fullname,watchlist;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("Check5");
            name = itemView.findViewById(R.id.txtname);
            fullname = itemView.findViewById(R.id.txtfullname);
            watchlist = itemView.findViewById(R.id.txWatchcounter);
            imageView = itemView.findViewById(R.id.image);
            parent= itemView.findViewById(R.id.parentLayout);

        }
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }


    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItemModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ItemModel item : mData) {


                        if (item.getName().contains(filterPattern))
                        {
                            filteredList.add(item);
                        }



                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void updateData(ArrayList<ItemModel> newList){
        this.mName.clear();
        this.mData.addAll(newList);
    }



}
