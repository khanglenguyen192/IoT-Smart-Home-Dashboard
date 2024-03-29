package com.example.smarthomedashboard.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smarthomedashboard.R;
import com.example.smarthomedashboard.Adapter.HomeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    // declared
    private TabLayout homeTabLayout;
    private ViewPager homeViewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get view
        homeTabLayout = view.findViewById(R.id.home_tab_layout);
        homeViewPager = view.findViewById(R.id.home_view_pager);

        //call
        setUpHomeViewPager();

        homeTabLayout.setupWithViewPager(homeViewPager);

        return view;
    }

    private void setUpHomeViewPager() {
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        homeViewPager.setAdapter(homeViewPagerAdapter);
    }
}
