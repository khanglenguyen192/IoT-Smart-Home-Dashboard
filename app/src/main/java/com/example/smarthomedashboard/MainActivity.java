package com.example.smarthomedashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.smarthomedashboard.adapter.MainViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Declare
    private BottomNavigationView main_bottom_navigation;
    private ViewPager main_view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get view
        main_bottom_navigation = findViewById(R.id.main_bottom_navigation);
        main_view_pager = findViewById(R.id.main_view_pager);


        // Set up
        setUpBottomNavigation();
        setUpViewPager();

    }

    private void setUpBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        main_view_pager.setCurrentItem(0);
                        break;
                    case R.id.action_chart:
                        main_view_pager.setCurrentItem(1);
                        break;
                    case R.id.action_setting:
                        main_view_pager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        main_view_pager.setAdapter(mainViewPagerAdapter);
        main_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        main_bottom_navigation.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        main_bottom_navigation.getMenu().findItem(R.id.action_chart).setChecked(true);
                        break;
                    case 2:
                        main_bottom_navigation.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}