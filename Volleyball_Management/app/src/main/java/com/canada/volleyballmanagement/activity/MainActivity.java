package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityMainBinding;
import com.canada.volleyballmanagement.databinding.NavHeaderMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
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
                binding.txtName.setText(getResources().getString(R.string.text_teams));
                break;

            case R.id.navPlayers:
                binding.txtName.setText(getResources().getString(R.string.text_players));
                break;

            case R.id.navSchedules:
                binding.txtName.setText(getResources().getString(R.string.text_schedules));
                break;

            case R.id.navMatches:
                binding.txtName.setText(getResources().getString(R.string.text_matches));
                break;

            case R.id.navEvents:
                binding.txtName.setText(getResources().getString(R.string.text_events));
                break;

            case R.id.navProfile:
                binding.txtName.setText(getResources().getString(R.string.text_your_profile));
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
