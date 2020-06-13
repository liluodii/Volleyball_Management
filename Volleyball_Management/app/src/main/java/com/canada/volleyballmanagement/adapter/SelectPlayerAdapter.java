package com.canada.volleyballmanagement.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.databinding.ItemSelectPlayerBinding;
import com.canada.volleyballmanagement.pojo.GetPlayerListResponse;

import java.util.ArrayList;
import java.util.List;


public class SelectPlayerAdapter extends RecyclerView.Adapter<SelectPlayerAdapter.Holder> {

    public EventListener mEventListener;
    Context context;
    private List<GetPlayerListResponse.Datum> data = new ArrayList<>();

    public SelectPlayerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<GetPlayerListResponse.Datum> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public String getId() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                list.add(String.valueOf(data.get(i).getPlayerID()));
            }
        }

        return TextUtils.join(",", list);

    }

    public void add(GetPlayerListResponse.Datum mData) {
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

    public GetPlayerListResponse.Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemSelectPlayerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_select_player, parent, false);
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

        final GetPlayerListResponse.Datum dataModel = data.get(position);

        holder.binding.ckbPlayer.setChecked(dataModel.isSelect());

        holder.bind(dataModel, position);

        RequestOptions options = new RequestOptions();
        options.placeholder(context.getResources().getDrawable(R.drawable.ic_person));
        options.error(context.getResources().getDrawable(R.drawable.ic_person));
        Glide.with(context).setDefaultRequestOptions(options)
                .load(dataModel.getPhoto()).into(holder.binding.imgProfile);

        holder.binding.ckbPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataModel.setSelect(!dataModel.isSelect());
            }
        });

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
        void onDelete(GetPlayerListResponse.Datum data, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ItemSelectPlayerBinding binding;

        public Holder(ItemSelectPlayerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GetPlayerListResponse.Datum obj, int position) {
            binding.setData(obj);
            binding.setPosition(position);

        }
    }


}