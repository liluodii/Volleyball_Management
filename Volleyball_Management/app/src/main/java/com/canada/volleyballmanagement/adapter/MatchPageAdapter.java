package com.canada.volleyballmanagement.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.canada.volleyballmanagement.fragment.CompletedTabFragment;
import com.canada.volleyballmanagement.fragment.RunningTabFragment;
import com.canada.volleyballmanagement.fragment.UpcomingTabFragment;


public class MatchPageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public MatchPageAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager);
        this.numOfTabs = i;
    }

    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new UpcomingTabFragment();
            case 1:
                return new RunningTabFragment();
            case 2:
                return new CompletedTabFragment();
        }
        return new UpcomingTabFragment();
    }

    public int getCount() {
        return this.numOfTabs;
    }
}
