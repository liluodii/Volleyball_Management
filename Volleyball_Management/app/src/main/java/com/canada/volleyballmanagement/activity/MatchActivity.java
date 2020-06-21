package com.canada.volleyballmanagement.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.adapter.MatchPageAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityMatchBinding;
import com.canada.volleyballmanagement.fragment.UpcomingTabFragment;
import com.canada.volleyballmanagement.pojo.MatchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchActivity extends BaseActivity {

    ActivityMatchBinding binding;
    public static MatchActivity activity;
    public ArrayList<MatchResponse.Upcomming> dataUpcoming = new ArrayList<>();
    public ArrayList<MatchResponse.Running> dataRunning = new ArrayList<>();
    public ArrayList<MatchResponse.Completed> dataCompleted = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match);
        binding.setActivity(this);
        showToolBar(true, getResources().getString(R.string.match));
        activity = this;
        tabSelected(binding.txtUpcoming);
        callApi();
    }

    private void init() {
        binding.vpPager.setAdapter(new MatchPageAdapter(getSupportFragmentManager(), 3));

        binding.vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabDeselected();
                switch (position) {
                    case 0:
                        tabSelected(binding.txtUpcoming);
                        break;
                    case 1:
                        tabSelected(binding.txtRunning);
                        break;
                    case 2:
                        tabSelected(binding.txtCompleted);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    public void tabDeselected() {
        binding.txtUpcoming.setBackground(getResources().getDrawable(R.drawable.btn_rounded_gray));
        binding.txtRunning.setBackground(getResources().getDrawable(R.drawable.btn_rounded_gray));
        binding.txtCompleted.setBackground(getResources().getDrawable(R.drawable.btn_rounded_gray));
    }

    public void changePager(int position) {
        binding.vpPager.setCurrentItem(position);
    }

    public void tabSelected(TextView view) {
        view.setBackground(getResources().getDrawable(R.drawable.btn_rounded_yello));
    }

    public void callApi() {
        showDialog();
        requestAPI.GetMatchList(0).enqueue(GetMatchListCallback);
    }

    Callback<MatchResponse> GetMatchListCallback = new Callback<MatchResponse>() {
        @Override
        public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {

            dismissDialog();

            MatchResponse matchListResponse = response.body();

            dataUpcoming.clear();
            dataRunning.clear();
            dataCompleted.clear();

            if (matchListResponse.getReturnCode().equals("1")) {

                dataUpcoming.addAll(matchListResponse.getData().getUpcoming());
                dataRunning.addAll(matchListResponse.getData().getRunning());
                dataCompleted.addAll(matchListResponse.getData().getCompleted());

                init();

            } else {

                Toast.makeText(getActivity(), "" + matchListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(Call<MatchResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

}
