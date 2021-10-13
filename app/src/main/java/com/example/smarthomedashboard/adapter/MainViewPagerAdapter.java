package com.example.smarthomedashboard.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.smarthomedashboard.Fragment.ChartFragment;
import com.example.smarthomedashboard.Fragment.HomeFragment;
import com.example.smarthomedashboard.Fragment.SettingFragment;
import com.example.smarthomedashboard.fragment.CameraFragment;


public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ChartFragment();
            case 2:
                return new SettingFragment();
            case 3:
                return new CameraFragment();
            default:
                return new HomeFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }
}
