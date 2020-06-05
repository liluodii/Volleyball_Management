package com.canada.volleyballmanagement.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityMainBinding;
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
        binding.lvNavigation.inflateMenu(R.menu.team_manager_drawer);
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

        switch (menuItem.getItemId()) {

            case R.id.navTeams:

                break;

            case R.id.navPlayers:

                break;

            case R.id.navSchedules:

                break;

            case R.id.navMatches:

                break;

            case R.id.navEvents:

                break;

            case R.id.navProfile:

                break;

            case R.id.navSignOut:

                break;

        }

        binding.lvDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
