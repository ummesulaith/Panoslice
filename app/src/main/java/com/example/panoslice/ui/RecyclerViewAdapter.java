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

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements
        Filterable {

    private Context mContext;
    private ArrayList<GitModel> mAvatimage = new ArrayList<>();
    private ArrayList<GitModel> mName = new ArrayList<>();
    private ArrayList<GitModel> mFullname = new ArrayList<>();
    private ArrayList<GitModel> mWatchercount = new ArrayList<>();
    private ArrayList<GitModel> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<GitModel> mData) {
        this.mContext = mContext;
        this.mData = mData;

        System.out.println("Check1");
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.item_layout, parent, false);
        System.out.println("Check2");
//        return new MyViewHolder(view);

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder,final int position) {

        System.out.println("Check3");
        GitModel gitModel = mData.get(position);




//        System.out.println("Checking mData"+mData);
//        for (int i=0;i<gitModel.getItems().size();i++)
//        {
            Glide.with(mContext).load(gitModel.getItems().get(position).getOwner().getAvatarURL()).into(holder.imageView);
            holder.name.setText(gitModel.getItems().get(position).getName());
            holder.fullname.setText(gitModel.getItems().get(position).getFullName());
            holder.watchlist.setText(gitModel.getItems().get(position).getWatchersCount().toString());

            System.out.println("Avatr"+Glide.with(mContext).load(gitModel.
                    getItems().get(position).getOwner().getAvatarURL()).into(holder.imageView));
//        }



    }

    @Override
    public int getItemCount() {
        System.out.println("Check4");
        System.out.println("check4 Size:"+mData.size());
        return mData.size();
    }

    public void clear() {
        int size = mData.size();
        mData.clear();
        notifyDataSetChanged();
    }



    public void setmData(ArrayList<GitModel> data)
    {
        this.mData = mData;
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
            List<GitModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GitModel item : mData) {

                    for (int i =0;i<item.getItems().size();i++)
                    {
                        if (item.getItems().get(i).getName().contains(filterPattern))
                        {
                            filteredList.add(item);
                        }
                    }


//                    if (item.getLogin().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
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

    public void updateData(ArrayList<GitModel> newList){
        this.mName.clear();
        this.mData.addAll(newList);
    }



}
