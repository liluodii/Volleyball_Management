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
import com.canada.volleyballmanagement.databinding.ItemSelectTeamMemberBinding;
import com.canada.volleyballmanagement.pojo.SelectTeamMemberResponse;

import java.util.ArrayList;
import java.util.List;


public class SelectTeamMemberAdapter extends RecyclerView.Adapter<SelectTeamMemberAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<SelectTeamMemberResponse.Datum> data = new ArrayList<>();

    public SelectTeamMemberAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<SelectTeamMemberResponse.Datum> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void add(SelectTeamMemberResponse.Datum mData) {
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

    public SelectTeamMemberResponse.Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemSelectTeamMemberBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_select_team_member, parent, false);
        binding.setActivity(this);

        return new Holder(binding);
    }

    public void onClick(View view, int position) {

        switch (view.getId()) {
            case R.id.imgDelete:
                if (mEventListener != null) {
                    mEventListener.onDelete(data.get(position), position);
                }
                break;
        }

    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        SelectTeamMemberResponse.Datum dataModel = data.get(position);

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
        void onDelete(SelectTeamMemberResponse.Datum data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemSelectTeamMemberBinding binding;

        public Holder(ItemSelectTeamMemberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SelectTeamMemberResponse.Datum obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}