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
import com.canada.volleyballmanagement.databinding.ItemTeamManagerBinding;
import com.canada.volleyballmanagement.databinding.ItemTeamManagerBinding;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;

import java.util.ArrayList;
import java.util.List;


public class TeamManagerListAdapter extends RecyclerView.Adapter<TeamManagerListAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<GetTeamManagerListResponse.Datum> data = new ArrayList<>();

    public TeamManagerListAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<GetTeamManagerListResponse.Datum> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void add(GetTeamManagerListResponse.Datum mData) {
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

    public GetTeamManagerListResponse.Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemTeamManagerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_team_manager, parent, false);
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

        GetTeamManagerListResponse.Datum dataModel = data.get(position);

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
        void onDelete(GetTeamManagerListResponse.Datum data, int position);

        void onEdit(GetTeamManagerListResponse.Datum data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemTeamManagerBinding binding;

        public Holder(ItemTeamManagerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GetTeamManagerListResponse.Datum obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}