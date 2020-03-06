package com.suny.common.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suny.common.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private List<Data> mDataList;


    public abstract static class ViewHolder<Data> extends RecyclerView.ViewHolder {
        private Data data;

        public void bind(Data data){
            this.data = data;
            onBind(data);
        }

        public abstract void update(Data data);

        public abstract void onBind(Data data);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));
    }

    public abstract int getItemViewType(int position, Data data);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        ViewHolder holder = onCreateViewHolder(root, viewType);
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);
        root.setTag(R.id.tag_recycler_view, holder);
        return holder;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public void add(Data data){
        mDataList.add(data);
        notifyItemInserted(mDataList.size()-1);
    }

    public void add(Data... dataList){
        Collections.addAll(mDataList, dataList);
        notifyItemRangeInserted(mDataList.size(), dataList.length);
    }

    public void add(List<Data> dataList){
        mDataList.addAll(dataList);
        notifyItemRangeInserted(mDataList.size(), dataList.size());
    }

    public void replace(List<Data> list){
        mDataList.clear();
        if(list!=null && list.size()>0){
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void remove(){
        mDataList.clear();
        notifyDataSetChanged();
    }

    public abstract ViewHolder onCreateViewHolder(View root, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
