package com.canada.volleyballmanagement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.activity.MatchActivity;
import com.canada.volleyballmanagement.adapter.UpcomingListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseFragment;
import com.canada.volleyballmanagement.databinding.FragmentUpcomingBinding;

public class UpcomingTabFragment extends BaseFragment {

    UpcomingListAdapter adapter;
    FragmentUpcomingBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_upcoming, viewGroup, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.rvUpComing.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UpcomingListAdapter(getActivity());
        adapter.addAll(MatchActivity.activity.dataUpcoming);
        binding.rvUpComing.setAdapter(adapter);
        noDataFound();
    }

    private void noDataFound() {
        if (adapter.checkSize()) {
            binding.rvUpComing.setVisibility(View.GONE);
            binding.lvNoDataFound.setVisibility(View.VISIBLE);
        } else {
            binding.rvUpComing.setVisibility(View.VISIBLE);
            binding.lvNoDataFound.setVisibility(View.GONE);
        }
    }

}
