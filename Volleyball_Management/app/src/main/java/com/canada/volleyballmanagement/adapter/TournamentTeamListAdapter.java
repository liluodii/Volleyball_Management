package com.canada.volleyballmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.databinding.ItemTournamentTeamBinding;
import com.canada.volleyballmanagement.databinding.ItemTournamentTeamBinding;
import com.canada.volleyballmanagement.pojo.TournamentTeamResponse;

import java.util.ArrayList;
import java.util.List;


public class TournamentTeamListAdapter extends RecyclerView.Adapter<TournamentTeamListAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<TournamentTeamResponse.Datum> data = new ArrayList<>();

    public TournamentTeamListAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<TournamentTeamResponse.Datum> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void add(TournamentTeamResponse.Datum mData) {
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

    public TournamentTeamResponse.Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemTournamentTeamBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_tournament_team, parent, false);
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

        TournamentTeamResponse.Datum dataModel = data.get(position);

        holder.bind(dataModel, position);


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
        void onDelete(TournamentTeamResponse.Datum data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemTournamentTeamBinding binding;

        public Holder(ItemTournamentTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(TournamentTeamResponse.Datum obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}