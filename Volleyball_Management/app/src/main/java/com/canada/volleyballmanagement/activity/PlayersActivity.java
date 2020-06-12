package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityPlayerBinding;

public class PlayersActivity extends BaseActivity {

    ActivityPlayerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);
        binding.setActivity(this);
        showToolBar(true, getString(R.string.text_players));
    }


    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                intent = new Intent(getActivity(), AddPlayersActivity.class);
                startActivity(intent);
                break;
        }
    }

}
