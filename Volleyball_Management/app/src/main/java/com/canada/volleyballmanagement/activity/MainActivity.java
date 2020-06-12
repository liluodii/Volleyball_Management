package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityMainBinding;
import com.canada.volleyballmanagement.databinding.NavHeaderMainBinding;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    NavHeaderMainBinding headerMainBinding;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImageUploading(EventBusType event) {
        if (event.getType() == 1) {

            headerMainBinding.txtTeamName.setText(getLoginResponse().getData().getFirstName() + " " + getLoginResponse().getData().getLastName());

            if (!getLoginResponse().getData().getProfilePic().isEmpty()) {

                if (!getLoginResponse().getData().getProfilePic().isEmpty()) {

                    Picasso.get().load(getLoginResponse().getData().getProfilePic()).placeholder(R.drawable.ic_person).into(headerMainBinding.imgProfile);
                } else {
                    headerMainBinding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
                }

            } else {
                headerMainBinding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        EventBus.getDefault().register(this);
        binding.lvNavigation.setNavigationItemSelectedListener(this);
        binding.lvNavigation.setItemIconTintList(null);
        binding.lvNavigation.getMenu().clear();

        setHeader();
    }

    public void setHeader() {
        NavHeaderMainBinding headerMainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_main, binding
                .lvNavigation, false);
        binding.lvNavigation.addHeaderView(headerMainBinding.getRoot());
        headerMainBinding.txtTeamName.setText(getLoginResponse().getData().getFirstName() + " " + getLoginResponse().getData().getLastName());

        if (!getLoginResponse().getData().getProfilePic().isEmpty()) {
            RequestOptions options = new RequestOptions();
            options.placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person));
            options.error(getActivity().getResources().getDrawable(R.drawable.ic_person));
            Glide.with(getActivity()).setDefaultRequestOptions(options)
                    .load(getLoginResponse().getData().getProfilePic()).into(headerMainBinding.imgProfile);
        } else {
            headerMainBinding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
        }

        if (isTeamManager())
            binding.lvNavigation.inflateMenu(R.menu.team_manager_drawer);
        else
            binding.lvNavigation.inflateMenu(R.menu.league_manager_drawer);

    }


    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgMenu:
                binding.lvDrawer.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.navTeams:

                break;
            case R.id.navPlayers:
                intent = new Intent(getActivity(), PlayersActivity.class);
                startActivity(intent);
                break;
            case R.id.navSchedules:
                break;
            case R.id.navMatches:

                break;
            case R.id.navEvents:
                break;
            case R.id.navProfile:
                intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.navSignOut:
                logoutDialog();
                break;
        }

        binding.lvDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
