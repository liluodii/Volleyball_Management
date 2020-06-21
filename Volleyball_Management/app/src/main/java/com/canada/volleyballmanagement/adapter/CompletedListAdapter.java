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
import com.canada.volleyballmanagement.databinding.ItemCompletedBinding;
import com.canada.volleyballmanagement.databinding.ItemCompletedBinding;
import com.canada.volleyballmanagement.pojo.MatchResponse;

import java.util.ArrayList;
import java.util.List;


public class CompletedListAdapter extends RecyclerView.Adapter<CompletedListAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<MatchResponse.Completed> data = new ArrayList<>();

    public CompletedListAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<MatchResponse.Completed> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void add(MatchResponse.Completed mData) {
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

    public MatchResponse.Completed getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemCompletedBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_completed, parent, false);
        binding.setActivity(this);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        MatchResponse.Completed dataModel = data.get(position);

        holder.bind(dataModel, position);

        RequestOptions options = new RequestOptions();
        options.placeholder(context.getResources().getDrawable(R.drawable.ic_person));
        options.error(context.getResources().getDrawable(R.drawable.ic_person));
        Glide.with(context).setDefaultRequestOptions(options)
                .load(dataModel.getTeam1Pic()).into(holder.binding.imgProfile);

        Glide.with(context).setDefaultRequestOptions(options)
                .load(dataModel.getTeam2Pic()).into(holder.binding.imgProfileSecond);

        if (dataModel.getWinnerTeam() == dataModel.getTeam1()) {
            holder.binding.txtTeamOneWinner.setVisibility(View.VISIBLE);
            holder.binding.txtTeamTwoWinner.setVisibility(View.GONE);
        } else {
            holder.binding.txtTeamOneWinner.setVisibility(View.GONE);
        }

        if (dataModel.getWinnerTeam() == dataModel.getTeam2()) {
            holder.binding.txtTeamTwoWinner.setVisibility(View.VISIBLE);
            holder.binding.txtTeamOneWinner.setVisibility(View.GONE);
        } else {
            holder.binding.txtTeamTwoWinner.setVisibility(View.GONE);
        }



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
        void onDelete(MatchResponse.Completed data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemCompletedBinding binding;

        public Holder(ItemCompletedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MatchResponse.Completed obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}