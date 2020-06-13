package com.canada.volleyballmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.databinding.ItemTeamBinding;
import com.canada.volleyballmanagement.databinding.ItemTeamBinding;
import com.canada.volleyballmanagement.pojo.GetTeamListResponse;

import java.util.ArrayList;
import java.util.List;


public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<GetTeamListResponse.Datum> data = new ArrayList<>();

    public TeamListAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<GetTeamListResponse.Datum> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void add(GetTeamListResponse.Datum mData) {
        data.add(mData);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public boolean checkSize() {
        if (data.size() == 0)
            return true;
        else
            return false;
    }

    public void removeAt(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public GetTeamListResponse.Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemTeamBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_team, parent, false);
        binding.setActivity(this);

        return new Holder(binding);
    }

    public void onClick(View view, int position) {

        switch (view.getId()) {
            case R.id.imgEdit:
                if (mEventListener != null) {
                    mEventListener.onEdit(data.get(position), position);
                }
                break;
            case R.id.imgDelete:
                if (mEventListener != null) {
                    mEventListener.onDelete(data.get(position), position);
                }
                break;
        }

    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        GetTeamListResponse.Datum dataModel = data.get(position);

        holder.bind(dataModel, position);

        RequestOptions options = new RequestOptions();
        options.placeholder(context.getResources().getDrawable(R.drawable.ic_person));
        options.error(context.getResources().getDrawable(R.drawable.ic_person));
        Glide.with(context).setDefaultRequestOptions(options)
                .load(dataModel.getPhoto()).into(holder.binding.imgProfile);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public EventListener getEventListener() {
        return mEventListener;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }


    public interface EventListener {
        void onDelete(GetTeamListResponse.Datum data, int position);

        void onEdit(GetTeamListResponse.Datum data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemTeamBinding binding;

        public Holder(ItemTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GetTeamListResponse.Datum obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}