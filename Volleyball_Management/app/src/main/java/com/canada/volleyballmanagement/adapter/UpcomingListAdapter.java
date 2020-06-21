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
import com.canada.volleyballmanagement.databinding.ItemUpcomingBinding;
import com.canada.volleyballmanagement.databinding.ItemUpcomingBinding;
import com.canada.volleyballmanagement.pojo.MatchResponse;
import com.canada.volleyballmanagement.pojo.TournamentTeamResponse;

import java.util.ArrayList;
import java.util.List;


public class UpcomingListAdapter extends RecyclerView.Adapter<UpcomingListAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<MatchResponse.Upcomming> data = new ArrayList<>();

    public UpcomingListAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<MatchResponse.Upcomming> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void add(MatchResponse.Upcomming mData) {
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

    public MatchResponse.Upcomming getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUpcomingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_upcoming, parent, false);
        binding.setActivity(this);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        MatchResponse.Upcomming dataModel = data.get(position);

        holder.bind(dataModel, position);

        RequestOptions options = new RequestOptions();
        options.placeholder(context.getResources().getDrawable(R.drawable.ic_person));
        options.error(context.getResources().getDrawable(R.drawable.ic_person));
        Glide.with(context).setDefaultRequestOptions(options)
                .load(dataModel.getTeam1Pic()).into(holder.binding.imgProfile);

        Glide.with(context).setDefaultRequestOptions(options)
                .load(dataModel.getTeam2Pic()).into(holder.binding.imgProfileSecond);

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
        void onDelete(MatchResponse.Upcomming data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemUpcomingBinding binding;

        public Holder(ItemUpcomingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MatchResponse.Upcomming obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}